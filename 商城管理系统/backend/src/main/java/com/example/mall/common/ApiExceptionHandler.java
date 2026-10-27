package com.example.mall.common;

import jakarta.validation.ConstraintViolationException;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.PessimisticLockingFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@RestControllerAdvice
public class ApiExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(ApiExceptionHandler.class);

    @ExceptionHandler(org.springframework.web.multipart.MaxUploadSizeExceededException.class)
    ResponseEntity<Map<String, String>> uploadTooLarge(Exception exception) {
        return response(HttpStatus.PAYLOAD_TOO_LARGE, "图片不能超过 5 MB");
    }

    @ExceptionHandler({org.springframework.web.bind.MissingServletRequestParameterException.class,
            org.springframework.web.multipart.support.MissingServletRequestPartException.class})
    ResponseEntity<Map<String, String>> missingParameter(Exception exception) {
        return response(HttpStatus.BAD_REQUEST, "缺少必要参数，请检查输入");
    }

    @ExceptionHandler(BusinessException.class)
    ResponseEntity<Map<String, String>> business(BusinessException exception) {
        return response(exception.status(), exception.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    ResponseEntity<Map<String, String>> invalid(MethodArgumentNotValidException exception) {
        var errors = exception.getBindingResult().getAllErrors();
        return response(HttpStatus.BAD_REQUEST, errors.isEmpty() ? "请求参数无效" : errors.get(0).getDefaultMessage());
    }

    @ExceptionHandler({ HttpMessageNotReadableException.class, MethodArgumentTypeMismatchException.class,
            ConstraintViolationException.class })
    ResponseEntity<Map<String, String>> malformed(Exception exception) {
        return response(HttpStatus.BAD_REQUEST, "请求格式或参数无效，请检查输入内容");
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    ResponseEntity<Map<String, String>> integrity(DataIntegrityViolationException exception) {
        return response(HttpStatus.CONFLICT, "名称或编号已存在，或该记录仍被其他数据引用");
    }

    @ExceptionHandler(PessimisticLockingFailureException.class)
    ResponseEntity<Map<String, String>> lock(PessimisticLockingFailureException exception) {
        return response(HttpStatus.CONFLICT, "相关数据正在被其他操作修改，请稍后重试");
    }

    @ExceptionHandler(NoResourceFoundException.class)
    ResponseEntity<Map<String, String>> missing(NoResourceFoundException exception) {
        return response(HttpStatus.NOT_FOUND, "接口不存在");
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    ResponseEntity<Map<String, String>> unsupportedMethod(HttpRequestMethodNotSupportedException exception) {
        return response(HttpStatus.METHOD_NOT_ALLOWED, "该接口不支持此请求方式");
    }

    @ExceptionHandler(Exception.class)
    ResponseEntity<Map<String, String>> unexpected(Exception exception) {
        log.error("处理请求时发生异常", exception);
        return response(HttpStatus.INTERNAL_SERVER_ERROR, "服务器处理失败，请稍后重试");
    }

    private ResponseEntity<Map<String, String>> response(HttpStatus status, String message) {
        return ResponseEntity.status(status).body(Map.of("message", message));
    }
}
