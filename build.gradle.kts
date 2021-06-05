group = "mx.com.inftel.codegen"
version = "1.0.1"

repositories {
    mavenCentral()
}

plugins {
    kotlin("multiplatform") version "1.4.21"
    kotlin("plugin.serialization") version "1.4.21"
    `maven-publish`
    signing
}

kotlin {

    jvm()

    js(org.jetbrains.kotlin.gradle.plugin.KotlinJsCompilerType.BOTH) {
        browser {}
    }

    sourceSets {

        val commonMain by getting {
            dependencies {
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-core:1.0.1")
            }
        }

        val jsMain by getting {
            dependencies {
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-core:1.0.1")
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.0.1")
            }
        }

        val jvmMain by getting {
            dependencies {
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-core:1.0.1")
                compileOnly("jakarta.ejb:jakarta.ejb-api:3.2.6")
                compileOnly("jakarta.faces:jakarta.faces-api:2.3.2")
                compileOnly("jakarta.persistence:jakarta.persistence-api:2.2.3")
                compileOnly("jakarta.security.auth.message:jakarta.security.auth.message-api:1.1.3")
                compileOnly("jakarta.servlet:jakarta.servlet-api:4.0.3")
                compileOnly("jakarta.ws.rs:jakarta.ws.rs-api:2.1.6")
            }
        }
    }
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
    kotlinOptions {
        javaParameters = true
        jvmTarget = "1.8"
    }
}

val fakeJavadoc by tasks.registering(Jar::class) {
    archiveBaseName.set("${project.name}-fake")
    archiveClassifier.set("javadoc")
    from(file("$projectDir/files/README"))
}

val extraSources by tasks.registering(Jar::class) {
    archiveBaseName.set("${project.name}-extra-sources")
    archiveClassifier.set("source")
    from(file("$projectDir/src/commonMain")) {
        into("commonMain")
    }
    from(file("$projectDir/src/jvmMain")) {
        into("jvmMain")
    }
}

publishing {
    publications.withType<MavenPublication> {
        pom {
            name.set("${project.group}:${project.name}")
            description.set("Codegen API Library")
            url.set("https://github.com/santoszv/codegen-api")
            inceptionYear.set("2021")
            licenses {
                license {
                    name.set("Apache License, Version 2.0")
                    url.set("https://www.apache.org/licenses/LICENSE-2.0")
                }
            }
            developers {
                developer {
                    id.set("santoszv")
                    name.set("Santos Zatarain Vera")
                    email.set("santoszv@inftel.com.mx")
                    url.set("https://www.inftel.com.mx")
                }
            }
            scm {
                connection.set("scm:git:https://github.com/santoszv/codegen-api")
                developerConnection.set("scm:git:https://github.com/santoszv/codegen-api")
                url.set("https://github.com/santoszv/codegen-api")
            }
        }
        signing.sign(this)
    }

    publications.named<MavenPublication>("kotlinMultiplatform") {
        artifact(extraSources)
        artifact(fakeJavadoc)
    }

    publications.named<MavenPublication>("js") {
        artifact(fakeJavadoc)
    }

    publications.named<MavenPublication>("jvm") {
        artifact(fakeJavadoc)
    }

    publications.named<MavenPublication>("metadata") {
        artifact(fakeJavadoc)
    }

    repositories {
        maven {
            setUrl(file("$projectDir/build/repo"))
        }
    }
}

signing {
    useGpgCmd()
}