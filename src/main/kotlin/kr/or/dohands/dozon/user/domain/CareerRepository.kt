package kr.or.dohands.dozon.user.domain

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor

interface CareerRepository:JpaRepository<Career, Long>, JpaSpecificationExecutor<Career> {

    fun findByName(name: String): Career
}