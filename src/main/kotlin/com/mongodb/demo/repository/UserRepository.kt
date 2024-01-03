package com.mongodb.demo.repository

import com.mongodb.demo.model.User
import org.springframework.data.domain.Sort
import org.springframework.data.mongodb.core.query.BasicQuery
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.Query
import org.springframework.stereotype.Repository
import org.springframework.data.mongodb.core.MongoTemplate


@Repository
interface UserRepository: MongoRepository<User, String> {

    @Query("{ 'firstName' : ?0, 'age' : { \$gt: ?1 } }")
    fun findByFirstNameAndAgeGreaterThan(firstName: String, age: Int): List<User>

    @Query("{ 'age' : { \$gte: ?0, \$lte: ?1 } }")
    fun findByAgeRange(minAge: Int, maxAge: Int): List<User>

    @Query("{ 'age' : ?0 }")
    fun findByAgeOrderByLastName(age: Int, sort: Sort = Sort.by(Sort.Direction.DESC, "lastName")): List<User>

}
