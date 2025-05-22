package br.com.rhribeiro25.forum.controller

import io.swagger.v3.oas.annotations.security.SecurityRequirement
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponentsBuilder
import javax.validation.Valid

@RestController
@RequestMapping("/topicos")
@SecurityRequirement(name = "bearerAuth")
class TopicController(private val service: br.com.rhribeiro25.forum.service.TopicService) {

    @GetMapping
    fun listar(
            @RequestParam(required = false) nomeCurso: String?,
            @PageableDefault(size = 5, sort = ["dataCriacao"], direction = Sort.Direction.DESC) paginacao: Pageable
    ) = service.listar(nomeCurso, paginacao)

    @GetMapping("/{id}")
    fun buscarPorId(@PathVariable id: Long) = service.buscarPorId(id)

    @PostMapping
    @Transactional
    fun cadastrar(
        @RequestBody @Valid form: br.com.rhribeiro25.forum.dto.NewTopicForm,
        uriBuilder: UriComponentsBuilder
    ): ResponseEntity<br.com.rhribeiro25.forum.dto.TopicView> {
        val topicoView = service.cadastrar(form)
        val uri = uriBuilder.path("/topicos/${topicoView.id}").build().toUri()
        return ResponseEntity.created(uri).body(topicoView)
    }

    @PutMapping
    @Transactional
    fun atualizar(@RequestBody @Valid form: br.com.rhribeiro25.forum.dto.AuthorityTopicForm): ResponseEntity<br.com.rhribeiro25.forum.dto.TopicView> {
        val topicoView = service.atualizar(form)
        return ResponseEntity.ok(topicoView)
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Transactional
    fun deletar(@PathVariable id: Long) = service.deletar(id)
}