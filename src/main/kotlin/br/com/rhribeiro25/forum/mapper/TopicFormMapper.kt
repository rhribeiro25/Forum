package br.com.rhribeiro25.forum.mapper

import br.com.rhribeiro25.forum.dto.NewTopicForm
import br.com.rhribeiro25.forum.model.Topic
import br.com.rhribeiro25.forum.service.CourseService
import br.com.rhribeiro25.forum.service.UserService
import org.springframework.stereotype.Component
import java.time.LocalDate

@Component
class TopicFormMapper(
    private val courseService: CourseService,
    private val userService: UserService
): Mapper<NewTopicForm, Topic> {
    override fun map(t: NewTopicForm): Topic {
        return Topic(
            title = t.title,
            message = t.message,
            course = courseService.findById(t.idCourse),
            author = userService.findById(t.idAuthor),
            updateDate = LocalDate.now()
        )
    }

}
