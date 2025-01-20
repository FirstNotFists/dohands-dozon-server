package kr.or.dohands.dozon.quest.controller.data

class Quests (
    val questType: String,
    val exp: Long,
    val grade : String,
    val dayday: String,
    val month: String,
    val day : String
){

    companion object{
        fun from(questType: String, exp: Long , grade: String, dayday: String, month: String, day: String): Quests{
            return Quests(
                questType,
                exp,
                grade,
                dayday,
                month,
                day
            )
        }
    }

}
