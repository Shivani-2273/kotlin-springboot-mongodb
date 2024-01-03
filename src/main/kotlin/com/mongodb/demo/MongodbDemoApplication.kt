package com.mongodb.demo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MongodbDemoApplication

fun main(args: Array<String>) {
	runApplication<MongodbDemoApplication>(*args)
}
