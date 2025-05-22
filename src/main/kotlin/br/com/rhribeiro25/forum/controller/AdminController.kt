package br.com.rhribeiro25.forum.controller

import io.swagger.v3.oas.annotations.security.SecurityRequirement
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping

@Controller
@SecurityRequirement(name = "bearerAuth")
class AdminController(
    private val service: br.com.rhribeiro25.forum.service.TopicService
) {

    @GetMapping("/relatorios")
    fun relatorio(model: Model): String {
        model.addAttribute("topicosPorCategorias", service.relatorio())
        return "relatorio"
    }
}