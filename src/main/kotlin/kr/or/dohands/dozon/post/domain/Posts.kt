package kr.or.dohands.dozon.post.domain

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import kr.or.dohands.dozon.post.controller.data.PostRequest

@Entity(name = "posts")
class Posts(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long,
    val title: String,
    val content: String,
) {

    companion object {

        fun toEntity (
            post: PostRequest.create,
        ) : Posts {
            return Posts(
            0,
            post.title,
            post.content
            )
        }

        fun toEntity (
        post: PostRequest.update, id: Long
        ) : Posts {
            return Posts(
                id,
                post.title,
                post.content
            )
        }

    }
}