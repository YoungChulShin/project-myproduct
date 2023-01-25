group = "project.msa.composite.product"
version = "0.0.1-SNAPSHOT"

apply(plugin="org.springframework.boot")

dependencies {
    implementation(project(":model"))
    implementation(project(":util"))

    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("io.springfox:springfox-boot-starter:3.0.0")
    implementation("io.springfox:springfox-swagger-ui:3.0.0")
}