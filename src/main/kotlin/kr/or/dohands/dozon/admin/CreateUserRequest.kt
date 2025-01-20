package kr.or.dohands.dozon.admin

data class CreateUserRequest(
    val number : Long,
    val career : String,
    val name : String,
    val joinDate : String,
    val level : String, // 레벨의 타입
    val exp : Long,
    val id : String,
    val password : String
) {

    companion object{

    }
}