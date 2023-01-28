group = "project.msa.core.review"
version = "0.0.1-SNAPSHOT"

apply(plugin = "org.springframework.boot")

allOpen {
    annotation("javax.persistence.Entity")
}

dependencies {
    implementation(project(":model"))
    implementation(project(":util"))

    implementation("org.springframework.boot:spring-boot-starter-web")

    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("mysql:mysql-connector-java")
    testImplementation("com.h2database:h2")
}