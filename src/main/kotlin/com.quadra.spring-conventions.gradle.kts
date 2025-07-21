plugins {
    id("com.quadra.java-conventions")
    id("org.springframework.boot")
    id("io.spring.dependency-management")
}

extra["springCloudVersion"] = "2025.0.0"

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

dependencyManagement {
    imports {
        mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
    }
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-validation")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}