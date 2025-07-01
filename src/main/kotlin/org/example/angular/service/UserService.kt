package org.example.angular.service

import org.example.angular.dto.user.request.CreateUserDto
import org.example.angular.dto.user.request.UpdateUserDto
import org.example.angular.dto.user.response.UserDto
import org.example.angular.model.User
import org.example.angular.repo.UserRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
data class UserService(
    val userRepository: UserRepository,
) {
    @Transactional(readOnly = true)
    fun getUserById(id: Long): User? =
        userRepository.findUserById(id)
            ?: throw IllegalArgumentException("Такого пользователя с id: $id нет")

    @Transactional(readOnly = true)
    fun getUserByUsername(username: String): UserDto? {
        val findUser =
            userRepository.findUserByUsername(username)
                ?: throw IllegalArgumentException("User with name $username not found")

        return UserDto(
            id = findUser.id,
            username = findUser.username,
            fullName = findUser.fullName,
            email = findUser.email,
        )
    }

    @Transactional
    fun createUser(dto: CreateUserDto) {
        userRepository.save(
            User(
                username = dto.username,
                fullName = dto.fullName,
                email = dto.email,
            ),
        )
    }

    @Transactional
    fun updateUserById(
        id: Long,
        dto: UpdateUserDto,
    ) {
        val findUser = getUserById(id)

        findUser?.username = dto.username
        findUser?.fullName = dto.fullName
        findUser?.email = dto.email
    }

    @Transactional
    fun deleteUserById(id: Long) {
        val findUser = getUserById(id)

        userRepository.delete(findUser!!)
    }
}
