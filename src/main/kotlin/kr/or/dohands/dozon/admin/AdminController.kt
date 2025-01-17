package kr.or.dohands.dozon.admin

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import kr.or.dohands.dozon.core.configuration.jwt.JwtToken
import kr.or.dohands.dozon.core.exception.JwtInValidException
import kr.or.dohands.dozon.core.responsetemplate.Response
import kr.or.dohands.dozon.user.controller.data.UpdateUserRequest
import kr.or.dohands.dozon.user.domain.User
import kr.or.dohands.dozon.user.service.UserService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/admin")
class AdminController (
    private val jwtToken: JwtToken,
    private val adminServiceImpl: AdminServiceImpl,
    private val userService: UserService
) {

    @GetMapping
    @Operation(summary = "유저 목록 조회", description = "유저 목록조회입니다. header로 전달 token: String")
    @ApiResponse(responseCode = "401", description = "토큰이 유효하지 않습니다.")
    fun selectUser(@RequestHeader token: String) : Response<AdminUsers> {
//        if(!jwtToken.isValidToken(token)){
//            throw JwtInValidException("토큰이 유효하지 않습니다.")
//        }

        val response = adminServiceImpl.userManageSelect()
        return Response(200, response, "유저 목록 조회입니다.")
    }

    @PostMapping("/create")
    @ApiResponse(responseCode = "401", description = "이미 존재하는 사번입니다.")
    @Operation(summary = "계정생성", description = "    " +
            "     number : Long,\n" +
            "     career : String,\n" +
            "     name : String,\n" +
            "     joinDate : String,\n" +
            "     level : String, // 레벨의 타입\n" +
            "     exp : Long,\n" +
            "     id : String,\n" +
            "     password : String")
    @ApiResponse(responseCode = "401", description = "토큰이 유효하지 않습니다.")
    fun createUser(@RequestHeader token: String, @RequestBody request: CreateUserRequest):Response<String> {
//        if(!jwtToken.isValidToken(token)){
//            throw JwtInValidException("토큰이 유효하지 않습니다.")
//        }

        adminServiceImpl.createUser(request)
        return Response(200, "", "유저 생성이 완료되었습니다.")

    }

    @PutMapping("/update")
    @Operation(summary = "내 정보 수정", description = "val name : String,\n" +
            "    val joinDate : String,\n" +
            "    val career: String,\n" +
            "    val level : String,\n" +
            "    val exp : String")
    @ApiResponse(responseCode = "200", description = "변경이 완료되었습니다.")
    fun updateUser(@RequestBody request: UpdateUserRequest): Response<String>{
        userService.update(request)

        return Response(200,
            "",
            "수정이 완료되었습니다.")
    }
}