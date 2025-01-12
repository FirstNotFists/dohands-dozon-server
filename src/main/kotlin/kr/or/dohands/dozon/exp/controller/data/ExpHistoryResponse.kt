package kr.or.dohands.dozon.exp.controller.data

import kr.or.dohands.dozon.exp.domain.ExpHistory
import java.util.*
import kotlin.collections.ArrayList

class ExpHistoryResponse(
    val exp: Long,
    val number: Long,
    val date: Date,
    val questType: String
) {
    companion object{
        fun from(list: List<ExpHistory>): List<ExpHistoryResponse>{
            var to : ArrayList<ExpHistoryResponse> = arrayListOf()

            for(i in list){
                to.add(
                    ExpHistoryResponse(
                        i.exp,
                        i.number,
                        i.updateDate,
                        i.questType
                    )
                )
            }
            return to
        }
    }

}