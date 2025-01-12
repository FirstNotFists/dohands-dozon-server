package kr.or.dohands.dozon.sheet.service

import com.google.api.services.drive.Drive
import com.google.api.services.drive.DriveScopes
import com.google.auth.http.HttpCredentialsAdapter
import com.google.auth.oauth2.GoogleCredentials
import org.springframework.stereotype.Service
import java.io.FileInputStream

@Service
class DriveService(){


    fun getDriveService(): Drive {
        val credentials = GoogleCredentials
            .fromStream(FileInputStream("src/main/resources/key.json"))
            .createScoped(listOf(DriveScopes.DRIVE))

        return Drive.Builder(
            com.google.api.client.http.javanet.NetHttpTransport(),
            com.google.api.client.json.jackson2.JacksonFactory(),
            HttpCredentialsAdapter(credentials)
        )
            .setApplicationName("Google Drive API Integration")
            .build()
    }

}
