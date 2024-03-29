package com.ray.dao;

import com.ray.dataobject.PromoDO;
import org.springframework.stereotype.Repository;

@Repository
public interface PromoDOMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table promo
     *
     * @mbg.generated Fri Apr 19 15:25:38 CST 2019
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table promo
     *
     * @mbg.generated Fri Apr 19 15:25:38 CST 2019
     */
    int insert(PromoDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table promo
     *
     * @mbg.generated Fri Apr 19 15:25:38 CST 2019
     */
    int insertSelective(PromoDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table promo
     *
     * @mbg.generated Fri Apr 19 15:25:38 CST 2019
     */
    PromoDO selectByPrimaryKey(Integer id);


    PromoDO selectByItemId(Integer itemId);
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table promo
     *
     * @mbg.generated Fri Apr 19 15:25:38 CST 2019
     */
    int updateByPrimaryKeySelective(PromoDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table promo
     *
     * @mbg.generated Fri Apr 19 15:25:38 CST 2019
     */
    int updateByPrimaryKey(PromoDO record);
}