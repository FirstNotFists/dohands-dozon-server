package kr.or.dohands.dozon.user.service

import kr.or.dohands.dozon.user.domain.Career
import kr.or.dohands.dozon.user.domain.CareerRepository
import org.springframework.stereotype.Service

@Service
class CareerService(
    private val careerRepository: CareerRepository
) {
    fun findByName(name: String): Career {
        return careerRepository.findByName(name)
    }

}