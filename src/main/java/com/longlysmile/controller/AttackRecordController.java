package com.longlysmile.controller;

import com.longlysmile.common.lang.Result;
import com.longlysmile.entity.AttackRecord;
import com.longlysmile.service.impl.AttackRecordServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * TODO
 *
 * @author wujie
 * @version 1.0
 * @date 2021/4/27 0:02
 */
@RestController
@RequestMapping("record")
public class AttackRecordController {

    @Resource
    private AttackRecordServiceImpl attackRecordService;

    @PostMapping("save")
    public void save(AttackRecord attackRecord) {
        attackRecordService.saveAndSend(attackRecord);
    }

    @GetMapping("statistics")
    public Result statistics() {
        return Result.success(attackRecordService.statistics());
    }
}
