package com.minjihong.never.eduservice.entity.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(value = "eduTeacher查询对象",description = "讲师查询对象封装")
public class QueryTeacher implements Serializable {

    private static final long serialVersionUID = 7645776189049549810L;

    @ApiModelProperty(value = "讲师名称,模糊查询" )
    private String name;

    @ApiModelProperty(value = "头衔,1高级讲师,2首席讲师")
    private String level;

    @ApiModelProperty(value = "查询开始时间")
    private String begin;

    @ApiModelProperty(value = "查询结束时间")
    private String end;

}
