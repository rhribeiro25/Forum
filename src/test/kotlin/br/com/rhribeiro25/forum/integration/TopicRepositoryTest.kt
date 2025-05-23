//package br.com.rhribeiro25.forum.integration
//
//import br.com.rhribeiro25.forum.configuration.DatabaseContainerConfiguration
//import br.com.rhribeiro25.forum.dto.TopicPerCategoryDto
//import br.com.rhribeiro25.forum.model.TopicTest
//import br.com.rhribeiro25.forum.repository.TopicRepository
//import org.assertj.core.api.Assertions.assertThat
//import org.junit.jupiter.api.Test
//import org.springframework.beans.factory.annotation.Autowired
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
//import org.springframework.data.domain.PageRequest
//import org.testcontainers.junit.jupiter.Testcontainers
//
//@Testcontainers
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//class TopicRepositoryTest : DatabaseContainerConfiguration() {
//
//    @Autowired
//    private lateinit var topicRepository: TopicRepository
//
//    private val pageable = PageRequest.of(0,5)
//    private val topic = TopicTest.build()
//
//    @Test
//    fun `deve gerar um relatorio`() {
//        topicRepository.save(topic)
//        val report = topicRepository.findTopicPerCategory()
//
//        assertThat(report).isNotNull
//        assertThat(report.first()).isExactlyInstanceOf(TopicPerCategoryDto::class.java)
//    }
//
//    @Test
//    fun `deve buscar um topico por nome`() {
//        topicRepository.save(topic)
//        val result = topicRepository.findByCourseName(courseName = "Kotlin", pageable = pageable)
//
//        assertThat(result.totalElements).isEqualTo(1)
//    }
//}