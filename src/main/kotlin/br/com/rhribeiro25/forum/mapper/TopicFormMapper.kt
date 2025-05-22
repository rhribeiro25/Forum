package br.com.rhribeiro25.forum.mapper

import org.springframework.stereotype.Component
import java.time.LocalDate

@Component
class TopicFormMapper(
    private val courseService: br.com.rhribeiro25.forum.service.CourseService,
    private val userService: br.com.rhribeiro25.forum.service.UserService
): br.com.rhribeiro25.forum.mapper.Mapper<br.com.rhribeiro25.forum.dto.NewTopicForm, br.com.rhribeiro25.forum.model.Topic> {
    override fun map(t: br.com.rhribeiro25.forum.dto.NewTopicForm): br.com.rhribeiro25.forum.model.Topic {
        return br.com.rhribeiro25.forum.model.Topic(
            title = t.title,
            message = t.message,
            course = courseService.buscarPorId(t.idCourse),
            author = userService.buscarPorId(t.idAuthor),
            updateDate = LocalDate.now()
        )
    }

}
