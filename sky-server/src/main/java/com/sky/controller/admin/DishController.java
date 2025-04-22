package com.sky.controller.admin;

import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.DishService;
import com.sky.vo.DishVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@Slf4j
@RestController
@RequestMapping("/admin/dish")
public class DishController {

    @Autowired
    DishService dishService;
    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    @PostMapping()
    public Result save(@RequestBody DishDTO dishDTO) {
        log.info("保存菜品：{}", dishDTO);
        dishService.saveWithFlavor(dishDTO);

        String key = "dish_" + dishDTO.getCategoryId();
        clearCache(key);

        return Result.success();
    }

    @GetMapping("/page")
    public Result<PageResult> pageQuery(DishPageQueryDTO dishPageQueryDTO) {
        log.info("菜品分页查询：{}", dishPageQueryDTO);
        PageResult pageResult = dishService.pageQuery(dishPageQueryDTO);
        return Result.success(pageResult);
    }

    @DeleteMapping()
    public Result deleteByIds(@RequestParam List<Long> ids) { // Spring 对集合类型（如 List）的支持更完善，推荐直接使用 List<Long> 作为参数类型。
        log.info("根据 id 批量删除数据: {}", ids);
        dishService.deleteByIds(ids);

        clearCache("dish_*");

        return Result.success();
    }

    @GetMapping("/{id}")
    public Result<DishVO> getById(@PathVariable Long id) {
        log.info("根据 id 查询菜品: {}", id);
        DishVO dishVO = dishService.getById(id);
        return Result.success(dishVO);
    }

    @PutMapping()
    public Result update(@RequestBody DishDTO dishDTO) {
        log.info("跟新菜品数据: {}", dishDTO);
        dishService.updateWithFlavor(dishDTO);

        clearCache("dish_*");

        return Result.success();
    }

    @GetMapping("/list")
    public Result<List<Dish>> list(Long categoryId) {
        log.info("根据分类 id 查询: {}", categoryId);
        List<Dish> dishes = dishService.list(categoryId);
        return Result.success(dishes);
    }

    @PostMapping("/status/{status}")
    public Result switchStatus(@PathVariable Integer status, Long id) {
        log.info("起售/停售菜品, status: {}, id: {}", status, id);
        dishService.switchStatus(status, id);

        clearCache("dish_*");

        return Result.success();
    }

    /**
     * 清除 Redis 缓存中所有数据
     * @param pattern
     */
    private void clearCache(String pattern) {
        Set<Object> keys = redisTemplate.keys(pattern);
        redisTemplate.delete(keys);
    }


}
