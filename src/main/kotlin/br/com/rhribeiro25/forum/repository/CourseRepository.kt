package br.com.rhribeiro25.forum.repository

import br.com.rhribeiro25.forum.model.Course
import org.springframework.data.jpa.repository.JpaRepository

interface CourseRepository: JpaRepository<Course, Long> {
}