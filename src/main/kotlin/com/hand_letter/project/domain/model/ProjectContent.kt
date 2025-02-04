package com.hand_letter.project.domain.model

import com.hand_letter.project.dto.ProjectContentRequestDTO
import com.hand_letter.project.dto.ProjectContentResponseDTO
import jakarta.persistence.*

@Entity
@Table(name = "project_content")
class ProjectContent(
    id: Long = 0,
    project: Project, // 연관된 Project 엔티티
    type: String,
    content: String,
    subContent: String,
    backgroundColor: String,
    font: String,
    fontSize: String,
    animation: String
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    val id: Long = id

    @ManyToOne(fetch = FetchType.LAZY) // 다대일 관계 매핑
    @JoinColumn(name = "project_id", referencedColumnName = "id", nullable = false) // 외래 키 매핑
    var project: Project = project // 연관된 Project 엔티티
        private set

    @Column(name = "type")
    var type: String = type
        private set

    @Column(name = "content")
    var content: String = content
        private set

    @Column(name = "sub_content")
    var subContent: String = subContent
        private set

    @Column(name = "background_color")
    var backgroundColor: String = backgroundColor
        private set

    @Column(name = "font")
    var font: String = font
        private set

    @Column(name = "font_size")
    var fontSize: String = fontSize
        private set

    @Column(name = "animation")
    var animation: String = animation
        private set

    fun updateProjectContent(projectContentRequestDTO: ProjectContentRequestDTO) {
        this.type = projectContentRequestDTO.type
        this.content = projectContentRequestDTO.content
        this.subContent = projectContentRequestDTO.subContent
        this.backgroundColor = projectContentRequestDTO.backgroundColor
        this.font = projectContentRequestDTO.font
        this.fontSize = projectContentRequestDTO.fontSize
        this.animation = projectContentRequestDTO.animation
    }

    fun convertToDTO(projectContent: ProjectContent): ProjectContentResponseDTO {
        return ProjectContentResponseDTO(
            id = projectContent.id,
            type = projectContent.type,
            content = projectContent.content,
            subContent = projectContent.subContent,
            backgroundColor = projectContent.backgroundColor,
            font = projectContent.font,
            fontSize = projectContent.fontSize,
            animation = projectContent.animation

        )
    }
}
