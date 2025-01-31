package com.hand_letter.project.domain.repository

import com.hand_letter.project.domain.model.Project
import org.springframework.data.jpa.repository.JpaRepository

interface ProjectRepository : JpaRepository<Project, Long> {
    fun findAllByCreateUserId(userId: Long): List<Project>
}
