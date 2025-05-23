package br.com.rhribeiro25.forum.model

object UserTest {
    fun build() = User(id = 1, name = "Joao", email = "jvc.martins", password = "123")
    fun buildToToken() =
        User(name = "Ana da Silva", email = "ana@email.com", password = "123456")
}