package com.hand_letter.project.domain.model

import jakarta.persistence.*

@Entity
@Table(name = "project_content")
data class ProjectContent(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    val id: Long = 0,

    @ManyToOne(fetch = FetchType.LAZY) // 다대일 관계 매핑
    @JoinColumn(name = "project_id", referencedColumnName = "id", nullable = false) // 외래 키 매핑
    val project: Project, // 연관된 Project 엔티티

    @Column(name = "type")
    val type: String,

    @Column(name = "content")
    val content: String,

    @Column(name = "sub_content")
    val subContent: String,

    @Column(name = "background_color")
    val backgroundColor: String,

    @Column(name = "font")
    val font: String,

    @Column(name = "font_size")
    val fontSize: String,

    @Column(name = "animation")
    val animation: String

)
