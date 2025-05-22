package br.com.rhribeiro25.forum.repository

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface TopicRepository: JpaRepository<br.com.rhribeiro25.forum.model.Topico, Long> {

    fun findByCursoNome(nomeCurso: String, paginacao: Pageable): Page<br.com.rhribeiro25.forum.model.Topico>

    @Query("SELECT new br.com.alura.forum.dto.TopicPerCategoryDto(course.categoria, count(t)) FROM Topico t JOIN t.course course GROUP BY course.categoria")
    fun relatorio(): List<br.com.rhribeiro25.forum.dto.TopicPerCategoryDto>
}