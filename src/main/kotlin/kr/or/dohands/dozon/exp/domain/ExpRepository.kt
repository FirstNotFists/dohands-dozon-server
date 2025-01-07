package kr.or.dohands.dozon.exp.domain

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor

interface ExpRepository: JpaRepository<Exp, Long>, JpaSpecificationExecutor<Exp> {
}