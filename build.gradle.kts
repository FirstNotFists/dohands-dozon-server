plugins {
    kotlin("jvm") version "1.9.25"
    kotlin("plugin.spring") version "1.9.25"
    id("org.springframework.boot") version "3.3.7"
    id("io.spring.dependency-management") version "1.1.7"
    kotlin("plugin.jpa") version "1.9.25"
    id("org.unbroken-dome.test-sets") version "4.1.0" // 테스트 세트 플러그인
    id("maven-publish")
    id("signing")
}

group = "kr.or.dohands"
version = "0.0.1-SNAPSHOT"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(17)
    }
    withJavadocJar()
    withSourcesJar()
}

repositories {
    mavenCentral()
}

val isReleaseVersion = !version.toString().endsWith("SNAPSHOT")

dependencies {
    // Spring Boot
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-web")

    // Jackson
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.15.2")
    implementation("com.fasterxml.jackson.core:jackson-core:2.15.2")

    // Google API
    implementation("com.google.api-client:google-api-client:1.34.1")
    implementation("com.google.auth:google-auth-library-oauth2-http:1.17.0")
    implementation("com.google.apis:google-api-services-sheets:v4-rev612-1.25.0")
    implementation("com.google.http-client:google-http-client-jackson2:1.41.0")
    implementation("com.google.apis:google-api-services-drive:v3-rev136-1.25.0")

    // HTTP Client
    implementation("org.apache.httpcomponents.client5:httpclient5:5.2.1")

    // Logging
    implementation("org.slf4j:slf4j-api:2.0.9")
    implementation("org.slf4j:jcl-over-slf4j:2.0.9")

    // Commons Lang
    implementation("org.apache.commons:commons-lang3:3.13.0")

    // JWT
    implementation("com.auth0:java-jwt:4.4.0")

    // OpenAPI
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.2.0")

    // Database
    runtimeOnly("com.mysql:mysql-connector-j")

    // Lombok
    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")

    // Testing
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
    testImplementation("io.kotest:kotest-runner-junit5:5.7.2")
    testImplementation("io.kotest:kotest-framework-api:5.7.2")
    testImplementation("org.mock-server:mockserver-junit-jupiter:5.15.0")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

    // Integration Test
//    integrationTestImplementation("org.junit.jupiter:junit-jupiter-api:5.10.0")
//    integrationTestRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.10.0")
}

kotlin {
    compilerOptions {
        freeCompilerArgs.addAll("-Xjsr305=strict")
    }
}

allOpen {
    annotation("jakarta.persistence.Entity")
    annotation("jakarta.persistence.MappedSuperclass")
    annotation("jakarta.persistence.Embeddable")
}

testSets {
    create("integrationTest")
}

tasks.withType<Test> {
    useJUnitPlatform()
}

publishing {
    repositories {
        maven {
            name = "ossrh"
            val releaseRepoUrl = project.findProperty("ossrhReleaseRepoUrl") as String? ?: ""
            val snapshotRepoUrl = project.findProperty("ossrhSnapshotRepoUrl") as String? ?: ""
            url = if (isReleaseVersion) uri(releaseRepoUrl) else uri(snapshotRepoUrl)

            credentials {
                username = project.findProperty("ossrhUsername") as String? ?: System.getenv("OSSRH_USERNAME") ?: ""
                password = project.findProperty("ossrhPassword") as String? ?: System.getenv("OSSRH_PASSWORD") ?: ""
            }
        }

        maven {
            name = "nexus"
            val releaseRepoUrl = project.findProperty("nexusReleaseRepoUrl") as String? ?: ""
            val snapshotRepoUrl = project.findProperty("nexusSnapshotRepoUrl") as String? ?: ""
            url = if (isReleaseVersion) uri(releaseRepoUrl) else uri(snapshotRepoUrl)

            credentials {
                username = project.findProperty("nexusUsername") as String? ?: System.getenv("NEXUS_USERNAME") ?: ""
                password = project.findProperty("nexusPassword") as String? ?: System.getenv("NEXUS_PASSWORD") ?: ""
            }
        }
    }

    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])

            pom {
                name.set("expo-server-sdk")
                description.set("Java implementation of expo-server-sdk implementation. Classes and methods to manage push notifications")
                url.set("https://github.com/nia-medtech/expo-server-sdk-java")

                licenses {
                    license {
                        name.set("The MIT License")
                        url.set("https://raw.githubusercontent.com/nia-medtech/expo-server-sdk-java/edit/main/LICENCE.txt")
                    }
                }
                developers {
                    developer {
                        id.set("oliverwelter")
                        name.set("Oliver Welter")
                        email.set("oliver.welter@nia-medtech.com")
                    }
                }
                scm {
                    connection.set("scm:git:git@github.com:nia-medtech/expo-server-sdk-java.git")
                    developerConnection.set("scm:git:git@github.com:nia-medtech/expo-server-sdk-java.git")
                    url.set("https://github.com/nia-medtech/expo-server-sdk-java")
                }
            }
        }
    }
}


signing {
    isRequired = isReleaseVersion && gradle.taskGraph.hasTask("publish")
    useInMemoryPgpKeys(
        project.findProperty("signingKey") as String?,
        project.findProperty("signingPassword") as String?
    )
    sign(publishing.publications["mavenJava"])
}


tasks.named<Test>("integrationTest") {
    useJUnitPlatform()
}
