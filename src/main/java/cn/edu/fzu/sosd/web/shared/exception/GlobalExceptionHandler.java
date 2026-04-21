package cn.edu.fzu.sosd.web.shared.exception;

import cn.edu.fzu.sosd.web.shared.response.R;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BizException.class)
    public ResponseEntity<R<Void>> handleBizException(BizException ex) {
        return new ResponseEntity<>(R.fail(ex.getCode(), ex.getMessage()), HttpStatus.OK);
    }
}
