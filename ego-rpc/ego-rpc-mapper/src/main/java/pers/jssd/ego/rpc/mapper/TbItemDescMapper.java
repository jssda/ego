package pers.jssd.ego.rpc.mapper;

import org.apache.ibatis.annotations.Param;
import pers.jssd.ego.rpc.pojo.TbItemDesc;
import pers.jssd.ego.rpc.pojo.TbItemDescExample;

import java.util.List;

public interface TbItemDescMapper {
    int countByExample(TbItemDescExample example);

    int deleteByExample(TbItemDescExample example);

    int deleteByPrimaryKey(Long itemId);

    int insert(TbItemDesc record);

    int insertSelective(TbItemDesc record);

    List<TbItemDesc> selectByExampleWithBLOBs(TbItemDescExample example);

    List<TbItemDesc> selectByExample(TbItemDescExample example);

    TbItemDesc selectByPrimaryKey(Long itemId);

    int updateByExampleSelective(@Param("record") TbItemDesc record, @Param("example") TbItemDescExample example);

    int updateByExampleWithBLOBs(@Param("record") TbItemDesc record, @Param("example") TbItemDescExample example);

    int updateByExample(@Param("record") TbItemDesc record, @Param("example") TbItemDescExample example);

    int updateByPrimaryKeySelective(TbItemDesc record);

    int updateByPrimaryKeyWithBLOBs(TbItemDesc record);

    int updateByPrimaryKey(TbItemDesc record);
}