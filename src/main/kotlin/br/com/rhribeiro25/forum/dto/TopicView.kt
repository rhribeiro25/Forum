package br.com.rhribeiro25.forum.dto

import br.com.rhribeiro25.forum.model.TopicStatus
import java.io.Serializable
import java.time.LocalDate
import java.time.LocalDateTime

data class TopicView(
    val id: Long?,
    val title: String,
    val message: String,
    val status: TopicStatus,
    val createDate: LocalDateTime,
    val updateDate: LocalDate?
) : Serializable
