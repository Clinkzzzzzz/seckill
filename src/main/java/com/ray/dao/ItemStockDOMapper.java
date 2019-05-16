package com.ray.dao;

import com.ray.dataobject.ItemStockDO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemStockDOMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table item_stock
     *
     * @mbg.generated Tue Apr 16 10:02:00 CST 2019
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table item_stock
     *
     * @mbg.generated Tue Apr 16 10:02:00 CST 2019
     */
    int insert(ItemStockDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table item_stock
     *
     * @mbg.generated Tue Apr 16 10:02:00 CST 2019
     */
    int insertSelective(ItemStockDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table item_stock
     *
     * @mbg.generated Tue Apr 16 10:02:00 CST 2019
     */
    ItemStockDO selectByPrimaryKey(Integer id);

    ItemStockDO selectByItemId(Integer itemId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table item_stock
     *
     * @mbg.generated Tue Apr 16 10:02:00 CST 2019
     */
    int updateByPrimaryKeySelective(ItemStockDO record);


    int decreaseStock(@Param("itemId") Integer itemId,@Param("amount") Integer amount);
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table item_stock
     *
     * @mbg.generated Tue Apr 16 10:02:00 CST 2019
     */
    int updateByPrimaryKey(ItemStockDO record);
}