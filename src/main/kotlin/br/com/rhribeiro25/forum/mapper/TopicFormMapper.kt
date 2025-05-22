package br.com.rhribeiro25.forum.mapper

import org.springframework.stereotype.Component
import java.time.LocalDate

@Component
class TopicFormMapper(
    private val courseService: br.com.rhribeiro25.forum.service.CourseService,
    private val userService: br.com.rhribeiro25.forum.service.UserService
): br.com.rhribeiro25.forum.mapper.Mapper<br.com.rhribeiro25.forum.dto.NewTopicForm, br.com.rhribeiro25.forum.model.Topico> {
    override fun map(t: br.com.rhribeiro25.forum.dto.NewTopicForm): br.com.rhribeiro25.forum.model.Topico {
        return br.com.rhribeiro25.forum.model.Topico(
            titulo = t.titulo,
            mensagem = t.mensagem,
            course = courseService.buscarPorId(t.idCurso),
            autor = userService.buscarPorId(t.idAutor),
            dataAlteracao = LocalDate.now()
        )
    }

}
