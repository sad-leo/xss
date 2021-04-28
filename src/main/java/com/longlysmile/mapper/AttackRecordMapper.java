package com.longlysmile.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.longlysmile.entity.AttackRecord;

import java.util.List;
import java.util.Map;

/**
 * 攻击记录接口
 * @author wujie
 */
public interface AttackRecordMapper extends BaseMapper<AttackRecord> {

    List<Map<String,String>> statistics();
}
