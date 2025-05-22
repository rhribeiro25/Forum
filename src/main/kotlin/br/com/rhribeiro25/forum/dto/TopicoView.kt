package br.com.rhribeiro25.forum.dto

import java.io.Serializable
import java.time.LocalDate
import java.time.LocalDateTime

data class TopicoView(
    val id: Long?,
    val titulo: String,
    val mensagem: String,
    val status: br.com.rhribeiro25.forum.model.TopicStatus,
    val dataCriacao: LocalDateTime,
    val dataAlteracao: LocalDate?
) : Serializable
