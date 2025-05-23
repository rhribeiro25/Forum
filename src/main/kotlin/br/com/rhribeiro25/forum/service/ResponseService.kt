package br.com.rhribeiro25.forum.service

import br.com.rhribeiro25.forum.repository.ResponseRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ResponseService(
    private val responseRepository: ResponseRepository,
    private val emailService: EmailService,
    private val userService: UserService
) {
    @Transactional
    fun reply(response: br.com.rhribeiro25.forum.model.Response) {
        responseRepository.save(response)

        val user = userService.findById(requireNotNull(response.author.id))

        emailService.send(
            subject = "Alura - Tópico Respondido",
            text = "Você recebeu uma response no seu tópico",
            targetEmail = user.email
        )
    }
}