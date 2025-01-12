package kr.or.dohands.dozon.user.controller.data

import kr.or.dohands.dozon.user.domain.Part
import kr.or.dohands.dozon.user.domain.User
import kr.or.dohands.dozon.user.domain.UserType

class UserResponse(
    userType: UserType,
    exp: Long,
    number: Long,
    career: String,
    id: String,
    joinDate: String,
    level: String,
    name: String,
    part: Part
){
    companion object{
        fun of(user: User): UserResponse {
            return UserResponse(
                user.userType,
                user.exp,
                user.number,
                user.career.name,
                user.id,
                user.joinDate,
                user.level.level,
                user.name,
                user.part
            )
        }
    }
}
