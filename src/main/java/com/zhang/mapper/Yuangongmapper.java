package com.zhang.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhang.pojo.Yuanggong;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface Yuangongmapper extends BaseMapper<Yuanggong> {
  IPage<Yuanggong> selectPageVo(Page<Yuanggong> page, Object o);

  IPage<Yuanggong> Sousuochaxgu(Page<?> page, Object o, String staffname, String sex, String namsd, String dianhuan, String dizhi);
}
