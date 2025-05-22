package br.com.rhribeiro25.forum.mapper

import org.springframework.stereotype.Component

@Component
class TopicViewMapper:
    br.com.rhribeiro25.forum.mapper.Mapper<br.com.rhribeiro25.forum.model.Topic, br.com.rhribeiro25.forum.dto.TopicView> {

    override fun map(t: br.com.rhribeiro25.forum.model.Topic): br.com.rhribeiro25.forum.dto.TopicView {
        return br.com.rhribeiro25.forum.dto.TopicView(
            id = t.id,
            title = t.title,
            message = t.message,
            createDate = t.createDate,
            status = t.status,
            updateDate = t.updateDate
        )
    }
}