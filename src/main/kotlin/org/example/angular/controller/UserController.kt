@file:Suppress("ktlint:standard:no-wildcard-imports")

package org.example.angular.controller

import org.example.angular.dto.user.CreateUserDto
import org.example.angular.model.User
import org.example.angular.service.UserService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1")
data class UserController(
    val userService: UserService,
) {
    @PostMapping("/createUser")
    fun createUser(
        @RequestBody dto: CreateUserDto,
    ): User? = userService.createUser(dto)

    @GetMapping("/user/{username}")
    fun getUserByUsername(
        @PathVariable username: String,
    ): User? = userService.getUserByUsername(username)
}
