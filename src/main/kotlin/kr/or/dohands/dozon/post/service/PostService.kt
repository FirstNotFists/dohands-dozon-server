package kr.or.dohands.dozon.post.service

import kr.or.dohands.dozon.post.controller.data.PostRequest
import kr.or.dohands.dozon.post.domain.Posts
import org.springframework.stereotype.Service

@Service
interface PostService {

    fun findAll(): List<Posts>
    fun findById(id: Long): Posts
    fun save(posts: PostRequest.body): Unit
    fun deleteById(id: Long):Unit
    fun update(id: Long, post: PostRequest.body): Unit
}