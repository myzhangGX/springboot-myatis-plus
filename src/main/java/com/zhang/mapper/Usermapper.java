package com.zhang.mapper;

import com.zhang.pojo.User;
import com.zhang.pojo.Yuanggong;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface Usermapper {
  List<User> queryUserList();

  User Userquerdernyname(String username);

  List<Yuanggong> queryList();

  int insert(Yuanggong record);

  int update(Yuanggong record);

  Yuanggong yuangongid(Integer staffid);

  int delete(Integer staffid);

  /* List<Yuanggong> Sousuochaxgu(@Param("staffname")String staffname,@Param("sex")String sex,@Param("namsd")String namsd,@Param("dianhuan")String dianhuan,@Param("dizhi")String dizhi);*/
}
