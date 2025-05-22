package br.com.rhribeiro25.forum.dto

import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

data class NewTopicForm(
        @field:NotEmpty(message = "Titulo nao pode ser em branco")
        @field:Size(min = 5, max = 100, message = "Titulo deve ter entre 5 e 100 caracteres")
        val title: String,
        @field:NotEmpty(message = "Mensagem nao pode ser em branco")
        val message: String,
        @field:NotNull
        val idCourse: Long,
        @field:NotNull
        val idAuthor: Long
)
