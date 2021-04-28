package com.longlysmile.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 攻击记录
 *
 * @author wujie
 * @version 1.0
 * @date 2021/4/26 22:30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("attack_record")
public class AttackRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String type;

    @TableField("content")
    private String content;

    @TableField("create_time")
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss",
            timezone = "GMT+8"
    )
    private LocalDateTime createTime;

    public AttackRecord(){}

    public AttackRecord(String type, String content, LocalDateTime createTime) {
        this.type = type;
        this.content = content;
        this.createTime = createTime;
    }

    public AttackRecord(Long id, String type, String content, LocalDateTime createTime) {
        this.id = id;
        this.type = type;
        this.content = content;
        this.createTime = createTime;
    }
}
