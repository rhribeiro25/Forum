package br.com.rhribeiro25.forum.service

import br.com.rhribeiro25.forum.model.Course
import br.com.rhribeiro25.forum.repository.CourseRepository
import org.springframework.stereotype.Service

@Service
class CourseService(private val repository: CourseRepository) {

    fun findById(id: Long): Course {
        return repository.getOne(id)
    }


}
