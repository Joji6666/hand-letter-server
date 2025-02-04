package com.hand_letter.auth.domain.model

import jakarta.persistence.*
import org.springframework.data.annotation.CreatedDate
import java.time.LocalDateTime

@Entity
@Table(name = "user")
class User(
    id: Long = 0,
    userId: String,
    password: String,
    createDate: LocalDateTime = LocalDateTime.now()
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    val id: Long = id

    @Column(name = "user_id", unique = true)
    val userId: String = userId

    @Column(name = "password")
    var password: String = password
        private set

    @CreatedDate
    @Column(name = "create_date")
    val createDate: LocalDateTime = createDate

    fun updatePassword(password: String) {
        this.password = password
    }
}
