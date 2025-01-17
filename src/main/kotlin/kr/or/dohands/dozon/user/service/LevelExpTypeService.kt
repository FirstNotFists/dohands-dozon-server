package kr.or.dohands.dozon.user.service

import jakarta.transaction.Transactional
import kr.or.dohands.dozon.user.domain.LevelExpType
import kr.or.dohands.dozon.user.domain.LevelExpTypeRepository
import org.springframework.stereotype.Service

@Service
class LevelExpTypeService(
    private val levelExpTypeRepository: LevelExpTypeRepository
){

    @Transactional
    fun findNextLevel(type: String, exp: Long) : List<LevelExpType> {
        return levelExpTypeRepository.findNextLevel(type, exp)
    }

    @Transactional
    fun findNextLevelUnder(type: String, exp: Long) : List<LevelExpType> {
        return levelExpTypeRepository.findNextLevelUnder(type, exp)
    }

    fun findNextLevelNowCheck(type: String, exp: Long) : List<LevelExpType> {
        return levelExpTypeRepository.findNextLevelNowCheck(type, exp)
    }

    fun findByLevel(level: String): LevelExpType {
        return levelExpTypeRepository.findByLevel(level)
    }

}