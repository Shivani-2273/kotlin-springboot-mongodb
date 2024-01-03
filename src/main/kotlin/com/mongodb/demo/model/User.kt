package com.mongodb.demo.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "users")
class User {

    @Id
    var id: String? = null

    var firstName: String = ""

    var lastName: String = ""

    var email: String = ""

    var age: Int = 0
}
