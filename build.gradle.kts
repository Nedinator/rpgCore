plugins {
    java
    id("com.gradleup.shadow") version "9.0.0-beta13"
}

group = "me.ned"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()

    maven {
        name = "papermc"
        url = uri("https://repo.papermc.io/repository/maven-public/")
    }
}

dependencies {
    compileOnly(libs.papermc.api)
    implementation(libs.mongodb.driver.core)
    implementation(libs.mongodb.driver.sync)
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(21))
}