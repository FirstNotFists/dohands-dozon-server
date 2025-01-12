package kr.or.dohands.dozon.user.domain

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor

interface PartRepository : JpaRepository<Part, Long>, JpaSpecificationExecutor<Part>{

}