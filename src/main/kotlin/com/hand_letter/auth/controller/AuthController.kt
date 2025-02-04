package com.hand_letter.auth.controller

import com.hand_letter.auth.domain.model.User
import com.hand_letter.auth.dto.UserRequestDTO
import com.hand_letter.auth.service.AuthService
import jakarta.servlet.http.HttpServletResponse
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/auth")
class AuthController(private val authService: AuthService) {

    @PostMapping
    fun createUser(@RequestBody userRequestDTO: UserRequestDTO): User {
        return authService.createUser(userRequestDTO.userId, userRequestDTO.password)
    }

    @PostMapping("/login")
    fun login(@RequestBody userRequestDTO: UserRequestDTO, response: HttpServletResponse): User {
        val result = authService.login(userId = userRequestDTO.userId, password = userRequestDTO.password)

        // ✅ JWT를 쿠키에 저장 (httpOnly, Secure 설정)
        response.addHeader("Set-Cookie", "token=${result.token}; Path=/; HttpOnly;")

        return result.user
    }
}
