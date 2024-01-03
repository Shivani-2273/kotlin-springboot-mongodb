package com.mongodb.demo.service

import com.mongodb.demo.exception.UserNotFoundException
import com.mongodb.demo.model.User
import com.mongodb.demo.repository.UserRepository
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.stereotype.Service

@Service
class UserService(private val userRepository: UserRepository, private val mongoTemplate: MongoTemplate) {

    fun findAll(): List<User> = userRepository.findAll()

    fun findById(id: String): User? = userRepository.findById(id).orElseThrow{UserNotFoundException("User not found with id: $id")}

    fun save(user: User): User = userRepository.save(user)

    fun update(id: String, updatedUser: User): User? {
        val existingUser = findById(id)

        existingUser?.apply {
            firstName = updatedUser.firstName
            lastName = updatedUser.lastName
            email = updatedUser.email
            age = updatedUser.age
        }

        return existingUser?.let { userRepository.save(it) }
    }


    fun delete(id: String) {
        if (!userRepository.existsById(id)) {
            throw UserNotFoundException("User not found with id: $id")
        }
        userRepository.deleteById(id)
    }

    //other methods

    fun getUsersByFirstNameAndAgeGreaterThan(firstName: String, age: Int): List<User> {
        val users = userRepository.findByFirstNameAndAgeGreaterThan(firstName, age)
        if (users.isEmpty()) {
            throw UserNotFoundException("Users with first name $firstName and age greater than $age not found")
        }
        return users
    }

    fun getUsersByAgeRange(minAge: Int, maxAge: Int): List<User> {
        val users = userRepository.findByAgeRange(minAge, maxAge)
        if (users.isEmpty()) {
            throw UserNotFoundException("Users within age range $minAge - $maxAge not found")
        }
        return users
    }

    fun getUsersByAgeOrderByLastName(age: Int): List<User> {
        val users = userRepository.findByAgeOrderByLastName(age)
        if (users.isEmpty()) {
            throw UserNotFoundException("Users with age $age not found for sorting by last name")
        }
        return users
    }

    //criteria
    fun findUsersByCriteria(firstName: String?, lastName: String?, minAge: Int?, maxAge: Int?): List<User> {
        val query = Query()
        val criteriaList = mutableListOf<Criteria>()

        firstName?.let { criteriaList.add(Criteria.where("firstName").`is`(it)) }
        lastName?.let { criteriaList.add(Criteria.where("lastName").`is`(it)) }
        minAge?.let { criteriaList.add(Criteria.where("age").gte(it)) }
        maxAge?.let { criteriaList.add(Criteria.where("age").lte(it)) }

        if (criteriaList.isNotEmpty()) {
            query.addCriteria(Criteria().andOperator(*criteriaList.toTypedArray()))
        }

        val users = mongoTemplate.find(query, User::class.java)
        if (users.isEmpty()) {
            throw UserNotFoundException("No users found matching the specified criteria.")
        }
        return users
    }






}
