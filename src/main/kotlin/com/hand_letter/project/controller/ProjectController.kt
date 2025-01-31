package com.hand_letter.project.controller

import com.hand_letter.project.dto.ProjectRequestDTO
import com.hand_letter.project.service.ProjectService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/project")
class ProjectController(
    private val projectService: ProjectService
) {

    @PostMapping
    fun saveProject(@RequestBody projectRequestDTO: ProjectRequestDTO): Long {
        return projectService.saveProject(projectRequestDTO)
    }
}
