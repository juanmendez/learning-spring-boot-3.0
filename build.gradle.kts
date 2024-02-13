import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "3.2.2"
	id("io.spring.dependency-management") version "1.1.4"
	kotlin("jvm") version "1.9.22"
	kotlin("plugin.spring") version "1.9.22"
}

group = "com.springboot"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
	mavenCentral()
}

dependencies {
	val springBoot = "org.springframework.boot"

    implementation("$springBoot:spring-boot-starter-mustache")
    implementation("$springBoot:spring-boot-starter-webflux")
    implementation("$springBoot:spring-boot-starter-web")
    implementation("$springBoot:spring-boot-starter-data-jpa")
    implementation("com.h2database:h2")
    implementation("$springBoot:spring-boot-starter-oauth2-client")
    testImplementation("$springBoot:spring-boot-starter-test")
    testImplementation("$springBoot:spring-security-test")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs += "-Xjsr305=strict"
		jvmTarget = "17"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
