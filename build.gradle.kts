plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.10.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.10.0")
    implementation("org.seleniumhq.selenium:selenium-java:4.15.0")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.15.3")
    implementation("org.mongodb:mongodb-driver-sync:4.2.3")

}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}