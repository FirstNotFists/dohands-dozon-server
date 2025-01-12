package kr.or.dohands.dozon.post.controller.data

data class PostRequest(
    val id: Long,
    val title: String,
    val content: String
) {

    data class create(
        val title: String,
        val content: String
    ) {

    }

    data class update(
        val id: Long,
        val title: String,
        val content: String
    ){

    }


}