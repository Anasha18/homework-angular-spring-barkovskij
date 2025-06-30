package org.example.angular.service

import org.example.angular.dto.user.CreateUserDto
import org.example.angular.model.User
import org.example.angular.repo.UserRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
data class UserService(
    val userRepository: UserRepository,
) {
    @Transactional(readOnly = true)
    fun getUserByUsername(username: String): User? = userRepository.findByUsername(username)

    @Transactional
    fun createUser(dto: CreateUserDto): User? =
        userRepository.save(
            User(
                username = dto.username,
                fullName = dto.fullName,
                email = dto.email,
            ),
        )
}
