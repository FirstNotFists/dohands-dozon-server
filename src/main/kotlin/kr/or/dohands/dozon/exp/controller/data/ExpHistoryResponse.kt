package kr.or.dohands.dozon.exp.controller.data

import kr.or.dohands.dozon.exp.domain.ExpHistory
import java.time.LocalDateTime
import java.util.*
import kotlin.collections.ArrayList

class ExpHistoryResponse(
    val exp: Long,
    val grade: String,
    val date: LocalDateTime,
    val questType: String
) {
    companion object{
        fun from(list: List<ExpHistory>): List<ExpHistoryResponse>{
            var to : ArrayList<ExpHistoryResponse> = arrayListOf()

            for(i in list){
                to.add(
                    ExpHistoryResponse(
                        i.exp,
                        i.grade,
                        i.updateDate,
                        i.questType
                    )
                )
            }
            return to
        }
    }

}