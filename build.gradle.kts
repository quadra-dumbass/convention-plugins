plugins {
    `kotlin-dsl`
    `maven-publish`
}

group = "com.quadra.convention"
version = "1.0.0"

repositories {
    mavenCentral()
    gradlePluginPortal()
}

kotlin {
    jvmToolchain(17)
}

dependencies {
    implementation("org.springframework.boot:spring-boot-gradle-plugin:3.5.3")
    implementation("io.spring.gradle:dependency-management-plugin:1.1.7")
}

publishing {
    repositories {
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/quadra-dumbass/convention-plugins")
            credentials {
                username = project.findProperty("gpr.user") as String? ?: System.getenv("GITHUB_ACTOR")
                password = project.findProperty("gpr.key") as String? ?: System.getenv("GITHUB_TOKEN")
            }
        }
    }
}
