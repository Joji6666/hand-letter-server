package com.hand_letter.project.service

import com.hand_letter.project.domain.model.ProjectContent
import com.hand_letter.project.domain.repository.ProjectContentRepository
import com.hand_letter.project.domain.repository.ProjectRepository
import com.hand_letter.project.dto.ProjectContentRequestDTO
import com.hand_letter.project.exception.ProjectNotFoundException
import org.springframework.stereotype.Service

@Service
class ProjectContentService(
    private val projectContentRepository: ProjectContentRepository,
    private val projectRepository: ProjectRepository
) {
    fun saveProjectContent(projectId: Long, projectContentRequestDTOS: List<ProjectContentRequestDTO>) {
        val targetProject = projectRepository.findById(projectId).orElseThrow { ProjectNotFoundException("Project with Id $projectId not found") }

        val projectContents = ArrayList<ProjectContent>()

        for (projectContentRequestDTO in projectContentRequestDTOS) {
            projectContents.add(
                ProjectContent(
                    project = targetProject,
                    type = projectContentRequestDTO.type,
                    content = projectContentRequestDTO.content,
                    subContent = projectContentRequestDTO.subContent,
                    backgroundColor = projectContentRequestDTO.backgroundColor,
                    font = projectContentRequestDTO.font,
                    fontSize = projectContentRequestDTO.fontSize,
                    animation = projectContentRequestDTO.animation
                )
            )
        }

        projectContentRepository.saveAll(projectContents)
    }
}
