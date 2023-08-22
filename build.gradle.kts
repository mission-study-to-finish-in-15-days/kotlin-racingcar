plugins {
    kotlin("jvm") version "1.8.10"
}

group = "com.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(group="org.junit.jupiter", name="junit-jupiter", version = "5.8.2")
    testImplementation(group="org.assertj", name="assertj-core", version="3.22.0")
    testImplementation(group="io.kotest", name="kotest-runner-junit5", version="5.6.2")
}

tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "11"
    }
    compileTestKotlin {
        kotlinOptions.jvmTarget = "11"
    }
    test {
        useJUnitPlatform()
    }
}
