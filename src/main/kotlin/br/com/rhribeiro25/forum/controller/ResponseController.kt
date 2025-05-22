package br.com.rhribeiro25.forum.controller

import io.swagger.v3.oas.annotations.security.SecurityRequirement
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/responses")
@SecurityRequirement(name = "bearerAuth")
class ResponseController(
    private val responseService: br.com.rhribeiro25.forum.service.ResponseService
) {

    @PostMapping
    fun responder(@RequestBody response: br.com.rhribeiro25.forum.model.Response) {
        return responseService.responder(response)
    }
}