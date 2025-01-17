package kr.or.dohands.dozon.admin

import kr.or.dohands.dozon.user.domain.User

data class AdminUsers(
    val list: List<User>
) {

    companion object{
        fun from(list: List<User>): AdminUsers {
            return AdminUsers(
                list
            )
        }
    }

}