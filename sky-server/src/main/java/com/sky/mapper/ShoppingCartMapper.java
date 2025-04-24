package com.sky.mapper;

import com.sky.entity.ShoppingCart;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ShoppingCartMapper {
//    @Select("select * from sky_take_out.shopping_cart")
//    List<ShoppingCart> list();

    @Insert("insert into sky_take_out.shopping_cart (name, image, user_id, dish_id, setmeal_id, dish_flavor, amount, " +
            "create_time) values " +
            "(#{name}, #{image}, #{userId}, #{dishId}, #{setmealId}, #{dishFlavor}, #{amount}, #{createTime})")
    void insert(ShoppingCart shoppingCart);


    List<ShoppingCart> list(ShoppingCart shoppingCart);

//    void upate(ShoppingCart cart);

    @Update("update sky_take_out.shopping_cart set number = #{number} where id = #{id}")
    void updateNumberById(ShoppingCart cart);

    @Delete("delete from sky_take_out.shopping_cart where user_id = #{userId};")
    void deleteByUserId(Long userId);

    @Delete("DELETE from sky_take_out.shopping_cart where id = #{id}")
    void deleteById(ShoppingCart cart);


    void insertBatch(List<ShoppingCart> shoppingCartList);
}
