package com.verge.theverge.exception

import com.verge.theverge.controller.responses.ErrorResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest

@ControllerAdvice
class ControllerAdvice {
    //For Tables already reserved
    @ExceptionHandler(ReservedTableException::class)
    fun handleReservedTableException(ex: ReservedTableException, request: WebRequest): ResponseEntity<ErrorResponse> {
       val error = ErrorResponse(
            HttpStatus.CONFLICT.value(),
            ex.message,
            ex.errorCode,
            null
        )
        return ResponseEntity(error, HttpStatus.CONFLICT)
    }

    //Not Found exception
    @ExceptionHandler(NotFoundException::class)
    fun handleNotFoundException(ex: NotFoundException, request: WebRequest): ResponseEntity<ErrorResponse> {
        val error = ErrorResponse(
            HttpStatus.NOT_FOUND.value(),
            ex.message,
            ex.errorCode,
            null
        )
        return ResponseEntity(error, HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(BadRequestException::class)
    fun handleBadRequestException(ex: BadRequestException, request: WebRequest): ResponseEntity<ErrorResponse> {
        val error = ErrorResponse(
            HttpStatus.BAD_REQUEST.value(),
            ex.message,
            ex.errorCode,
            null
        )
        return ResponseEntity(error, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(BadLoginException::class)
    fun handleBadRequestException(ex: BadLoginException, request: WebRequest): ResponseEntity<ErrorResponse> {
        val error = ErrorResponse(
            HttpStatus.BAD_REQUEST.value(),
            ex.message,
            ex.errorCode,
            null
        )
        return ResponseEntity(error, HttpStatus.BAD_REQUEST)
    }


}