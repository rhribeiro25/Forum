package br.com.rhribeiro25.forum.service

import br.com.rhribeiro25.forum.model.User
import br.com.rhribeiro25.forum.model.UserDetails
import br.com.rhribeiro25.forum.repository.UserRepository
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import java.lang.RuntimeException

@Service
class UserService(
    private val repository: UserRepository
) : UserDetailsService {

    fun findById(id: Long): User {
        return repository.getOne(id)
    }

    override fun loadUserByUsername(username: String?): UserDetails {
        val user = repository.findByEmail(username) ?: throw RuntimeException()
        return UserDetails(user)
    }
}
