package kr.or.dohands.dozon.post.service

import jakarta.transaction.Transactional
import kr.or.dohands.dozon.post.controller.data.PostRequest
import kr.or.dohands.dozon.post.domain.Posts
import kr.or.dohands.dozon.post.domain.PostsRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service

@Transactional
@Component
class PostServiceImpl(
    private val postsRepository: PostsRepository
): PostService {

    override fun findById(id: Long) : Posts {
        return postsRepository.findByIdOrNull(id = id) ?: throw NoSuchElementException("해당 게시글이 존재하지 않습니다.")
    }

    override fun save(post: PostRequest.body): Unit {
        val entity = Posts.toEntity(post)
        postsRepository.save(entity)
    }

    override fun deleteById(id: Long): Unit {
        postsRepository.deleteById(id)
    }

    override fun update(id: Long, post: PostRequest.body): Unit {
        val before = postsRepository.findById(id).get()
        before.update(post)
    }

    override fun findAll(): List<Posts> {
        return postsRepository.findAll()
    }


}