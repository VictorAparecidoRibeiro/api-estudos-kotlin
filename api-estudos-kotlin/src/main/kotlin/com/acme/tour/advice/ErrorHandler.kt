package com.acme.tour.advice

import com.acme.tour.dto.ErrorMessageDTO
import com.acme.tour.exception.PromocaoNotFoundException
import com.fasterxml.jackson.core.JsonParseException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import java.lang.Exception
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@ControllerAdvice
class ErrorHandler {

    @ExceptionHandler(JsonParseException::class)
    fun JsonParseExceptionHandler(servletRequest: HttpServletRequest, servletResponse: HttpServletResponse, exception: Exception): ResponseEntity<ErrorMessageDTO> {
            return ResponseEntity(ErrorMessageDTO("JSON ERROR", exception.message ?: " Invalid Json"), HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(PromocaoNotFoundException::class)
    fun PromocaoNotFoundExceptionHandlerservletRequest(servletRequest: HttpServletRequest, servletResponse: HttpServletResponse, exception: Exception): ResponseEntity<ErrorMessageDTO>{
        return ResponseEntity(ErrorMessageDTO("Promoção não localizada ", exception.message !! ), HttpStatus.NOT_FOUND)
    }
}