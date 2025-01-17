package kr.or.dohands.dozon.post.domain

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import kr.or.dohands.dozon.post.controller.data.PostRequest
import java.time.LocalDateTime

@Entity(name = "posts")
class Posts(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,

    var title: String,
    var content: String,
    val date: LocalDateTime
) {

    fun update (post: PostRequest.body): Unit{
        this.title = post.title
        this.content = post.content
    }

    companion object {

        fun toEntity (
            post: PostRequest.body,
        ) : Posts {
            return Posts(
            0,
            post.title,
            post.content,
            LocalDateTime.now()
            )
        }

        fun toEntity (
        post: PostRequest.body, id: Long
        ) : Posts {
            return Posts(
                id,
                post.title,
                post.content,
                LocalDateTime.now()
            )
        }



    }
}