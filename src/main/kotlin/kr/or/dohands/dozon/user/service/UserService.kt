package kr.or.dohands.dozon.user.service

import jakarta.transaction.Transactional
import kr.or.dohands.dozon.core.configuration.jwt.JwtToken
import kr.or.dohands.dozon.core.configuration.jwt.JwtUtil
import kr.or.dohands.dozon.exp.controller.data.ExpHistoryResponse
import kr.or.dohands.dozon.exp.service.ExpHistoryService
import kr.or.dohands.dozon.exp.service.ExpService
import kr.or.dohands.dozon.user.controller.data.*
import kr.or.dohands.dozon.user.domain.User
import kr.or.dohands.dozon.user.domain.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.lang.IllegalArgumentException

@Service
class UserService @Autowired constructor(
    private val userRepository: UserRepository,
    private val expService: ExpService,
    private val levelExpTypeService: LevelExpTypeService,
    private val expHistoryService: ExpHistoryService
) {
    private val jwtToken: JwtToken = JwtUtil()

    @Transactional
    fun findUserByNumber(number: Long): User{
        return userRepository.findUserByNumber(number)
    }

    @Transactional
    fun findUserById(id: String): User{
        return userRepository.findUserById(id)
    }

    private fun updatePassword(password: String, user: User): Unit {
        userRepository.updatePasswordById(password, user.id)
    }

    @Transactional
    fun get(number: Long): UserResponse {
        return UserResponse.of(findUserByNumber(number))
    }

    @Transactional
    fun edit (updateUserRequest: EditRequest): Unit {
        val user: User = findUserById(updateUserRequest.id)
        updatePassword(updateUserRequest.password, user)
    }

    @Transactional
    fun signIn(signInRequest: SignInRequest): SignInResponse {
        val user = userRepository.findUserById(signInRequest.id)
        val exp = expService.findExpInYear(user)

        validate(user, signInRequest)

        val token = jwtToken.create(user.id)
        pushToken(user.id, signInRequest.pushToken)

        return SignInResponse.of(user, token, exp)
    }

//    @Transactional
//    fun signUp(signUpRequest: SignInRequest): SignUpResponse {
//
//    }


    @Transactional
    fun validate(user: User, signInRequest: SignInRequest) {
        var default = user?.password
        var edited = user?.newPassword

        if(edited == null) {
            if(signInRequest.password != default){
                throw IllegalArgumentException("아이디 및 비밀번호를 확인해주세요")
            }
        }else if(edited != null){
            if (signInRequest.password != edited){
                throw IllegalArgumentException("아이디 및 비밀번호를 확인해주세요")
            }
        }
    }

    @Transactional
    fun pushToken(id: String, token: String): Unit {
        userRepository.updateTokenById(id, token)
    }

    @Transactional
    fun myPage(id: String): MyPageResponse {
        // user 검색
        val user = findUserById(id)

        // user의 level 앞글짜따기 -> level 테이블 조회에 사용, < exp 이고 order by 오름차순해서 맨첫번째만 가져오기
        var needNextLevelExp = 0L
        val type = user.level.level.substring(0, 1)
        val myExp = user.exp

        val nextLevelExp = levelExpTypeService.findNextLevel(type, myExp)
        needNextLevelExp = nextLevelExp.get(0).exp // 총 필요 경험치

        val level = user.level.level
//        val needExp = needNextLevelExp - myExp // 현재 필요경험치

        val history = ExpHistoryResponse.from(expHistoryService.findExpHistoryByNumber(user.number))

        return MyPageResponse.of(level, myExp, needNextLevelExp, history)
    }

}
