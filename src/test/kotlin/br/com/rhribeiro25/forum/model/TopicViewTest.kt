package br.com.rhribeiro25.forum.model

import java.time.LocalDate
import java.time.LocalDateTime

object TopicViewTest {
    fun build() = br.com.rhribeiro25.forum.dto.TopicView(
        id = 1,
        title = "Kotlin Basico",
        message = "Aprendendo kotlin basico",
        status = TopicStatus.NOT_ANSWERED,
        createDate = LocalDateTime.now(),
        updateDate = LocalDateTime.now()
    )
}