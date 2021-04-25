package com.longlysmile.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author wujie
 * @since 2020-10-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("m_blog")
public class Blog implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private Long userId;

    @NotNull(message = "标题不能为空")
    private String title;

    @NotNull(message = "摘要不能为空")
    private String description;

    @NotNull(message = "内容不能为空")
    private String content;

    private LocalDateTime created;

    private Integer status;


    public Blog(){}

    public Blog(@NotNull(message = "标题不能为空") String title, @NotNull(message = "摘要不能为空") String description, @NotNull(message = "内容不能为空") String content) {
        this.title = title;
        this.description = description;
        this.content = content;
    }
}
