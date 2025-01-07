package kr.or.dohands.dozon.quest.domain

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor

interface SwordProjectRepository: JpaRepository<SwordProject, Long> , JpaSpecificationExecutor<SwordProject>{
}