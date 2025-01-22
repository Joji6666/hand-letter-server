package com.hand_letter.auth.domain.model

import jakarta.persistence.*
import org.springframework.data.annotation.CreatedDate
import java.time.LocalDateTime

@Entity
@Table(name = "user")
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    val id:Long = 0,

    @Column(name = "user_id", unique = true)
    val userId:String,

    @Column(name = "password")
    val password:String,

    @CreatedDate
    @Column(name = "create_date")
    val createDate:LocalDateTime = LocalDateTime.now()

)