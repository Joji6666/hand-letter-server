package com.hand_letter.project.service

import com.hand_letter.project.dto.ProjectContentRequestDTO
import com.hand_letter.project.dto.ProjectContentResponseDTO
import com.hand_letter.project.dto.ProjectRequestDTO
import com.hand_letter.project.dto.ProjectResponseDTO
import org.springframework.stereotype.Service

@Service
interface ProjectService {
    fun createProject(projectRequestDTO: ProjectRequestDTO): Long
    fun updateProject(projectId: Long, projectRequestDTO: ProjectRequestDTO): Long
    fun saveProjectContent(projectId: Long, projectContentRequestDTOS: List<ProjectContentRequestDTO>)
    fun getProjectsByUserId(userId: Long): List<ProjectResponseDTO>
    fun getProjectContentsByProjectId(projectId: Long): List<ProjectContentResponseDTO>
}
