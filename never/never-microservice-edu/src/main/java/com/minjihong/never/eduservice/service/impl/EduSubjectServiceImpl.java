package com.minjihong.never.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.minjihong.never.common.constants.ResultCode;
import com.minjihong.never.eduservice.entity.EduSubject;
import com.minjihong.never.eduservice.exception.EduException;
import com.minjihong.never.eduservice.mapper.EduSubjectMapper;
import com.minjihong.never.eduservice.service.EduSubjectService;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author jihong.min
 * @since 2019-12-03
 */
@Service
@Transactional
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {

    @Override
    public List<String> importSubject(MultipartFile file) {

        List<String> message = new ArrayList<>();
        try {
            //获取输入流
            InputStream inputStream = file.getInputStream();
            // 创建新的Excel 工作簿
            Workbook workbook = new XSSFWorkbook(inputStream);
            //workbook 获取sheeet
            Sheet sheet = workbook.getSheetAt(0);


            //sheet 获取row, 循环遍历,从第二行开始获取数据
            int lastRowNum = sheet.getLastRowNum();
            for (int i = 1; i <= lastRowNum; i++) {
                Row row = sheet.getRow(i);
                //行为空,提示错误信息
                if (row == null) {
                    String str = "表格数据为空,请输入数据";
                    message.add(str);
                    return message;
                }
                //行不为空,取第一列
                Cell cellOne = row.getCell(0);
                if (cellOne == null) {
                    String str = "第" + i + "行1列数据为空";
                    message.add(str);
                    continue;
                }
                //列不为空,取第一列
                //一级分类值
                String cellOneStringValue = cellOne.getStringCellValue();
                String id_parent = null;
                //添加一级分类
                //先判断,是否存在,不存在,再添加
                EduSubject existOneSubject = this.existOneSubject(cellOneStringValue);
                if (existOneSubject == null) {
                    EduSubject eduSubject = new EduSubject();
                    eduSubject.setTitle(cellOneStringValue);
                    eduSubject.setParentId("0");
                    eduSubject.setSort(0);
                    baseMapper.insert(eduSubject);
                    id_parent = eduSubject.getParentId();
                } else {
                    id_parent = existOneSubject.getParentId();
                }

                //行不为空,取第二列
                Cell cellTwo = row.getCell(1);
                if (cellTwo == null) {
                    String str = "第" + i + "行2列数据为空";
                    message.add(str);
                    continue;
                }
                //列不为空,取第二列
                String cellTwoStringValue = cellTwo.getStringCellValue();
                //添加二级分类
                EduSubject existTwoSubject = this.existTwoSubject(cellTwoStringValue, id_parent);
                if (existTwoSubject == null) {
                    EduSubject eduSubject = new EduSubject();
                    eduSubject.setTitle(cellTwoStringValue);
                    eduSubject.setParentId(id_parent);
                    eduSubject.setSort(0);
                    baseMapper.insert(eduSubject);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new EduException(ResultCode.EXCEL_IMPORT_ERROR.getCode(), ResultCode.EXCEL_IMPORT_ERROR.getMessage());
        }
        return message;


    }


    //判断一级分类在数据库是否存在
    private EduSubject existOneSubject(String name) {
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title", name);
        wrapper.eq("parent_id", "0");
        EduSubject eduSubject = baseMapper.selectOne(wrapper);
        return eduSubject;
    }

    //判断二级分类在数据库是否存在
    private EduSubject existTwoSubject(String name, String parentId) {
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title", name);
        wrapper.eq("parent_id", parentId);
        EduSubject eduSubject = baseMapper.selectOne(wrapper);
        return eduSubject;
    }


}
