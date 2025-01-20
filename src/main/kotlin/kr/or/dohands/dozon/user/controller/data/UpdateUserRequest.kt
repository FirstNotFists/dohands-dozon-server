package kr.or.dohands.dozon.user.controller.data

data class UpdateUserRequest(
    val number : Long,
    val name : String,
    val joinDate : String,
    val career: String,
    val level : String,
    val exp : Long
    // 이름 입사일 소속 레벨 경험치
) {
}