package com.convenientbuy.mapper;

import com.convenientbuy.pojo.CbUser;
import com.convenientbuy.pojo.CbUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * Created by bonismo@hotmail.com
 * 下午11:06 on 16/8/14.
 */
public interface CbUserMapper {
    int countByExample(CbUserExample example);

    int deleteByExample(CbUserExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CbUser record);

    int insertSelective(CbUser record);

    List<CbUser> selectByExample(CbUserExample example);

    CbUser selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CbUser record, @Param("example") CbUserExample example);

    int updateByExample(@Param("record") CbUser record, @Param("example") CbUserExample example);

    int updateByPrimaryKeySelective(CbUser record);

    int updateByPrimaryKey(CbUser record);
}