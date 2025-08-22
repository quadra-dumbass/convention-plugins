plugins {
    java
}

group = "com.quadra"

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

configurations.compileOnly {
    extendsFrom(configurations.annotationProcessor.get())
}

val gprUser = providers.gradleProperty("gpr.module.user")
    .orElse(providers.environmentVariable("GITHUB_MODULE_USER"))
    .orNull
val gprToken = providers.gradleProperty("gpr.module.token")
    .orElse(providers.environmentVariable("GITHUB_MODULE_TOKEN"))
    .orNull

repositories {
    mavenCentral()
    if (!gprUser.isNullOrBlank() && !gprToken.isNullOrBlank()) {
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/quadra-dumbass/common-module")
            credentials {
                username = gprUser
                password = gprToken
            }
        }
    }
}

dependencies {

    // lombok
    compileOnly("org.projectlombok:lombok:1.18.38")
    annotationProcessor("org.projectlombok:lombok:1.18.38")
    testCompileOnly("org.projectlombok:lombok:1.18.38")
    testAnnotationProcessor("org.projectlombok:lombok:1.18.38")

    // logging

    // log4j2 to slf4j binding (including slf4j-api)
    implementation("org.apache.logging.log4j:log4j-slf4j2-impl:2.24.3")

    // log4j2 (including log4j-api)
    implementation("org.apache.logging.log4j:log4j-core:2.24.3")

    // log4j JUL Handler, if needed.
    // runtimeOnly("org.apache.logging.log4j:log4j-jul:2.24.3")

    // testing tools below

    // junit
    testImplementation(platform("org.junit:junit-bom:5.12.2"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

    // mockito
    testImplementation("org.mockito:mockito-core:5.17.0")
    testImplementation("org.mockito:mockito-junit-jupiter:5.17.0")

    // assertj
    testImplementation("org.assertj:assertj-core:3.27.3")
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}