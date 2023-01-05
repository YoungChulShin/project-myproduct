group = "project.msa.composite.product"
version = "0.0.1-SNAPSHOT"

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation(project(":model"))
    implementation(project(":util"))
}