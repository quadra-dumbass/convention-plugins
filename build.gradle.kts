plugins {
    `kotlin-dsl`
    `maven-publish`
}

group = "com.quadra"
version = "1.0.2"

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
                username = project.findProperty("gpr.plugin.user") as String? ?: System.getenv("GITHUB_PLUGIN_USER")
                password = project.findProperty("gpr.plugin.token") as String? ?: System.getenv("GITHUB_PLUGIN_TOKEN")
            }
        }
    }
}
