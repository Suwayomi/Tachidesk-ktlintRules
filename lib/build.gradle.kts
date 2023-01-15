import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.kotlin.jvm)
//    id("ktlint-kotlin-common")
    `java-library`
    `maven-publish`
}

repositories {
    mavenCentral()
}

dependencies {
    compileOnly("com.pinterest.ktlint:ktlint-core:0.48.1")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

java {
    withSourcesJar()
}

publishing {
    val libVersion = "0.0.1"

    publications {
        create<MavenPublication>("defaultJar") {
            groupId = "com.github.suwayomi"
            artifactId = "tachidesk-ktlintrules"
            version = libVersion

            from(components["kotlin"])
        }

        create<MavenPublication>("sourcesJar") {
            groupId = "com.github.suwayomi"
            artifactId = "tachidesk-ktlintrules"
            version = "$libVersion-sources"
            artifact(tasks.named("sourcesJar"))

            from(components["kotlin"])
        }
    }
}