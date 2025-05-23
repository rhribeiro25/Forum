package br.com.rhribeiro25.forum.mapper

import br.com.rhribeiro25.forum.dto.TopicView
import br.com.rhribeiro25.forum.model.Topic
import org.springframework.stereotype.Component

@Component
class TopicViewMapper:
    Mapper<Topic, TopicView> {

    override fun map(t: Topic): TopicView {
        return TopicView(
            id = t.id,
            title = t.title,
            message = t.message,
            createDate = t.createDate,
            status = t.status,
            updateDate = t.updateDate
        )
    }
}