package kr.or.dohands.dozon.exp.domain

import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import kotlin.math.exp

@Service
class ExpHistoryYearService(
    private val expHistoryYearRepository: ExpHistoryYearRepository
) {

    @Transactional
    fun save(entity: ExpHistoryYear){
        expHistoryYearRepository.save(entity)
    }
}