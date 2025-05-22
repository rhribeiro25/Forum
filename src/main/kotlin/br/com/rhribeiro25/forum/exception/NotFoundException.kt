package br.com.rhribeiro25.forum.exception

import java.lang.RuntimeException

class NotFoundException(message: String?) : RuntimeException(message) {
}