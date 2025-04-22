package com.sky.mapper;

import com.sky.entity.ShoppingCart;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface ShoppingCartMapper {
    @Insert("insert into sky_take_out.shopping_cart (name, image, user_id, dish_id, setmeal_id, dish_flavor, amount, " +
            "create_time) values " +
            "(#{name}, #{image}, #{userId}, #{dishId}, #{setmealId}, #{dishFlavor}, #{amount}, #{createTime})")
    void insert(ShoppingCart shoppingCart);


    List<ShoppingCart> list(ShoppingCart shoppingCart);

//    void upate(ShoppingCart cart);

    @Update("update sky_take_out.shopping_cart set number = #{number} where id = #{id}")
    void upateNumberById(ShoppingCart cart);
}
