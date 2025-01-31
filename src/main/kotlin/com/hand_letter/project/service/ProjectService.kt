package com.hand_letter.project.service

import com.hand_letter.project.domain.model.Project
import com.hand_letter.project.domain.repository.ProjectRepository
import com.hand_letter.project.dto.ProjectRequestDTO
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class ProjectService(
    private val projectRepository: ProjectRepository
) {
    fun saveProject(projectRequestDTO: ProjectRequestDTO): Long {
        val newProject = projectRepository.save(
            Project(
                createUserId = projectRequestDTO.createUserId,
                effect = projectRequestDTO.effect,
                url = projectRequestDTO.url,
                endDate = LocalDateTime.now()
            )
        )

        return newProject.id
    }
}
