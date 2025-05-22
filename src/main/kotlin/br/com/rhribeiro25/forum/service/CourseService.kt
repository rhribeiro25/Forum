package br.com.rhribeiro25.forum.service

import org.springframework.stereotype.Service

@Service
class CourseService(private val repository: br.com.rhribeiro25.forum.repository.CourseRepository) {

    fun buscarPorId(id: Long): br.com.rhribeiro25.forum.model.Course {
        return repository.getOne(id)
    }


}
