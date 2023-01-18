package core.recommendation

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
@ComponentScan(value = ["core.recommendation", "util"])
class RecommendationServiceApplication

fun main(args: Array<String>) {
    runApplication<RecommendationServiceApplication>(*args)
}