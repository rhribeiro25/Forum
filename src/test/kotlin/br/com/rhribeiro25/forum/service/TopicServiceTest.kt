package br.com.rhribeiro25.forum.service

import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import java.util.*

class TopicServiceTest {
    private val topico = br.com.rhribeiro25.forum.model.TopicTest.build()
    private val topicoView = br.com.rhribeiro25.forum.model.TopicViewTest.build()

    private val pages = PageImpl(listOf(topico))

    private val pageable: Pageable = mockk()
    private val topicViewMapper: br.com.rhribeiro25.forum.mapper.TopicViewMapper = mockk()
    private val topicFormMapper: br.com.rhribeiro25.forum.mapper.TopicFormMapper = mockk()

    private val topicRepository: br.com.rhribeiro25.forum.repository.TopicRepository = mockk {
        every { findAll(pageable) } returns pages
        every { findByCursoNome(any(), any()) } returns pages
    }

    private val topicService = br.com.rhribeiro25.forum.service.TopicService(
        repository = topicRepository,
        topicViewMapper = topicViewMapper,
        topicFormMapper = topicFormMapper,
        notFoundMessage = "Topico nao encontrado!"
    )

    @Test
    fun `deve listar topico por nome do curso`() {
        val slot = slot<br.com.rhribeiro25.forum.model.Topico>()
        every { topicViewMapper.map(capture(slot)) } returns topicoView

        topicService.listar("Kotlin Basico", pageable)

        verify(exactly = 0) { topicRepository.findAll(pageable) }
        verify(exactly = 1) { topicRepository.findByCursoNome(any(), any()) }
        verify(exactly = 1) { topicViewMapper.map(any()) }

        assertThat(slot.captured.titulo).isEqualTo(topico.titulo)
        assertThat(slot.captured.mensagem).isEqualTo(topico.mensagem)
        assertThat(slot.captured.status).isEqualTo(topico.status)
    }

    @Test
    fun `deve listar todos os topicos quando nome do curso for nulo`() {
        val slot = slot<br.com.rhribeiro25.forum.model.Topico>()
        every { topicViewMapper.map(capture(slot)) } returns topicoView

        topicService.listar(null, pageable)

        verify(exactly = 1) { topicRepository.findAll(pageable) }
        verify(exactly = 0) { topicRepository.findByCursoNome(any(), any()) }
        verify(exactly = 1) { topicViewMapper.map(any()) }

        assertThat(slot.captured.titulo).isEqualTo(topico.titulo)
        assertThat(slot.captured.mensagem).isEqualTo(topico.mensagem)
        assertThat(slot.captured.status).isEqualTo(topico.status)
    }

    @Test
    fun `deve lancar excecao se nao achar topico por id`() {
        every { topicRepository.findById(any()) } returns Optional.empty()

        val actual = assertThrows<br.com.rhribeiro25.forum.exception.NotFoundException> {
            topicService.buscarPorId(2)
        }

        assertThat(actual.message).isEqualTo("Topico nao encontrado!")
    }
}