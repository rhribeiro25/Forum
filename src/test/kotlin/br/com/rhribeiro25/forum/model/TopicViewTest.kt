package br.com.rhribeiro25.forum.model

import java.time.LocalDate
import java.time.LocalDateTime

object TopicViewTest {
    fun build() = br.com.rhribeiro25.forum.dto.TopicoView(
        id = 1,
        titulo = "Kotlin Basico",
        mensagem = "Aprendendo kotlin basico",
        status = br.com.rhribeiro25.forum.model.TopicStatus.NAO_RESPONDIDO,
        dataCriacao = LocalDateTime.now(),
        dataAlteracao = LocalDate.now()
    )
}