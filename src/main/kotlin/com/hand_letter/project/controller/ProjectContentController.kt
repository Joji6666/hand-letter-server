package com.hand_letter.project.controller

import com.hand_letter.project.dto.ProjectContentRequestDTO
import com.hand_letter.project.dto.ProjectContentResponseDTO
import com.hand_letter.project.service.ProjectService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/project-content")
class ProjectContentController(
    private val projectService: ProjectService
) {

    @PostMapping
    fun saveProjectContent(@RequestBody projectId: Long, projectContentRequestDTOS: List<ProjectContentRequestDTO>) {
        return projectService.saveProjectContent(projectId, projectContentRequestDTOS)
    }

    @GetMapping("/{projectId}")
    fun getProjectContentsByProjectId(@PathVariable projectId: Long): List<ProjectContentResponseDTO> {
        return projectService.getProjectContentsByProjectId(projectId)
    }
}
