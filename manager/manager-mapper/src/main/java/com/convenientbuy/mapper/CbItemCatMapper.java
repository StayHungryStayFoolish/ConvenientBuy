package com.convenientbuy.mapper;

import com.convenientbuy.pojo.CbItemCat;
import com.convenientbuy.pojo.CbItemCatExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * Created by bonismo@hotmail.com
 * 下午10:10 on 16/7/31.
 */
public interface CbItemCatMapper {
    int countByExample(CbItemCatExample example);

    int deleteByExample(CbItemCatExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CbItemCat record);

    int insertSelective(CbItemCat record);

    List<CbItemCat> selectByExample(CbItemCatExample example);

    CbItemCat selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CbItemCat record, @Param("example") CbItemCatExample example);

    int updateByExample(@Param("record") CbItemCat record, @Param("example") CbItemCatExample example);

    int updateByPrimaryKeySelective(CbItemCat record);

    int updateByPrimaryKey(CbItemCat record);
}