package br.com.rhribeiro25.forum.integration

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.data.domain.PageRequest
import org.testcontainers.junit.jupiter.Testcontainers

@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class TopicRepositoryTest : br.com.rhribeiro25.forum.configuration.DatabaseContainerConfiguration() {

    @Autowired
    private lateinit var topicRepository: br.com.rhribeiro25.forum.repository.TopicRepository

    private val paginacao = PageRequest.of(0,5)
    private val topico = br.com.rhribeiro25.forum.model.TopicTest.build()

    @Test
    fun `deve gerar um relatorio`() {
        topicRepository.save(topico)
        val relatorio = topicRepository.report()

        assertThat(relatorio).isNotNull
        assertThat(relatorio.first()).isExactlyInstanceOf(br.com.rhribeiro25.forum.dto.TopicPerCategoryDto::class.java)
    }

    @Test
    fun `deve buscar um topico por nome`() {
        topicRepository.save(topico)
        val resultado = topicRepository.findByCourseName(courseName = "Kotlin", pageable = paginacao)

        assertThat(resultado.totalElements).isEqualTo(1)
    }
}