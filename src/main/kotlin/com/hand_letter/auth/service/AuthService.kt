package com.hand_letter.auth.service

import com.hand_letter.auth.domain.model.User
import com.hand_letter.auth.domain.repository.UserRepository
import com.hand_letter.auth.exception.UserNotFoundException
import org.springframework.stereotype.Service

@Service
class AuthService(
    private  val userRepository: UserRepository
) {

    fun createUser(userId:String,password:String):User{
        val newUser = User(userId = userId, password = password)
        return  userRepository.save(newUser)
    }


    fun login(userId:String,password:String):User{
        val targetUser = userRepository.findByUserId(userId)     ?: throw UserNotFoundException("User with ID $userId not found")


        return  targetUser
    }

}