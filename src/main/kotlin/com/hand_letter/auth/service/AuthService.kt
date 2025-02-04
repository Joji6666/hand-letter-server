package com.hand_letter.auth.service

import com.ez_studio.config.jwt.JwtTokenProvider
import com.hand_letter.auth.domain.model.User
import com.hand_letter.auth.domain.repository.UserRepository
import com.hand_letter.auth.dto.LoginSuccessDTO
import com.hand_letter.auth.exception.UserNotFoundException
import org.springframework.stereotype.Service

@Service
class AuthService(
    private val userRepository: UserRepository,
    private val jwtTokenProvider: JwtTokenProvider
) {

    fun createUser(userId: String, password: String): User {
        val newUser = User(userId = userId, password = password)
        return userRepository.save(newUser)
    }

    fun login(userId: String, password: String): LoginSuccessDTO {
        val targetUser = userRepository.findByUserId(userId) ?: throw UserNotFoundException("User with ID $userId not found")
        return LoginSuccessDTO(user = targetUser, token = jwtTokenProvider.createToken(targetUser.userId))
    }
}
