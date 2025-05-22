package br.com.rhribeiro25.forum.model

object TopicTest {
    fun build() = br.com.rhribeiro25.forum.model.Topico(
        id = 1,
        titulo = "Kotlin Basico",
        mensagem = "Aprendendo kotlin basico",
        course = br.com.rhribeiro25.forum.model.CourseTest.build(),
        autor = br.com.rhribeiro25.forum.model.UserTest.build()
    )
}