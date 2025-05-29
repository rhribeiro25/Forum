package br.com.rhribeiro25.forum.service

import br.com.rhribeiro25.forum.dto.AuthorityTopicForm
import br.com.rhribeiro25.forum.dto.NewTopicForm
import br.com.rhribeiro25.forum.dto.TopicPerCategoryDto
import br.com.rhribeiro25.forum.dto.TopicView
import br.com.rhribeiro25.forum.exception.NotFoundException
import br.com.rhribeiro25.forum.mapper.TopicFormMapper
import br.com.rhribeiro25.forum.mapper.TopicViewMapper
import br.com.rhribeiro25.forum.repository.TopicRepository
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.cache.annotation.Caching
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class TopicService(
    private val repository: TopicRepository,
    private val topicViewMapper: TopicViewMapper,
    private val topicFormMapper: TopicFormMapper,
    private val notFoundMessage: String = "Topic nao encontrado!"
) {
    @Cacheable(cacheNames = ["TopicsByCourse"], key = "#courseName ?: 'all'")
    fun list(
        courseName: String?,
        pageable: Pageable
    ): Page<TopicView> {
        val topics = courseName?.let {
            repository.findByCourseName(courseName, pageable)
        } ?: repository.findAll(pageable)

        return topics.map { t ->
            topicViewMapper.map(t)
        }
    }

    @Cacheable(cacheNames = ["TopicById"], key = "#id")
    fun findById(id: Long): TopicView {
        val topic = repository.findById(id)
                .orElseThrow{ NotFoundException(notFoundMessage) }
        return topicViewMapper.map(topic)
    }

    @Caching(
        evict = [
            CacheEvict(cacheNames = ["TopicsByCourse"], allEntries = true),
            CacheEvict(cacheNames = ["TopicById"], allEntries = true)
        ]
    )
    fun create(form: NewTopicForm): TopicView {
        val topic = topicFormMapper.map(form)
        repository.save(topic)
        return topicViewMapper.map(topic)
    }
    @Caching(
        evict = [
            CacheEvict(cacheNames = ["TopicsByCourse"], allEntries = true),
            CacheEvict(cacheNames = ["TopicById"], key = "#form.id")
        ]
    )
    fun update(form: AuthorityTopicForm): TopicView {
        val topic = repository.findById(form.id)
                .orElseThrow{ NotFoundException(notFoundMessage) }
        topic.title = form.title
        topic.message = form.message
        return topicViewMapper.map(topic)
    }

    @Caching(
        evict = [
            CacheEvict(cacheNames = ["TopicsByCourse"], allEntries = true),
            CacheEvict(cacheNames = ["TopicById"], key = "#id")
        ]
    )
    fun delete(id: Long) {
        repository.deleteById(id)
    }

    fun report(): List<TopicPerCategoryDto> {
        return repository.findTopicPerCategory()
    }
}