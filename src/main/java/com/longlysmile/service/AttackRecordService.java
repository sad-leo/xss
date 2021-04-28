package com.longlysmile.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.longlysmile.entity.AttackRecord;

import java.util.List;
import java.util.Map;

/**
 * 攻击记录服务类
 *
 * @author wujie
 * @version 1.0
 * @date 2021/4/26 22:35
 */
public interface AttackRecordService extends IService<AttackRecord> {

    void saveAndSend(AttackRecord attackRecord);

    List<Map<String,String>> statistics();
}
