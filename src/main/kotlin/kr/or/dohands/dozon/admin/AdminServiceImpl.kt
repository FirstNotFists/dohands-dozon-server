package kr.or.dohands.dozon.admin

import jakarta.transaction.Transactional
import kr.or.dohands.dozon.user.domain.*
import kr.or.dohands.dozon.user.service.CareerService
import kr.or.dohands.dozon.user.service.LevelExpTypeService
import kr.or.dohands.dozon.user.service.UserService
import org.springframework.stereotype.Component

@Component
class AdminServiceImpl (
    private val userService: UserService,
    private val levelExpTypeService: LevelExpTypeService,
    private val partRepository: PartRepository,
    private val careerService: CareerService
){
    @Transactional
    fun userManageSelect(): AdminUsers {
        val users = userService.findUsers()
        return AdminUsers.from(users)
    }

    @Transactional
    fun createUser(request: CreateUserRequest) : Unit {

        val part = partRepository.findAll()
        val career = careerService.findByName(request.career)

        userService.save(
            User(
                request.number,
                request.name,
                request.joinDate,
                request.id,
                "1111",
                request.password,
                request.exp,
                checkLevelType(request.level, request.exp),
                part.get(0),
                career,
                UserType.User,
                ""
            )
        )
    }

    fun checkLevelType(level: String, exp: Long): LevelExpType{
        return levelExpTypeService.findNextLevelUnder(level, exp).get(0)
    }
}