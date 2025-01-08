package kr.or.dohands.dozon.sheet.service

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport
import com.google.api.client.json.gson.GsonFactory
import com.google.api.client.json.jackson2.JacksonFactory
import com.google.api.services.sheets.v4.Sheets
import com.google.api.services.sheets.v4.SheetsScopes
import com.google.api.services.sheets.v4.model.ValueRange
import com.google.auth.http.HttpCredentialsAdapter
import com.google.auth.oauth2.GoogleCredentials
import com.google.auth.oauth2.ServiceAccountCredentials
import java.io.FileInputStream


class GoogleSheetsService(
    private val credentialPath:String,
    private val email: String,
    private val applicationName: String,
    private val sheetId: String
) {
    private var sheets: Sheets? = null

//    init {
//        println("!!! ${credentialPath}")
//        println("!!! ${email}")
//        println("!!! ${applicationName}")
//        println("!!! ${sheetId}")
//    }

    fun getSheetsService(): Sheets {
        if (sheets == null) {
            sheets = initializeSheets()
        }
        return sheets!!
    }


    private fun initializeSheets(): Sheets {
        var credentials = GoogleCredentials
            .fromStream(FileInputStream(credentialPath))
            .createScoped(listOf(SheetsScopes.SPREADSHEETS))
        return Sheets(
            com.google.api.client.http.javanet.NetHttpTransport(),
            JacksonFactory.getDefaultInstance(),
            HttpCredentialsAdapter(credentials)
        )
    }


    fun loadCredentials(): GoogleCredentials {
        return GoogleCredentials
            .fromStream(FileInputStream(credentialPath))
            .createScoped(listOf(SheetsScopes.SPREADSHEETS))
    }

    fun getValue(sheetName:String, cellRange:String): ValueRange {
        return getSheets()
            .spreadsheets()
            .values()
            .get(sheetId, "${sheetName.trim()}!${cellRange.trim()}")
            .execute()
    }

    private fun getSheets(): Sheets {
        return Sheets.Builder(
            GoogleNetHttpTransport.newTrustedTransport(),
            GsonFactory.getDefaultInstance(),
            HttpCredentialsAdapter(
                ServiceAccountCredentials.fromStream(FileInputStream(credentialPath))
                    .createScoped(listOf(SheetsScopes.SPREADSHEETS))
                    .createDelegated(email)
            )
        ).setApplicationName(applicationName).build()
    }

}