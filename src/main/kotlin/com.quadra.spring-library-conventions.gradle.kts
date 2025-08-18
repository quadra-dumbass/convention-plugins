import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    id("com.quadra.library-conventions")
    id("org.springframework.boot")
    id("io.spring.dependency-management")
}

configurations.all {
    resolutionStrategy {
        dependencySubstitution {
            // use spring-boot-starter-log4j2 instead of spring-boot-starter-logging
            substitute(module("org.springframework.boot:spring-boot-starter-logging"))
                .using(module("org.springframework.boot:spring-boot-starter-log4j2:3.5.0"))
                .because("Use Log4j2 instead of Logback")
        }
    }
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-validation")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.named<BootJar>("bootJar") {
    enabled = false
}

tasks.named<Jar>("jar") {
    enabled = true
}