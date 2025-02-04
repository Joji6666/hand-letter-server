package com.hand_letter.project.dto

import java.time.LocalDateTime

data class ProjectResponseDTO(
    val id: Long,
    val url: String,
    val endDate: LocalDateTime,
    val effect: String,
    val createUserId: Long
)
