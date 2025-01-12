package kr.or.dohands.dozon.exp.service

import jakarta.transaction.Transactional
import kr.or.dohands.dozon.exp.domain.ExpHistory
import kr.or.dohands.dozon.exp.domain.ExpHistoryRepository
import org.springframework.stereotype.Service
import kotlin.math.exp

@Service
class ExpHistoryService(
    private val expHistoryRepository: ExpHistoryRepository
) {

    @Transactional
    fun findExpHistoryByNumber(number: Long): List<ExpHistory> {
        return expHistoryRepository.findExpHistoryByNumber(number)
    }
}