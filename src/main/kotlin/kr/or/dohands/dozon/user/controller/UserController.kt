package kr.or.dohands.dozon.user.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import kr.or.dohands.dozon.core.responsetemplate.Response
import kr.or.dohands.dozon.user.controller.data.*
import kr.or.dohands.dozon.user.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class UserController @Autowired constructor(
    private val userService: UserService,
) {

    @PostMapping("/signIn")
    @Operation(summary = "로그인 요청", description = "id(String), password(String) 을 Body로 요청하시면 됩니다.")
    @ApiResponse(responseCode = "200", description = "password를 제외한 user정보, token(String)")
    @ApiResponse(responseCode = "401", description = "아이디 및 비밀번호를 확인해주세요")
    fun signIn(@RequestBody signInRequest: SignInRequest ): Response<SignInResponse> {
        val response = userService.signIn(signInRequest)

        return Response(200,
            response,
            "로그인을 성공하였습니다.")
    }


    @GetMapping("/user")
    @Operation(summary = "내 정보 확인", description = "내 정보를 확인합니다.")
    @ApiResponse(responseCode = "200", description = "비밀번호를 제외한 해당 유저의 정보를 조회합니다.")
    fun get(@RequestParam number: Long): Response<UserResponse>{
        val response = userService.get(number)

        return Response(200,
            response,
            "내 정보 확인 조회입니다."
            )
    }

    @PatchMapping("/user")
    @Operation(summary = "비밀번호 변경", description = "비밀번호를 변경합니다")
    @ApiResponse(responseCode = "200", description = "id, 변경할 password를 던져주시면 됩니다.")
    fun edit(@RequestBody updateUserRequest: EditRequest): Response<String>{
        userService.edit(updateUserRequest)

        return Response(200,
            "",
            "수정이 완료되었습니다.")
    }

    @GetMapping("/my/{id}")
    @Operation(summary = "마이페이지 조회", description = "요청 파라미터 id: 사번 , 현재 누적 경험치: exp , 레벨업을 위한 필요 경험치 : needExp, 현재 레벨: level")
    @ApiResponse(responseCode = "200", description = "" +
            "level: 현재 레벨,\n" +
            " needExp: 필요 경험치,\n" +
            " nextLevelExp: 다음 레벨까지 경험치,\n" +
            " exp: 부여 경험치,\n" +
            " number: 사번,\n" +
            " date: 부여 받은 날짜,\n" +
            " questType: 퀘스트명\n" +
            "")
    fun mypage(@PathVariable id: String): Response<MyPageResponse> {
        val response = userService.myPage(id)

        return Response(200,
            response,
            "마이페이지 정보 조회입니다."
            )
    }
}