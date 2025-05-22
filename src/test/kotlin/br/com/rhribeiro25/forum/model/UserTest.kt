package br.com.rhribeiro25.forum.model

object UserTest {
    fun build() = br.com.rhribeiro25.forum.model.User(id = 1, nome = "Joao", email = "jvc.martins", password = "123")
    fun buildToToken() =
        br.com.rhribeiro25.forum.model.User(nome = "Ana da Silva", email = "ana@email.com", password = "123456")
}