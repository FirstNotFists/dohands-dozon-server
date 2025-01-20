package kr.or.dohands.dozon.quest.controller.data

data class QuestCount(
    val questType: String,
    val clearNumber : Long,
    val totalNumber: Long,
    val questName : String
) {
    data class Response(
        val list: List<Any>
    ){
    }

    companion object{
        fun of(questType: String, clearNumber : Long, totalNumber : Long, questName : String) : QuestCount{
            return QuestCount(
                questType, clearNumber, totalNumber, questName
            )
        }
    }
}