package br.com.rhribeiro25.forum.model

import java.time.LocalDateTime
import javax.persistence.*

@Entity
data class Response(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long? = null,
    val mensagem: String,
    val dataCriacao: LocalDateTime = LocalDateTime.now(),
    @ManyToOne
        val autor: br.com.rhribeiro25.forum.model.User,
    @ManyToOne
        val topico: br.com.rhribeiro25.forum.model.Topico,
    val solucao: Boolean
)
