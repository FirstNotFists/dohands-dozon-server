package kr.or.dohands.dozon.quest.controller.data

class QuestsResponse(
    val list : List<Any>
) {
    companion object{
        fun from(list : List<Any>) : QuestsResponse{
            return QuestsResponse(
                list
            )
        }

    }
}