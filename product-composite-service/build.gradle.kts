group = "project.msa.composite.product"
version = "0.0.1-SNAPSHOT"

dependencies {
    implementation(project(":model"))
    implementation(project(":util"))

    implementation("org.springframework.boot:spring-boot-starter-web")
    //implementation("org.springframework.boot:spring-boot-starter-webflux")
}