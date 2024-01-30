package com.What2Do.What2Do.aop;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.persistence.EntityNotFoundException;
import java.sql.SQLIntegrityConstraintViolationException;

@ControllerAdvice
public class ExceptionHandlerAdvice {

    //    ExceptionHandler 의 역할은 EntityNotFoundException 이라는 예외 클래스가 발생했을 때 catch 역학 / 404 에러
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> notFound(Exception e) {
        String context = "<header><h1> 존재하지 않는 화면입니다.</h1></header>";
        return new ResponseEntity<String>(context, HttpStatus.NOT_FOUND);
    }

    //    SQLIntegrityConstraintViolationException : 중복테스트 / 409 에러
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<String> duplicateEmail(Exception e) {
        String context = "<header><h1> 중복된 이메일 입니다.</h1></header>";
        return new ResponseEntity<String>(context, HttpStatus.CONFLICT);
    }
//
//    그 외의 모든 예외
//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<String> etcException(Exception e){
//        String context = "<header><h1> 서버에 에러가 발생 했습니다.</h1></header>";
//        return new ResponseEntity<String>(context, HttpStatus.INTERNAL_SERVER_ERROR);
//    }

}