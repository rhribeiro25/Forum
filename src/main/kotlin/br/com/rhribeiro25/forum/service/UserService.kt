package br.com.rhribeiro25.forum.service

import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import java.lang.RuntimeException

@Service
class UserService(
    private val repository: br.com.rhribeiro25.forum.repository.UserRepository
) : UserDetailsService {

    fun buscarPorId(id: Long): br.com.rhribeiro25.forum.model.User {
        return repository.getOne(id)
    }

    override fun loadUserByUsername(username: String?): org.springframework.security.core.userdetails.UserDetails {
        val usuario = repository.findByEmail(username) ?: throw RuntimeException()
        return br.com.rhribeiro25.forum.model.UserDetails(usuario)
    }
}
