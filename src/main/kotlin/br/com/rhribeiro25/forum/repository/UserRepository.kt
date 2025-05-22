package br.com.rhribeiro25.forum.repository

import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository: JpaRepository<br.com.rhribeiro25.forum.model.User, Long> {

    fun findByEmail(username: String?): br.com.rhribeiro25.forum.model.User?
}