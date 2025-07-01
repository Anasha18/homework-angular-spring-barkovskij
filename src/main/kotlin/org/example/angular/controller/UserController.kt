@file:Suppress("ktlint:standard:no-wildcard-imports")

package org.example.angular.controller

import org.example.angular.dto.user.request.CreateUserDto
import org.example.angular.dto.user.request.UpdateUserDto
import org.example.angular.dto.user.response.UserDto
import org.example.angular.service.UserService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/users")
@CrossOrigin(
    origins = ["*"],
    allowedHeaders = ["*"],
)
data class UserController(
    val userService: UserService,
) {
    @PostMapping("/create")
    fun createUser(
        @RequestBody dto: CreateUserDto,
    ) = userService.createUser(dto = dto)

    @GetMapping("/{username}")
    fun getUserByUsername(
        @PathVariable username: String,
    ): UserDto? = userService.getUserByUsername(username = username)

    @DeleteMapping("/delete/{id}")
    fun deleteById(
        @PathVariable id: Long,
    ) = userService.deleteUserById(id = id)

    @PutMapping("/update/{id}")
    fun updateById(
        @PathVariable id: Long,
        @RequestBody dto: UpdateUserDto,
    ) = userService.updateUserById(id = id, dto = dto)
}
