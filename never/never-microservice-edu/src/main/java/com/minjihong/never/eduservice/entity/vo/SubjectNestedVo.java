package com.minjihong.never.eduservice.entity.vo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class SubjectNestedVo {

    private String id;

    private String title;

    private List<SubjectVo> children = new ArrayList<>();
}
