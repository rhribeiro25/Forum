package br.com.rhribeiro25.forum.model

object UserTest {
    fun build() = br.com.rhribeiro25.forum.model.User(id = 1, name = "Joao", email = "jvc.martins", password = "123")
    fun buildToToken() =
        br.com.rhribeiro25.forum.model.User(name = "Ana da Silva", email = "ana@email.com", password = "123456")
}