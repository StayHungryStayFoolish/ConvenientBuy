package com.convenientbuy.mapper;

import com.convenientbuy.pojo.CbContent;
import com.convenientbuy.pojo.CbContentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * Created by bonismo@hotmail.com
 * 下午11:21 on 16/7/30.
 */
public interface CbContentMapper {
    int countByExample(CbContentExample example);

    int deleteByExample(CbContentExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CbContent record);

    int insertSelective(CbContent record);

    List<CbContent> selectByExampleWithBLOBs(CbContentExample example);

    List<CbContent> selectByExample(CbContentExample example);

    CbContent selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CbContent record, @Param("example") CbContentExample example);

    int updateByExampleWithBLOBs(@Param("record") CbContent record, @Param("example") CbContentExample example);

    int updateByExample(@Param("record") CbContent record, @Param("example") CbContentExample example);

    int updateByPrimaryKeySelective(CbContent record);

    int updateByPrimaryKeyWithBLOBs(CbContent record);

    int updateByPrimaryKey(CbContent record);
}