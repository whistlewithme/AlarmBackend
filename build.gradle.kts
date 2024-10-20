plugins {
    application
    kotlin("jvm") version "2.0.20"
    id("org.jetbrains.kotlin.kapt") version "1.9.10"
}

group = "org.whistlewithme"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

application {
    mainClass.set("com.whistlewithme.alarm.MainKt") // Main entry point
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation("org.glassfish.grizzly:grizzly-http-server:2.4.4")
    implementation("org.glassfish.jersey.containers:jersey-container-grizzly2-http:2.35")
    implementation("org.glassfish.jersey.inject:jersey-hk2:2.35")
    implementation("org.mongodb:mongodb-driver-sync:4.8.0")
    implementation("com.google.dagger:dagger:2.50")
    kapt("com.google.dagger:dagger-compiler:2.50")
    implementation("org.slf4j:slf4j-api:1.7.30")
    implementation("javax.xml.parsers:jaxp-api:1.4.5")
    implementation("org.jetbrains.kotlin:kotlin-stdlib")
    implementation("org.glassfish.jersey.containers:jersey-container-servlet-core:2.35")
    implementation("org.glassfish.jersey.media:jersey-media-json-jackson:2.35")

    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}