package com.hand_letter.project.domain.model

import com.hand_letter.project.dto.ProjectRequestDTO
import com.hand_letter.project.dto.ProjectResponseDTO
import jakarta.persistence.*
import org.springframework.data.annotation.CreatedDate
import java.time.LocalDateTime

@Entity
@Table(name = "project")
class Project(
    id: Long = 0,
    url: String,
    createUserId: Long,
    effect: String,
    endDate: LocalDateTime
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    val id: Long = id

    @Column(name = "url", unique = true)
    var url: String = url
        private set

    @Column(name = "create_user_id")
    val createUserId: Long = createUserId

    @Column(name = "effect")
    var effect: String = effect
        private set

    @Column(name = "end_date")
    var endDate: LocalDateTime = endDate
        private set

    @CreatedDate
    @Column(name = "create_date")
    val createDate: LocalDateTime = LocalDateTime.now()

    fun updateProject(projectRequestDTO: ProjectRequestDTO) {
        this.effect = projectRequestDTO.effect
        this.url = projectRequestDTO.url
    }

    fun convertToDTO(project: Project): ProjectResponseDTO {
        return ProjectResponseDTO(
            id = project.id,
            url = project.url,
            endDate = project.endDate,
            effect = project.effect,
            createUserId = project.createUserId
        )
    }
}
