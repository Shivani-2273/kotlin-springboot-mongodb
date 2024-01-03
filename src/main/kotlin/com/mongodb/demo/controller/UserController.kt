package com.mongodb.demo.controller

import com.mongodb.demo.model.User
import com.mongodb.demo.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/users")
class UserController(private val userService: UserService) {

    @GetMapping
    fun findAllUsers(): List<User> = userService.findAll()

    @GetMapping("/{id}")
    fun findUserById(@PathVariable id: String): ResponseEntity<User> {
        val user = userService.findById(id)
        return ResponseEntity.ok(user)
    }

    @PostMapping
    fun saveUser(@RequestBody user: User): User = userService.save(user);

    @PutMapping("/{id}")
    fun updateUser(@PathVariable id: String, @RequestBody updatedUser: User): ResponseEntity<User> {
        val user = userService.update(id, updatedUser)
        return ResponseEntity.ok(user)
    }


    @DeleteMapping("/{id}")
    fun deleteUser(@PathVariable id: String):  ResponseEntity<String>{
        userService.delete(id)
        return ResponseEntity.ok("User with id: $id deleted successfully")
    }

    //other methods

    @GetMapping("/name-age/{firstName}/{age}")
    fun getUsersByFirstNameAndAgeGreaterThan(@PathVariable firstName: String, @PathVariable age: Int): ResponseEntity<List<User>> {
        val users = userService.getUsersByFirstNameAndAgeGreaterThan(firstName, age)
        return ResponseEntity.ok(users)
    }

    @GetMapping("/age-range/{minAge}/{maxAge}")
    fun getUsersByAgeRange(@PathVariable minAge: Int, @PathVariable maxAge: Int): ResponseEntity<List<User>> {
        val users = userService.getUsersByAgeRange(minAge, maxAge)
        return ResponseEntity.ok(users)
    }

    @GetMapping("/age-lastname/{age}")
    fun getUsersByAgeOrderByLastName(@PathVariable age: Int): ResponseEntity<List<User>> {
        val users = userService.getUsersByAgeOrderByLastName(age)
        return ResponseEntity.ok(users)
    }

    @GetMapping("/criteria")
    fun getUsersByCriteria(
        @RequestParam(required = false) firstName: String?,
        @RequestParam(required = false) lastName: String?,
        @RequestParam(required = false) minAge: Int?,
        @RequestParam(required = false) maxAge: Int?
    ): ResponseEntity<List<User>> {
        val users = userService.findUsersByCriteria(firstName, lastName, minAge, maxAge)
        return ResponseEntity.ok(users)
    }
}
