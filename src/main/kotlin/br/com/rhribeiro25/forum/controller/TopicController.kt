package br.com.rhribeiro25.forum.controller

import br.com.rhribeiro25.forum.dto.AuthorityTopicForm
import br.com.rhribeiro25.forum.dto.NewTopicForm
import br.com.rhribeiro25.forum.dto.TopicView
import br.com.rhribeiro25.forum.service.TopicService
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
@RequestMapping("/topics")
@SecurityRequirement(name = "bearerAuth")
class TopicController(private val service: TopicService) {

    @GetMapping
    fun list(
        @RequestParam(required = false) courseName: String?,
        @PageableDefault(size = 5, sort = ["createDate"], direction = Sort.Direction.DESC) pageable: Pageable
    ) = service.list(courseName, pageable)

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long) = service.findById(id)

    @PostMapping
    @Transactional
    fun create(
        @RequestBody @Valid form: NewTopicForm,
        uriBuilder: UriComponentsBuilder
    ): ResponseEntity<TopicView> {
        val topicView = service.create(form)
        val uri = uriBuilder.path("/topics/${topicView.id}").build().toUri()
        return ResponseEntity.created(uri).body(topicView)
    }

    @PutMapping
    @Transactional
    fun update(@RequestBody @Valid form: AuthorityTopicForm): ResponseEntity<TopicView> {
        val topicView = service.update(form)
        return ResponseEntity.ok(topicView)
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Transactional
    fun delete(@PathVariable id: Long) = service.delete(id)
}