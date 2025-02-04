package com.hand_letter.project.service

import com.hand_letter.project.domain.model.Project
import com.hand_letter.project.domain.model.ProjectContent
import com.hand_letter.project.domain.repository.ProjectContentRepository
import com.hand_letter.project.domain.repository.ProjectRepository
import com.hand_letter.project.dto.ProjectContentRequestDTO
import com.hand_letter.project.dto.ProjectContentResponseDTO
import com.hand_letter.project.dto.ProjectRequestDTO
import com.hand_letter.project.dto.ProjectResponseDTO
import com.hand_letter.project.exception.ProjectNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Service
class ProjectServiceImpl(
    private val projectRepository: ProjectRepository,
    private val projectContentRepository: ProjectContentRepository
) : ProjectService {

    @Transactional
    override fun createProject(projectRequestDTO: ProjectRequestDTO): Long {
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

    @Transactional
    override fun updateProject(projectId: Long, projectRequestDTO: ProjectRequestDTO): Long {
        val existingProject = projectRepository.findById(projectId)
            .orElseThrow { ProjectNotFoundException("Project with Id $projectId not found") }

        return projectRepository.save(existingProject).id
    }

    @Transactional
    override fun saveProjectContent(projectId: Long, projectContentRequestDTOS: List<ProjectContentRequestDTO>) {
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

    @Transactional
    override fun getProjectsByUserId(userId: Long): List<ProjectResponseDTO> {
        return projectRepository.findAllByCreateUserId(userId).map { it.convertToDTO(it) }
    }

    @Transactional
    override fun getProjectContentsByProjectId(projectId: Long): List<ProjectContentResponseDTO> {
        return projectContentRepository.findAllByProjectId(projectId).map { it.convertToDTO(it) }
    }
}
