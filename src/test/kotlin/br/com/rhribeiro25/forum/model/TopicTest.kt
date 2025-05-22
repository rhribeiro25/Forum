package br.com.rhribeiro25.forum.model

object TopicTest {
    fun build() = br.com.rhribeiro25.forum.model.Topic(
        id = 1,
        title = "Kotlin Basico",
        message = "Aprendendo kotlin basico",
        course = br.com.rhribeiro25.forum.model.CourseTest.build(),
        author = br.com.rhribeiro25.forum.model.UserTest.build()
    )
}