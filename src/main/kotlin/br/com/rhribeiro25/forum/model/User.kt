package br.com.rhribeiro25.forum.model

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.*

@Entity
data class User(

        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long? = null,
        val nome: String,
        val email: String,
        val password: String,

        @JsonIgnore
        @ManyToMany(fetch = FetchType.EAGER)
        @JoinColumn(name = "usuario_role")
        val role: List<br.com.rhribeiro25.forum.model.Role> = mutableListOf()
)
