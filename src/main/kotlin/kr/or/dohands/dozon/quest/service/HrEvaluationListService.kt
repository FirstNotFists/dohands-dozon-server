package kr.or.dohands.dozon.quest.service

import kr.or.dohands.dozon.quest.domain.HrEvaluationList
import kr.or.dohands.dozon.quest.domain.HrEvaluationListRepository
import org.springframework.stereotype.Service

@Service
class HrEvaluationListService(
    private val hrEvaluationListRepository: HrEvaluationListRepository
) {

    fun findAll() : List<HrEvaluationList> {
        return hrEvaluationListRepository.findAll()
    }
}