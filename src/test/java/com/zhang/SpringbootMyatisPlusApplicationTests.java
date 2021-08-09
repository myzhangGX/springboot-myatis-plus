package com.zhang;

import com.zhang.mapper.Yuangongmapper;
import com.zhang.pojo.Yuanggong;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@SpringBootTest
class SpringbootMyatisPlusApplicationTests {

  @Autowired
  Yuangongmapper yuangongmapper;
  @Autowired
  JavaMailSenderImpl javaMailSender;

  @Test
  void contextLoads() {
    for (int i = 1; i <= 9; i++) {
      for (int j = 1; j <= i; j++) {
        System.out.printf("%d*%d=%d  \t", j, i, j * i);
      }
      System.out.println("\n");
    }
  }

  //
  @Test
  void text1() {
    //参数是一个Wrapper , 条件构造器,这里我们先不用 --null
    //查询全部用户
    List<Yuanggong> users = yuangongmapper.selectList(null);
    users.forEach(System.out::println);
  }

  /*插入数据*/
  @Test
  public void testInsert() {
    Yuanggong user = new Yuanggong();
    user.setStaffname("codeyuaiiao");
    user.setSex("男");
    user.setNamsd("24");
    user.setDianhuan("747557612@qq.com");
    user.setDizhi("重庆");
    int result = yuangongmapper.insert(user);
    System.out.println(result);
    System.out.println(user);

  }

  //测试批量查询
  @Test
  public void testSelectBahId() {
    List<Yuanggong> users = yuangongmapper.selectBatchIds(Arrays.asList(4, 5));
    users.forEach(System.out::println);
  }

  @Test
  void delete() {
    int result = yuangongmapper.deleteById(3L);
    System.out.println(result + "删除");
  }

  //测试删除
  @Test
  public void testDeleteById() {
    yuangongmapper.deleteById(2L);
  }

  //通过id批量删除
  @Test
  public void testDeleteBatchId() {
    yuangongmapper.deleteBatchIds(Arrays.asList(4, 5));
  }

  //通过map删除
  @Test
  public void testDeleteMap() {
    HashMap<String, Object> map = new HashMap<>();
    map.put("staffname", "codeyuaiiao");
    yuangongmapper.deleteByMap(map);
  }

  /*邮箱发送*/
  /*一个简单的邮件*/
  @Test
  void context() {
    SimpleMailMessage message = new SimpleMailMessage();
    message.setSubject("你好张先生");
    message.setText("非常的感谢你的大力支持");

    /*接收*/
    message.setTo("1228632193@qq.com");
    /*发送的邮箱要和application.properties配置的一样*/
    message.setFrom("1972724673@qq.com");
    javaMailSender.send(message);
  }
}
