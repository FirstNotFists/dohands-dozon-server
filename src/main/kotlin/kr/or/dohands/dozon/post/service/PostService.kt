package kr.or.dohands.dozon.post.service

import jakarta.transaction.Transactional
import kr.or.dohands.dozon.post.controller.data.PostRequest
import kr.or.dohands.dozon.post.domain.Posts
import kr.or.dohands.dozon.post.domain.PostsRepository
import org.springframework.stereotype.Service

@Service
@Transactional
class PostService(
    private val postsRepository: PostsRepository
) {

    fun findById(id: Long) : Posts {
        return postsRepository.findById(id).orElseThrow()
    }

    fun save(post: PostRequest.create): Unit {
        val entity = Posts.toEntity(post)
        postsRepository.save(entity)
    }

    fun deleteById(id: Long): Unit {
        postsRepository.deleteById(id)
    }

    fun updateById(post: PostRequest.update): Unit {
        val entity = Posts.toEntity(post, post.id)
//        postsRepository.updatePostsById(entity, post.id)
    }

    fun findAll(): List<Posts> {
        return postsRepository.findAll()
    }


}