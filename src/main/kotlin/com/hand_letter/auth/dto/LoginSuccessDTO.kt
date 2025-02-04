package com.hand_letter.auth.dto

import com.hand_letter.auth.domain.model.User

data class LoginSuccessDTO(
    val user: User,
    val token: String
)
