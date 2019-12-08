package com.minjihong.never.eduservice.entity.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class ChapterVo implements Serializable {

    private static final long serialVersionUID = 2405661553433878584L;

    private String id;

    private String title;

    private List<VideoVo> children = new ArrayList<>();

}
