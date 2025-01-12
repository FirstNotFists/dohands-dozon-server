package kr.or.dohands.dozon.post.domain

import jakarta.transaction.Transactional
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query

interface PostsRepository: JpaRepository<Posts, Long> , JpaSpecificationExecutor<Posts>{

//    @Modifying
//    @Transactional
//    @Query("update posts m set m.title = ")
//    fun updatePostsById(posts: Posts, id: Long)

}