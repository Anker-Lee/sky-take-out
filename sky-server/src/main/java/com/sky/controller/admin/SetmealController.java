package com.sky.controller.admin;

import com.sky.constant.StatusConstant;
import com.sky.dto.SetmealDTO;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.entity.Setmeal;
import com.sky.mapper.SetmealMapper;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.SetmealService;
import com.sky.vo.DishItemVO;
import com.sky.vo.SetmealVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/setmeal")
@Slf4j
public class SetmealController {
    @Autowired
    SetmealService setmealService;

    private final SetmealMapper setmealMapper;

    public SetmealController(SetmealMapper setmealMapper) {
        this.setmealMapper = setmealMapper;
    }

    @GetMapping("/page")
    public Result<PageResult> pageQuery(SetmealPageQueryDTO setmealPageQueryDTO) {
        log.info("套餐分页查询:{}", setmealPageQueryDTO);
        PageResult pageResult = setmealService.pageQuery(setmealPageQueryDTO);
        return Result.success(pageResult);
    }

    @PostMapping()
    @CacheEvict(cacheNames = "setmealCache", key = "#setmealDTO.categoryId")
    public Result save(@RequestBody SetmealDTO setmealDTO) {
        log.info("保存套餐: {}", setmealDTO);
        setmealService.saveWithDish(setmealDTO);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result<SetmealVO> getById(@PathVariable Long id) {
        log.info("根据 id 查询套餐: {}", id);
        SetmealVO setmealVO = setmealService.getByIdWithDish(id);
        return Result.success(setmealVO);
    }

    @PutMapping()
    @CacheEvict(cacheNames = "setmealCache", key = "#setmealDTO.categoryId")
    public Result update(@RequestBody SetmealDTO setmealDTO) {
        log.info("更新套餐: {}", setmealDTO);
        setmealService.update(setmealDTO);
        return Result.success();
    }

    @PostMapping("/status/{status}")
    @CacheEvict(cacheNames = "setmealCache", allEntries = true)
    public Result switchStatus(@PathVariable Integer status, Long id) {
        log.info("起售/停售套餐: {}, {}", status, id);
        setmealService.switchStatus(status, id);
        return Result.success();
    }

    @DeleteMapping()
    @CacheEvict(cacheNames = "setmealCache", allEntries = true)
    public Result deleteByIds(@RequestParam List<Long> ids) {
        log.info("根据 id 删除套餐, ids: {}", ids);
        setmealService.deleteByIdsWithSetmealDishes(ids);
        return Result.success();
    }



}
