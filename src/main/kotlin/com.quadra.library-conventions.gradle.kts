plugins {
    `java-library`
    `maven-publish`
    id("com.quadra.java-conventions")
}

group = "com.quadra.common"

publishing {
    repositories {
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/quadra-dumbass/common-module")
            credentials {
                username = project.findProperty("gpr.module.user") as String? ?: System.getenv("GITHUB_MODULE_USER")
                password = project.findProperty("gpr.module.token") as String? ?: System.getenv("GITHUB_MODULE_TOKEN")
            }
        }
    }

    publications {
        create<MavenPublication>("gpr") {
            from(components["java"])
            groupId = project.findProperty("groupId") as String
            artifactId = project.findProperty("artifactId") as String? ?: project.name
            version = project.findProperty("version") as String
        }
    }
}