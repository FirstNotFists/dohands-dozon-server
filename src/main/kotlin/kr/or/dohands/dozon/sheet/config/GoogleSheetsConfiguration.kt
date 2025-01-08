package kr.or.dohands.dozon.sheet.config

import kr.or.dohands.dozon.sheet.service.GoogleSheetsService
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class GoogleSheetsConfiguration {

    @Value("\${credentialPath}") private lateinit var credentialPath:String
    @Value("\${email}") private lateinit var email: String
    @Value("\${applicationName}") private lateinit var applicationName: String
    @Value("\${sheetId}") private lateinit var sheetId: String

    @Bean
    fun googleSheetsService(): GoogleSheetsService {
        return GoogleSheetsService(credentialPath, email, applicationName, sheetId)
    }
}