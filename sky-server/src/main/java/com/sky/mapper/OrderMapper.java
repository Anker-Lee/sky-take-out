package com.sky.mapper;

import com.sky.dto.OrdersPageQueryDTO;
import com.sky.entity.Orders;
import com.sky.vo.OrderStatisticsVO;
import com.sky.vo.OrderVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Mapper
public interface OrderMapper {
    void insert(Orders orders);

    /**
     * 根据订单号查询订单
     * @param orderNumber
     */
    @Select("select * from orders where number = #{orderNumber}")
    Orders getByNumber(String orderNumber);

    /**
     * 修改订单信息
     */
    void update(Orders orders);

    // 用于替换微信支付更新数据库状态的问题
    @Update("update orders set status = #{orderStatus}, pay_status = #{orderPaidStatus} ,checkout_time = #{checkOutTime} where number = #{orderNumber}")
    void updateStatus(Integer orderStatus, Integer orderPaidStatus, LocalDateTime checkOutTime, String orderNumber);

    List<OrderVO> pageQuery(OrdersPageQueryDTO ordersPageQueryDTO);

    @Select("select * from orders where id = #{id}")
    Orders getById(Long id);

    @Select("select count(*) from orders where status =#{status}")
    Integer countStatus(Integer status);

    @Select("select * from  orders where status = #{status} and order_time < #{orderTime}")
    List<Orders> getByStatusAndTimeLT(Integer status, LocalDateTime orderTime);

    @Select("select * from orders where number = #{orderNumber}")
    Orders getByOrderNumber(String orderNumber);

    Double sumByMap(Map map);


    Integer countUser(Map map);

    Integer countOrder(Map map);
}
