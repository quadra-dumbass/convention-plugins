plugins {
    `java-library`
    `maven-publish`
    id("com.quadra.java-conventions")
}

group = "com.quadra.common"

publishing {
    repositories {
        val usernameProvider = project.providers.gradleProperty("gpr.module.user")
            .orElse(project.provider { System.getenv("GITHUB_MODULE_USER") })
        val passwordProvider = project.providers.gradleProperty("gpr.module.token")
            .orElse(project.provider { System.getenv("GITHUB_MODULE_TOKEN") })
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/quadra-dumbass/common-module")
            credentials {
                username = usernameProvider.get()
                password = passwordProvider.get()
            }
        }
    }

    publications {
        val groupIdProvider = project.providers.gradleProperty("groupId")
            .orElse(project.provider { project.group.toString() })
        val artifactIdProvider = project.providers.gradleProperty("artifactId")
            .orElse(project.provider { project.name })
        val versionProvider = project.providers.gradleProperty("version")
            .orElse(project.provider { project.version.toString() })
        create<MavenPublication>("gpr") {
            from(components["java"])
            groupId = groupIdProvider.get()
            artifactId = artifactIdProvider.get()
            version = versionProvider.get()
        }
    }
}