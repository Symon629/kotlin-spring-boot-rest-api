import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "3.2.5"
	id("io.spring.dependency-management") version "1.1.4"
	kotlin("jvm") version "1.9.23"
	kotlin("plugin.spring") version "1.9.23"
	kotlin("plugin.jpa") version "1.9.23"
}

group = "com.respapi"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-validation")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
//	runtimeOnly("com.h2database:h2")
	runtimeOnly("com.mysql:mysql-connector-j")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.springframework.boot:spring-boot-starter-webflux")
	testImplementation("io.mockk:mockk:1.10.4")
	testImplementation("com.ninja-squad:springmockk:3.0.1")
	implementation("io.github.microutils:kotlin-logging-jvm:2.0.11")
	implementation("org.springdoc:springdoc-openapi-ui:1.6.4")
//	implementation("org.springdoc:springdoc-openapi-ui:1.6.12")
//	implementation("io.springfox:springfox-boot-starter:3.0.0")
//	implementation("io.springfox:springfox-swagger-ui:3.0.0")
//	implementation ("javax.servlet:javax.servlet-api:4.0.1")
//	implementation ("org.springdoc:springdoc-openapi-ui:1.6.12")
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

sourceSets{
	test{

//		//any versions before 7.1
//		withConvention(org.jetbrains.kotlin.gradle.plugin.KotlinSourceSet::class){
//			kotlin.setSrcDirs(listOf("/src/test/intg","src/test/unit"))
//		}

		// After gradle 7.1
		java{
			setSrcDirs(listOf("src/test/intg","src/test/unit"))
		}
	}
}