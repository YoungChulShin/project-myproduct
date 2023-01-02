package project.msa.myproduct

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MyProductApplication

fun main(args: Array<String>) {
    runApplication<MyProductApplication>(*args)
}
