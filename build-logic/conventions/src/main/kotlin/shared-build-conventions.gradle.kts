/*
 * build-logic/conventions/src/main/kotlin/shared-build-conventions.gradle.kts
 *
 * Shared build conventions applied by every ejercicio: Java 25 toolchain,
 * Main as entry point, keyboard input and UTF-8 sources.
 */

plugins {
    application
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(25)
    }
}

application {
    mainClass = "Main"
}

// Allow keyboard input (Scanner) when running with `gradlew :ejercicioNN:run`
tasks.named<JavaExec>("run") {
    standardInput = System.`in`
}

tasks.withType<JavaCompile>().configureEach {
    options.encoding = "UTF-8"
}
