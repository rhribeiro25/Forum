package br.com.rhribeiro25.forum.repository

import org.springframework.data.jpa.repository.JpaRepository

interface CourseRepository: JpaRepository<br.com.rhribeiro25.forum.model.Course, Long> {
}