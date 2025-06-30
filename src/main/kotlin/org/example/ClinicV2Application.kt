package org.example

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ClinicV2Application

fun main(args: Array<String>) {
    runApplication<ClinicV2Application>(*args)
}
