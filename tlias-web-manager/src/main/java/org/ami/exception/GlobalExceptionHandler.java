package org.ami.exception;

import lombok.extern.slf4j.Slf4j;
import org.ami.pojo.Result;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler
    public Result handleClazzHasStudentsException(ClazzHasStudentsException e) {
        log.error("删除班级失败: {}", e.getMessage());
        return Result.error("该班级下有学生不能直接删除");
    }

    @ExceptionHandler
    public Result handleException(Exception e) {
        log.error("服务器发生异常: {}", e.getMessage());
        return Result.error("服务器发生异常");
    }
    @ExceptionHandler
//    手机号码重复的异常
    public Result handleDuplicateKeyException(DuplicateKeyException e) {
        log.error("手机号码重复: {}", e.getMessage());
        String message = e.getMessage();
        int index = message.indexOf("Duplicate entry");
        String errMsg = message.substring(index);
        String[] arr = errMsg.split(" ");
        return Result.error(arr[2] + " 已存在");
    }
}
