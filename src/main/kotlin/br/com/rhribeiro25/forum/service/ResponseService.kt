package br.com.rhribeiro25.forum.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ResponseService(
    private val responseRepository: br.com.rhribeiro25.forum.repository.ResponseRepository,
    private val emailService: br.com.rhribeiro25.forum.service.EmailService,
    private val userService: br.com.rhribeiro25.forum.service.UserService
) {
    @Transactional
    fun responder(response: br.com.rhribeiro25.forum.model.Response) {
        responseRepository.save(response)

        val usuario = userService.buscarPorId(requireNotNull(response.author.id))

        emailService.enviar(
            subject = "Alura - Tópico Respondido",
            text = "Você recebeu uma response no seu tópico",
            targetEmail = usuario.email
        )
    }
}