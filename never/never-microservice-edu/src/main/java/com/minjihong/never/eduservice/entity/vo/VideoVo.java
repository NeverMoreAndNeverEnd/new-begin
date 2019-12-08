package com.minjihong.never.eduservice.entity.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class VideoVo implements Serializable {

    private static final long serialVersionUID = -9126120334495036850L;

    private String id;

    private String title;

    private boolean free;

}
