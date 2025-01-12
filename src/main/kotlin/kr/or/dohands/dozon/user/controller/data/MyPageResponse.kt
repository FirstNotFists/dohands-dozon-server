package kr.or.dohands.dozon.user.controller.data

import kr.or.dohands.dozon.exp.controller.data.ExpHistoryResponse

class MyPageResponse(
    val level: String,
    val needExp: Long,
    val nextLevelExp: Long,
    val history: List<ExpHistoryResponse>
) {

    companion object{
        fun of(level: String, needExp: Long, nextLevelExp: Long, history: List<ExpHistoryResponse>): MyPageResponse {
            return MyPageResponse(
                level,
                needExp,
                nextLevelExp,
                history
            )
        }
    }


}
