package kr.or.dohands.dozon.core.exception

import com.fasterxml.jackson.databind.exc.MismatchedInputException
import kr.or.dohands.dozon.core.responsetemplate.Response
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ExceptionHandler {
    @ExceptionHandler(value = [IllegalArgumentException::class, IllegalStateException::class])
    protected fun handleIllegalException(e: IllegalArgumentException): Response<String> {
        return Response(401, "", e.message.toString())
    }

    @ExceptionHandler(value = [MismatchedInputException::class])
    protected fun handleMisMatchedInputExceptionIgnore(e: MismatchedInputException): Response<String> {
        return Response(200, "", "메시지 전송 성공")
    }
}