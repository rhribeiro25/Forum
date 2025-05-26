package br.com.rhribeiro25.forum.model

import java.io.Serializable
import java.time.LocalDate
import java.time.LocalDateTime
import javax.persistence.*

@Entity
data class Topic(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    var title: String,
    var message: String,
    val createDate: LocalDateTime = LocalDateTime.now(),
    @ManyToOne
    val course: Course,
    @ManyToOne
    val author: User,
    @Enumerated(value = EnumType.STRING)
    val status: TopicStatus = TopicStatus.NOT_ANSWERED,
    @OneToMany(mappedBy = "topic")
    val responses: List<Response> = ArrayList(),
    val updateDate: LocalDateTime? = LocalDateTime.now(),
) : Serializable