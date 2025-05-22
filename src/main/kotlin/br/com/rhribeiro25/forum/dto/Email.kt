package br.com.rhribeiro25.forum.dto

data class Email(
    val subject: String,
    val text: String,
    val targetEmail: String
)