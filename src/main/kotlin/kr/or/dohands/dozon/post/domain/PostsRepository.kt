package kr.or.dohands.dozon.post.domain

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor

interface PostsRepository: JpaRepository<Posts, Long> , JpaSpecificationExecutor<Posts>{
}