package kr.or.dohands.dozon.user.domain

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor

interface TeamRepository : JpaRepository<Team, Long>, JpaSpecificationExecutor<Team>{

}