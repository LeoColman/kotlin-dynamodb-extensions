/*
 * Copyright 2021 Leonardo Colman Lopes
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import java.lang.System.getenv

plugins {
    kotlin("jvm") version "1.4.20"
    `maven-publish`
    signing
    id("org.jetbrains.dokka") version "1.4.20"
    id("io.gitlab.arturbosch.detekt").version("1.15.0-RC1")
}

group = "br.com.colman.dynamodb"
version = getenv("RELEASE_VERSION") ?: "local"

repositories {
    mavenCentral()
    jcenter()
    maven(url = "https://s3-us-west-2.amazonaws.com/dynamodb-local/release")
}

dependencies {
    // Kotest
    testImplementation("io.kotest:kotest-runner-junit5:4.3.2")
    testImplementation("io.kotest:kotest-property:4.3.2")

    // Mockk
    testImplementation("io.mockk:mockk:1.10.3")

    // Dynamo
    api("software.amazon.awssdk:dynamodb-enhanced:2.16.62")
    testImplementation("com.amazonaws:DynamoDBLocal:1.13.5")
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

tasks.withType<Test> {
    useJUnitPlatform()
}

kotlin {
    explicitApi()
}

val sourcesJar by tasks.registering(Jar::class) {
    archiveClassifier.set("sources")
    from(sourceSets.getByName("main").allSource)
}

val javadocJar by tasks.registering(Jar::class) {
    dependsOn("dokkaHtml")
    archiveClassifier.set("javadoc")
    from("$buildDir/dokka")
}


publishing {
    repositories {

        maven("https://oss.sonatype.org/service/local/staging/deploy/maven2") {
            credentials {
                username = getenv("OSSRH_USERNAME")
                password = getenv("OSSRH_PASSWORD")
            }
        }
    }

    publications {

        register("mavenJava", MavenPublication::class) {
            from(components["java"])
            artifact(sourcesJar.get())
            artifact(javadocJar.get())

            pom {
                name.set("kotlin-dynamodb-extensions")
                description.set("Kotlin extension functions and utilities for DynamoDB")
                url.set("https://www.github.com/LeoColman/kotlin-dynamodb-extensions/")


                scm {
                    connection.set("scm:git:http://www.github.com/LeoColman/kotlin-dynamodb-extensions")
                    developerConnection.set("scm:git:http://github.com/LeoColman")
                    url.set("https://www.github.com/LeoColman/kotlin-dynamodb-extensions")
                }

                licenses {
                    license {
                        name.set("The Apache 2.0 License")
                        url.set("https://opensource.org/licenses/Apache-2.0")
                    }
                }

                developers {
                    developer {
                        id.set("LeoColman")
                        name.set("Leonardo Colman Lopes")
                        email.set("leonardo.dev@colman.com.br")
                    }
                }
            }
        }
    }
}

val signingKey: String? by project
val signingPassword: String? by project

signing {
    if(signingKey != null && signingPassword != null) {
        useGpgCmd()
        useInMemoryPgpKeys(signingKey, signingPassword)
        sign(publishing.publications["mavenJava"])
    }
}
