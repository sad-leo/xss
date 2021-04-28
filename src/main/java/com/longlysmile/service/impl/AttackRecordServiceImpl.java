package com.longlysmile.service.impl;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.longlysmile.entity.AttackRecord;
import com.longlysmile.mapper.AttackRecordMapper;
import com.longlysmile.service.AttackRecordService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * TODO
 *
 * @author wujie
 * @version 1.0
 * @date 2021/4/26 22:36
 */
@Service
public class AttackRecordServiceImpl extends ServiceImpl<AttackRecordMapper, AttackRecord> implements AttackRecordService {

    @Resource
    private MailService mailService;
    @Resource
    private AttackRecordMapper mapper;

    @Override
    public void saveAndSend(AttackRecord attackRecord) {
        try {
            attackRecord.setCreateTime(LocalDateTime.now());
            save(attackRecord);
            mailService.sendMail("[" + attackRecord.getType() + "][" +
                    DateUtil.format(localDateTimeToDate(attackRecord.getCreateTime()), DatePattern.NORM_DATETIME_PATTERN) + "]" + attackRecord.getContent());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Map<String, String>> statistics() {
        return mapper.statistics();
    }

    /**
     * LocalDateTime转Date
     *
     * @param localDateTime LocalDateTime
     * @return Date
     */
    public static Date localDateTimeToDate(LocalDateTime localDateTime) {
        try {
            ZoneId zoneId = ZoneId.systemDefault();
            ZonedDateTime zdt = localDateTime.atZone(zoneId);
            return Date.from(zdt.toInstant());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
