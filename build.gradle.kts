plugins {
    application
    kotlin("jvm") version "1.7.22"
    kotlin("plugin.jpa") version "1.7.22"
    kotlin("plugin.spring") version "1.7.22"
}

group = "com.manning.javapersistence"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation(kotlin("reflect"))
    implementation("org.springframework.data:spring-data-jpa:2.7.0")
    implementation("org.springframework:spring-context:5.3.20")
    implementation("org.springframework:spring-jdbc:5.3.20")
    implementation("org.springframework:spring-orm:5.3.20")
    implementation("org.hibernate:hibernate-core:5.6.9.Final")
    implementation("javax.persistence:javax.persistence-api:2.2")
    implementation("javax.validation:validation-api:2.0.1.Final")
    implementation("org.hibernate:hibernate-validator:6.2.3.Final")
    runtimeOnly("com.mysql:mysql-connector-j:8.4.0")

    testImplementation("org.junit.jupiter:junit-jupiter:5.8.2")
    testImplementation("org.springframework:spring-test:5.3.20")
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(17)
}

application {
    mainClass.set("com.manning.javapersistence.ch08.kotlin.MainKotlinKt")
}

tasks.register<JavaExec>("runJavaMain") {
    group = "application"
    description = "Runs the Java Main class"
    classpath = sourceSets["main"].runtimeClasspath
    mainClass.set("com.manning.javapersistence.ch08.java.MainJava")
}

tasks.register<JavaExec>("runKotlinMain") {
    group = "application"
    description = "Runs the Kotlin Main class"
    classpath = sourceSets["main"].runtimeClasspath
    mainClass.set("com.manning.javapersistence.ch08.kotlin.MainKotlinKt")
}
