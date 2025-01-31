package com.hand_letter.project.domain.repository

import com.hand_letter.project.domain.model.ProjectContent
import org.springframework.data.jpa.repository.JpaRepository

interface ProjectContentRepository : JpaRepository<ProjectContent, Long> {
    fun findAllByProjectId(projectId: Long): List<ProjectContent>
}
