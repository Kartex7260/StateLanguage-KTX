plugins {
    kotlin("jvm") version "1.9.21"
}

group = "kanti.sl"
version = "1.0.0"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.jetbrains.kotlin:kotlin-test")
    implementation("org.jetbrains.kotlin:kotlin-reflect:1.9.21")
    implementation(files("libs/kanti.sl.jar"))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(20)
}