package br.com.rhribeiro25.forum.mapper

import org.springframework.stereotype.Component

@Component
class TopicViewMapper:
    br.com.rhribeiro25.forum.mapper.Mapper<br.com.rhribeiro25.forum.model.Topico, br.com.rhribeiro25.forum.dto.TopicoView> {

    override fun map(t: br.com.rhribeiro25.forum.model.Topico): br.com.rhribeiro25.forum.dto.TopicoView {
        return br.com.rhribeiro25.forum.dto.TopicoView(
            id = t.id,
            titulo = t.titulo,
            mensagem = t.mensagem,
            dataCriacao = t.dataCriacao,
            status = t.status,
            dataAlteracao = t.dataAlteracao
        )
    }
}