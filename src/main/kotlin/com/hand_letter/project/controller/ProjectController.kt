package com.hand_letter.project.controller

import com.hand_letter.project.dto.ProjectRequestDTO
import com.hand_letter.project.dto.ProjectResponseDTO
import com.hand_letter.project.service.ProjectService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/project")
class ProjectController(
    private val projectService: ProjectService
) {

    @PostMapping
    fun createProject(@RequestBody projectRequestDTO: ProjectRequestDTO): Long {
        return projectService.createProject(projectRequestDTO)
    }

    @PutMapping("/{projectId}")
    fun updateProject(
        @PathVariable projectId: Long,
        @RequestBody projectRequestDTO: ProjectRequestDTO
    ): Long {
        return projectService.updateProject(projectId, projectRequestDTO)
    }

    @GetMapping("/{userId}")
    fun getProjects(@PathVariable userId: Long): List<ProjectResponseDTO> {
        return projectService.getProjectsByUserId(userId)
    }
}
