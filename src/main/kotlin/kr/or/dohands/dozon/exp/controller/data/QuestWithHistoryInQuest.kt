package kr.or.dohands.dozon.exp.controller.data

data class QuestWithHistoryInQuest(
    val grade: String,
    val date: String,
    val questName: String,
    val content: String,
    val exp : Long

) {

    data class Response (
        val list : List<HashMap<String, Object>>
    ) {
        companion object {
            fun from(list: List<HashMap<String, Object>>):Response{
                return Response(
                    list
                )
            }
        }
    }

    companion object{
        fun of(grade: String, date: String, questName: String, content: String, exp: Long): QuestWithHistoryInQuest {
            return QuestWithHistoryInQuest(
                grade,date, questName, content, exp
            )
        }
    }
}