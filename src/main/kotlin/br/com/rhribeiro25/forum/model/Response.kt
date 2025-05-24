package br.com.rhribeiro25.forum.model

import java.time.LocalDateTime
import javax.persistence.*

@Entity
data class Response(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val message: String,
    val createDate: LocalDateTime = LocalDateTime.now(),
    @ManyToOne
    val author: User,
    @ManyToOne
    val topic: Topic,
    val solution: Boolean
)
