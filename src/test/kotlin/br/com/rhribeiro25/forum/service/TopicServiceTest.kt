//package br.com.rhribeiro25.forum.service
//
//import br.com.rhribeiro25.forum.exception.NotFoundException
//import br.com.rhribeiro25.forum.mapper.TopicFormMapper
//import br.com.rhribeiro25.forum.mapper.TopicViewMapper
//import br.com.rhribeiro25.forum.model.Topic
//import br.com.rhribeiro25.forum.model.TopicTest
//import br.com.rhribeiro25.forum.model.TopicViewTest
//import br.com.rhribeiro25.forum.repository.TopicRepository
//import io.mockk.every
//import io.mockk.mockk
//import io.mockk.slot
//import io.mockk.verify
//import org.assertj.core.api.Assertions.assertThat
//import org.junit.jupiter.api.Test
//import org.junit.jupiter.api.assertThrows
//import org.springframework.data.domain.PageImpl
//import org.springframework.data.domain.Pageable
//import java.util.*
//
//class TopicServiceTest {
//    private val topic = TopicTest.build()
//    private val topicView = TopicViewTest.build()
//
//    private val pages = PageImpl(listOf(topic))
//
//    private val pageable: Pageable = mockk()
//    private val topicViewMapper: TopicViewMapper = mockk()
//    private val topicFormMapper: TopicFormMapper = mockk()
//
//    private val topicRepository: TopicRepository = mockk {
//        every { findAll(pageable) } returns pages
//        every { findByCourseName(any(), any()) } returns pages
//    }
//
//    private val topicService = TopicService(
//        repository = topicRepository,
//        topicViewMapper = topicViewMapper,
//        topicFormMapper = topicFormMapper,
//        notFoundMessage = "Topic nao encontrado!"
//    )
//
//    @Test
//    fun `deve listar topico por nome do curso`() {
//        val slot = slot<Topic>()
//        every { topicViewMapper.map(capture(slot)) } returns topicView
//
//        topicService.list("Kotlin Basico", pageable)
//
//        verify(exactly = 0) { topicRepository.findAll(pageable) }
//        verify(exactly = 1) { topicRepository.findByCourseName(any(), any()) }
//        verify(exactly = 1) { topicViewMapper.map(any()) }
//
//        assertThat(slot.captured.title).isEqualTo(topic.title)
//        assertThat(slot.captured.message).isEqualTo(topic.message)
//        assertThat(slot.captured.status).isEqualTo(topic.status)
//    }
//
//    @Test
//    fun `deve listar todos os topicos quando nome do curso for nulo`() {
//        val slot = slot<Topic>()
//        every { topicViewMapper.map(capture(slot)) } returns topicView
//
//        topicService.list(null, pageable)
//
//        verify(exactly = 1) { topicRepository.findAll(pageable) }
//        verify(exactly = 0) { topicRepository.findByCourseName(any(), any()) }
//        verify(exactly = 1) { topicViewMapper.map(any()) }
//
//        assertThat(slot.captured.title).isEqualTo(topic.title)
//        assertThat(slot.captured.message).isEqualTo(topic.message)
//        assertThat(slot.captured.status).isEqualTo(topic.status)
//    }
//
//    @Test
//    fun `deve lancar excecao se nao achar topico por id`() {
//        every { topicRepository.findById(any()) } returns Optional.empty()
//
//        val actual = assertThrows<NotFoundException> {
//            topicService.findById(2)
//        }
//
//        assertThat(actual.message).isEqualTo("Topic nao encontrado!")
//    }
//}