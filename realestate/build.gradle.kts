plugins {
	java
	id("org.springframework.boot") version "3.3.6"
	id("io.spring.dependency-management") version "1.1.6"
}

group = "vn.uit"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")

	implementation("org.springframework.boot:spring-boot-starter-security")

  implementation("me.paulschwarz:spring-dotenv:4.0.0")


	implementation("org.springframework.boot:spring-boot-starter-web")
	// Tomcat Jasper for JSP support
	implementation ("org.apache.tomcat.embed:tomcat-embed-jasper:10.1.5") // Adjust the version as needed

	// Jakarta JSTL API for JSTL support
	implementation ("jakarta.servlet.jsp.jstl:jakarta.servlet.jsp.jstl-api:3.0.0") // Adjust the version as needed

	// Glassfish JSTL implementation
	implementation ("org.glassfish.web:jakarta.servlet.jsp.jstl:3.0.1") // Adjust the version as needed

	implementation ("org.springframework.boot:spring-boot-starter-validation")
	implementation("org.springframework.session:spring-session-jdbc")
	compileOnly("org.projectlombok:lombok")
	developmentOnly("org.springframework.boot:spring-boot-devtools")
	runtimeOnly("com.mysql:mysql-connector-j")
  runtimeOnly("org.mariadb.jdbc:mariadb-java-client")
	annotationProcessor("org.projectlombok:lombok")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.springframework.security:spring-security-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.withType<Test> {
	useJUnitPlatform()
}
