package com.zhang.service;


import com.zhang.pojo.User;
import com.zhang.pojo.Yuanggong;

import java.util.LinkedHashMap;
import java.util.List;

public interface UserService {
  public User Userquerdernyname(String username);

  List<Yuanggong> queryStaffList();


  int insert(Yuanggong yuanggong);

  int update(Yuanggong yuanggong);

  int delete(Integer staffid);

  Yuanggong yuangongid(Integer staffid);


  /*    List<Yuanggong> Sousuochaxgu(String staffname, String sex, String namsd, String dianhuan, String dizhi);*/


  LinkedHashMap<String, Object> select(int page, int limit);

  LinkedHashMap<String, Object> selectchaxun(int page, int limit, String staffname, String sex, String namsd, String dianhuan, String dizhi);

}
