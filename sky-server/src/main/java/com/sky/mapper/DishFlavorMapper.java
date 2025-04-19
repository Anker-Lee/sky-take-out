package com.sky.mapper;

import com.sky.annotation.AutoFill;
import com.sky.entity.DishFlavor;
import com.sky.enumeration.OperationType;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DishFlavorMapper {

    @Insert("insert into dish_flavor (dish_id, name, value) VALUE (#{dishId}, #{name}, #{value})")
    void insert(DishFlavor dishFlavor);

    void insertBatch(List<DishFlavor> flavors);
}
