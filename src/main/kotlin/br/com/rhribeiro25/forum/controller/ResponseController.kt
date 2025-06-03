package br.com.rhribeiro25.forum.controller

import br.com.rhribeiro25.forum.model.Response
import br.com.rhribeiro25.forum.service.ResponseService
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/responses")
@SecurityRequirement(name = "bearerAuth")
class ResponseController(
    private val responseService: ResponseService
) {

    @PostMapping
    fun reply(@RequestBody response: Response) {
        return responseService.reply(response)
    }
}