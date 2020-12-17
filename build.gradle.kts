group = "mx.com.inftel.codegen"
version = "1.0.0-SNAPSHOT"

repositories {
    mavenCentral()
    jcenter()
}

plugins {
    kotlin("multiplatform") version "1.4.21"
    kotlin("plugin.serialization") version "1.4.21"
    `maven-publish`
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
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.0.1")
            }
        }

        val jvmMain by getting {
            dependencies {
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

publishing {
    repositories {
        maven {
            url = if (project.version.toString().endsWith("-SNAPSHOT")) {
                uri("https://nexus.inftelapps.com/repository/maven-snapshots/")
            } else {
                uri("https://nexus.inftelapps.com/repository/maven-releases/")
            }
            credentials {
                username = properties["inftel.nexus.username"]?.toString()
                password = properties["inftel.nexus.password"]?.toString()
            }
        }
    }
}