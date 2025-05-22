package br.com.rhribeiro25.forum.service

import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class TopicService(
    private val repository: br.com.rhribeiro25.forum.repository.TopicRepository,
    private val topicViewMapper: br.com.rhribeiro25.forum.mapper.TopicViewMapper,
    private val topicFormMapper: br.com.rhribeiro25.forum.mapper.TopicFormMapper,
    private val notFoundMessage: String = "Topic nao encontrado!"
) {

    @Cacheable(cacheNames = ["Topicos"], key = "#root.method.name")
    fun listar(
        nomeCurso: String?,
        paginacao: Pageable
    ): Page<br.com.rhribeiro25.forum.dto.TopicView> {
        val topicos = nomeCurso?.let {
            repository.findByCourseName(nomeCurso, paginacao)
        } ?: repository.findAll(paginacao)

        return topicos.map { t ->
            topicViewMapper.map(t)
        }
    }

    fun buscarPorId(id: Long): br.com.rhribeiro25.forum.dto.TopicView {
        val topico = repository.findById(id)
                .orElseThrow{ br.com.rhribeiro25.forum.exception.NotFoundException(notFoundMessage) }
        return topicViewMapper.map(topico)
    }

    @CacheEvict(cacheNames = ["Topicos"], allEntries = true)
    fun cadastrar(form: br.com.rhribeiro25.forum.dto.NewTopicForm): br.com.rhribeiro25.forum.dto.TopicView {
        val topico = topicFormMapper.map(form)
        repository.save(topico)
        return topicViewMapper.map(topico)
    }

    @CacheEvict(cacheNames = ["Topicos"], allEntries = true)
    fun atualizar(form: br.com.rhribeiro25.forum.dto.AuthorityTopicForm): br.com.rhribeiro25.forum.dto.TopicView {
        val topico = repository.findById(form.id)
                .orElseThrow{ br.com.rhribeiro25.forum.exception.NotFoundException(notFoundMessage) }
        topico.title = form.title
        topico.message = form.message
        return topicViewMapper.map(topico)
    }

    @CacheEvict(cacheNames = ["Topicos"], allEntries = true)
    fun deletar(id: Long) {
        repository.deleteById(id)
    }

    fun relatorio(): List<br.com.rhribeiro25.forum.dto.TopicPerCategoryDto> {
        return repository.report()
    }
}