package br.com.rhribeiro25.forum.repository

import br.com.rhribeiro25.forum.model.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository: JpaRepository<User, Long> {

    fun findByEmail(userName: String?): User?
}