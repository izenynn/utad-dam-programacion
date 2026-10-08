/*
 * build-logic/conventions/build.gradle.kts
 *
 * Enables precompiled script plugins (kotlin-dsl) for the conventions.
 */

plugins {
    `kotlin-dsl`
}

repositories {
    gradlePluginPortal()
}
