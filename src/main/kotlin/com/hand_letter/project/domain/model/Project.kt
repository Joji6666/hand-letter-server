package com.hand_letter.project.domain.model

import jakarta.persistence.*
import org.springframework.data.annotation.CreatedDate
import java.time.LocalDateTime

@Entity
@Table(name = "project")
data class Project(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    val id: Long = 0,

    @Column(name = "url", unique = true)
    val url: String,

    @Column(name = "create_user_id")
    val createUserId: Long,

    @Column(name = "effect")
    val effect: String,

    @Column(name = "end_date")
    val endDate: LocalDateTime,

    @CreatedDate
    @Column(name = "create_date")
    val createDate: LocalDateTime = LocalDateTime.now()

)
