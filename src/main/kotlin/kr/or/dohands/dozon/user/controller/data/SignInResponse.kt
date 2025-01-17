package kr.or.dohands.dozon.user.controller.data

import kr.or.dohands.dozon.user.domain.User
import kr.or.dohands.dozon.user.domain.UserType
import lombok.AllArgsConstructor
import lombok.Data

@Data
@AllArgsConstructor
class SignInResponse(
    val userType: UserType,
    val exp: Long?,
    val number: Long,
    val career: String,
    val id: String,
    val joinDate: String,
    val level: String,
    val name: String,
    val team : String,
    val token: String,
){

    companion object {
        fun of(user: User, token:String, exp: Long?): SignInResponse{
            return SignInResponse(
                user.userType,
                exp,
                user.number,
                user.career.name.toString(),
                user.id,
                user.joinDate,
                user.level.level.toString(),
                user.name,
                user.part.name.toString(),
                token
            )
        }
    }

}
