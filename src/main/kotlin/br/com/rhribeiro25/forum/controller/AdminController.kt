package br.com.rhribeiro25.forum.controller

import br.com.rhribeiro25.forum.service.TopicService
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping

@Controller
@SecurityRequirement(name = "bearerAuth")
class AdminController(
    private val service: TopicService
) {

    @GetMapping("/reports")
    fun report(model: Model): String {
        model.addAttribute("topicPerCategory", service.report())
        return "report"
    }
}