package com.zhang.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhang.mapper.Usermapper;
import com.zhang.mapper.Yuangongmapper;
import com.zhang.pojo.User;
import com.zhang.pojo.Yuanggong;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;

@Service("UserService")
public class UserServiceImpl implements UserService {
  @Autowired
  Usermapper userMapper;
  @Autowired
  Yuangongmapper yuangongmapper;

  @Override
  public User Userquerdernyname(String username) {
    return userMapper.Userquerdernyname(username);
  }

  @Override
  public List<Yuanggong> queryStaffList() {
    List<Yuanggong> staff = userMapper.queryList();
    return staff;
  }

  @Override
  public int insert(Yuanggong yuanggong) {
    return userMapper.insert(yuanggong);
  }

  @Override
  public int update(Yuanggong yuanggong) {
    return userMapper.update(yuanggong);
  }

  @Override
  public int delete(Integer staffid) {
    return userMapper.delete(staffid);
  }

  /*  @Override
    public List<Yuanggong> Sousuochaxgu(String staffname, String sex, String namsd, String dianhuan, String dizhi) {
        return userMapper.Sousuochaxgu(staffname, sex, namsd, dianhuan, dizhi);
    }*/

  @Override
  public Yuanggong yuangongid(Integer staffid) {
    return userMapper.yuangongid(staffid);
  }

  @Override
  public LinkedHashMap<String, Object> select(int page, int limit) {
    QueryWrapper<Yuanggong> queryWrapper = new QueryWrapper<Yuanggong>();
    Page<Yuanggong> pages = new Page<Yuanggong>(page, limit);
    IPage<Yuanggong> iPage = yuangongmapper.selectPageVo(pages, queryWrapper);
    List<Yuanggong> list = iPage.getRecords();
    long count = iPage.getTotal();
    LinkedHashMap<String, Object> linkedHashMap = new LinkedHashMap<String, Object>();
    linkedHashMap.put("code", 0);
    linkedHashMap.put("msg", "");
    linkedHashMap.put("count", count);
    linkedHashMap.put("data", list);
    return linkedHashMap;
  }

  @Override
  public LinkedHashMap<String, Object> selectchaxun(int page, int limit, String staffname, String sex, String namsd, String dianhuan, String dizhi) {
    QueryWrapper<Yuanggong> queryWrapper = new QueryWrapper<Yuanggong>();
    Page<Yuanggong> pages = new Page<Yuanggong>(page, limit);
    IPage<Yuanggong> iPage = yuangongmapper.Sousuochaxgu(pages, queryWrapper, staffname, sex, namsd, dianhuan, dizhi);
    List<Yuanggong> list = iPage.getRecords();
    long count = iPage.getTotal();
    LinkedHashMap<String, Object> linkedHashMap = new LinkedHashMap<String, Object>();
    linkedHashMap.put("code", 0);
    linkedHashMap.put("msg", "");
    linkedHashMap.put("count", count);
    linkedHashMap.put("data", list);
    return linkedHashMap;
  }


}
