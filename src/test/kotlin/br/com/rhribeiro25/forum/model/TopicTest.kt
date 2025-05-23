package br.com.rhribeiro25.forum.model

object TopicTest {
    fun build() = Topic(
        id = 1,
        title = "Kotlin Basico",
        message = "Aprendendo kotlin basico",
        course = CourseTest.build(),
        author = UserTest.build()
    )
}