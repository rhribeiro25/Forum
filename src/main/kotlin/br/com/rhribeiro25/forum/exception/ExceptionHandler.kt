package br.com.rhribeiro25.forum.exception

import br.com.rhribeiro25.forum.dto.ErrorView
import org.springframework.http.HttpStatus
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import javax.servlet.http.HttpServletRequest

@RestControllerAdvice
class ExceptionHandler {

    @br.com.rhribeiro25.forum.exception.ExceptionHandler(br.com.rhribeiro25.forum.exception.NotFoundException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun handleNotFound(
        exception: br.com.rhribeiro25.forum.exception.NotFoundException,
        request: HttpServletRequest
    ): br.com.rhribeiro25.forum.dto.ErrorView {
        return br.com.rhribeiro25.forum.dto.ErrorView(
            status = HttpStatus.NOT_FOUND.value(),
            error = HttpStatus.NOT_FOUND.name,
            message = exception.message,
            path = request.servletPath
        )
    }

    @br.com.rhribeiro25.forum.exception.ExceptionHandler(MethodArgumentNotValidException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleValidationError(
            exception: MethodArgumentNotValidException,
            request: HttpServletRequest
    ): br.com.rhribeiro25.forum.dto.ErrorView {
        val errorMessage = HashMap<String, String?>()
        exception.bindingResult.fieldErrors.forEach{
            e -> errorMessage.put(e.field, e.defaultMessage)
        }
        return br.com.rhribeiro25.forum.dto.ErrorView(
            status = HttpStatus.BAD_REQUEST.value(),
            error = HttpStatus.BAD_REQUEST.name,
            message = errorMessage.toString(),
            path = request.servletPath
        )
    }

    @br.com.rhribeiro25.forum.exception.ExceptionHandler(Exception::class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    fun handleServerError(
            exception: Exception,
            request: HttpServletRequest
    ): br.com.rhribeiro25.forum.dto.ErrorView {
        return br.com.rhribeiro25.forum.dto.ErrorView(
            status = HttpStatus.INTERNAL_SERVER_ERROR.value(),
            error = HttpStatus.INTERNAL_SERVER_ERROR.name,
            message = exception.message,
            path = request.servletPath
        )
    }

}