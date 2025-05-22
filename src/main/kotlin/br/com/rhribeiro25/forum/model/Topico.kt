package br.com.rhribeiro25.forum.model

import java.io.Serializable
import java.time.LocalDate
import java.time.LocalDateTime
import javax.persistence.*

@Entity
data class Topico(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long? = null,
    var titulo: String,
    var mensagem: String,
    val dataCriacao: LocalDateTime = LocalDateTime.now(),
    @ManyToOne
        val course: br.com.rhribeiro25.forum.model.Course,
    @ManyToOne
        val autor: br.com.rhribeiro25.forum.model.User,
    @Enumerated(value = EnumType.STRING)
        val status: br.com.rhribeiro25.forum.model.TopicStatus = br.com.rhribeiro25.forum.model.TopicStatus.NAO_RESPONDIDO,
    @OneToMany(mappedBy = "topico")
        val responses: List<br.com.rhribeiro25.forum.model.Response> = ArrayList(),
    val dataAlteracao: LocalDate? = null
) : Serializable