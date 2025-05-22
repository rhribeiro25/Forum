package br.com.rhribeiro25.forum.model

import org.springframework.security.core.userdetails.UserDetails

class UserDetails(
        private val user: User
) : UserDetails {

    override fun getAuthorities() = user.role

    override fun getPassword() = user.password

    override fun getUsername() = user.email

    override fun isAccountNonExpired() = true

    override fun isAccountNonLocked() = true

    override fun isCredentialsNonExpired() = true

    override fun isEnabled() = true
}