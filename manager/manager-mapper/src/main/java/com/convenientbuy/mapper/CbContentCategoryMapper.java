package com.convenientbuy.mapper;

import com.convenientbuy.pojo.CbContentCategory;
import com.convenientbuy.pojo.CbContentCategoryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * Created by bonismo@hotmail.com
 * 下午10:18 on 16/7/30.
 */
public interface CbContentCategoryMapper {
    int countByExample(CbContentCategoryExample example);

    int deleteByExample(CbContentCategoryExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CbContentCategory record);

    int insertSelective(CbContentCategory record);

    List<CbContentCategory> selectByExample(CbContentCategoryExample example);

    CbContentCategory selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CbContentCategory record, @Param("example") CbContentCategoryExample example);

    int updateByExample(@Param("record") CbContentCategory record, @Param("example") CbContentCategoryExample example);

    int updateByPrimaryKeySelective(CbContentCategory record);

    int updateByPrimaryKey(CbContentCategory record);
}