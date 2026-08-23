package org.ami.controller;

import lombok.extern.slf4j.Slf4j;
import org.ami.pojo.LogQueryParam;
import org.ami.pojo.OperateLog;
import org.ami.pojo.PageResult;
import org.ami.pojo.Result;
import org.ami.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/log")
public class OperateLogController {
    @Autowired
    private EmpService empService;

    /**
     * 分页查询日志信息
     */
    @GetMapping("/page")
    public Result getLogList(LogQueryParam logQueryParam){
        log.info("分页查询日志信息，页码：{}，每页大小：{}", logQueryParam.getPage(), logQueryParam.getPageSize());
        PageResult<OperateLog> pageResult = empService.getLogList(logQueryParam);
        return Result.success(pageResult);
    }
}
