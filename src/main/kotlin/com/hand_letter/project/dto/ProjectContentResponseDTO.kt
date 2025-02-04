package com.hand_letter.project.dto

data class ProjectContentResponseDTO(
    val id: Long,
    val type: String,
    val content: String,
    val subContent: String,
    val backgroundColor: String,
    val font: String,
    val fontSize: String,
    val animation: String
)
