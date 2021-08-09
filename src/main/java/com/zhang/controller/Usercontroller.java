package com.zhang.controller;


import com.zhang.mapper.Usermapper;
import com.zhang.pojo.Yuanggong;
import com.zhang.service.UserService;
import com.zhang.utils.JsonReturn;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.LinkedHashMap;


@Controller
public class Usercontroller {
  @Autowired
  UserService userService;

  /* @GetMapping("/queryUserList")
   public List<User> queryUserList() {
       List<User> userList = usermapper.queryUserList();
       for (User user : userList) {
           System.out.println(user);
       }
       return userList;
   }*/
  @Autowired
  JavaMailSenderImpl javaMailSender;
  @Autowired
  private Usermapper usermapper;

  @RequestMapping({"/", "/longin"})
  public String login() {
    return "longin";
  }

  @RequestMapping({"/index"})
  public String index() {
    return "index";
  }

  /*登录*/
  @PostMapping("/index")//使用post提交隐藏参数
  public String login(String username, String password, Model model) {
    //获取一个用户
    Subject subject = SecurityUtils.getSubject();
    // 封装用户的登录数据
    UsernamePasswordToken token = new UsernamePasswordToken(username, password);
    try {
      subject.login(token);//执行登录的方法，如果没有异常就说明ok了
      model.addAttribute("msg", token.getUsername());
      return "index";/*隐藏后面的参数  redirect:/index.html*/
    } catch (UnknownAccountException e) {//用户名不存在
      model.addAttribute("msg", "用户名错误");
      return "longin";
    } catch (IncorrectCredentialsException e) {//密码不存在
      model.addAttribute("msg", "密码错误");
      return "/longin";
    }

  }

  @RequestMapping("/yuangong")
  private String yuangg() {
    return "yuangong/012";
  }

  @RequestMapping("/shiyan")
  private String shiyan() {
    return "yuangong/shiyan";
  }

  @RequestMapping("listCustomer")
  @ResponseBody
  public LinkedHashMap<String, Object> pageData(int page, int limit) {
    return userService.select(page, limit);
  }

  /**
   * @param yuanggong
   * @return
   */
  @ResponseBody /*这一个是只传参数给页面*/
  @RequestMapping(value = "/xiuzhengks", produces = {"application/json;charset=UTF-8"})
  private JsonReturn xinzhen(Yuanggong yuanggong) {
    JsonReturn jsonReturn = new JsonReturn();
    System.out.println(yuanggong.getSex() + "新增");
    int indeat = userService.insert(yuanggong);
    System.out.println(indeat + "新增");
    if (indeat > 0) {
      jsonReturn.setMsg("新增成功");
      jsonReturn.setId(6);
    } else {
      jsonReturn.setMsg("新增失败");
      jsonReturn.setId(2);
    }
    return jsonReturn;
  }

  /*数据回填*/

  /**
   * @param yuanggong
   * @return
   */
  @ResponseBody
  @RequestMapping(value = "/xiuguani", produces = {"application/json;charset=UTF-8"})
  private JsonReturn xiuguani(Yuanggong yuanggong) {
    JsonReturn jsonReturn = new JsonReturn();
    System.out.println(yuanggong.getSex() + "修改");
    int indeat = userService.update(yuanggong);
    System.out.println(indeat + "修改");
    if (indeat > 0) {
      jsonReturn.setMsg("修改成功");
      jsonReturn.setId(6);
    } else {
      jsonReturn.setMsg("修改失败");
      jsonReturn.setId(2);
    }
    return jsonReturn;
  }

  /*数据删除*/

  /**
   * @param staffid
   * @return
   */
  @ResponseBody
  @RequestMapping(value = "/yuangongchaxu", produces = {"application/json;charset=UTF-8"})
  private Yuanggong yuangongchaxu(Integer staffid) {
    System.out.println(staffid + "ID");
    Yuanggong yuanggong = userService.yuangongid(staffid);
    System.out.println(yuanggong + "付了款");
    return yuanggong;
  }

  /*模糊查询*/

  /**
   * @param staffid
   * @return
   */
  @ResponseBody
  @RequestMapping(value = "/shanchu", produces = {"application/json;charset=UTF-8"})
  private JsonReturn shanchu(Integer staffid) {
    JsonReturn jsonReturn = new JsonReturn();
    int indeat = userService.delete(staffid);
    System.out.println(indeat + "删除");
    if (indeat > 0) {
      jsonReturn.setMsg("删除成功");
      jsonReturn.setId(6);
      return jsonReturn;
    } else {
      jsonReturn.setMsg("删除失败");
      jsonReturn.setId(2);
      return jsonReturn;
    }
  }

  /**
   * @param limit
   * @param page
   * @param staffname
   * @param sex
   * @param namsd
   * @param dianhuan
   * @param dizhi
   * @return
   */

  @ResponseBody
  @RequestMapping(value = "/shuosuochaun", produces = {"application/json;charset=UTF-8"})
  public LinkedHashMap<String, Object> shuosuochaun(int page, int limit, String staffname, String sex, String namsd, String dianhuan, String dizhi) {
    return userService.selectchaxun(page, limit, staffname, sex, namsd, dianhuan, dizhi);
  }

  @RequestMapping("/youjiangfasong")
  @ResponseBody
  public void kksk(String youxiang, String text, String subject) throws MessagingException {
    /*String subject = "你好张先生";*/
    /*  String text = "<p style='color:red'>谢谢张先生学习Java系列课程</p>";*/
    System.out.println(text);
    String fasong = "1972724673@qq.com";
    senMail(true, subject, text, fasong, youxiang);
  }

  /*
   *
   * @param html
   * @param subject
   * @param text
   * @param fasong
   * @param jieshou
   * @throws MessagingException
   */
  public void senMail(Boolean html, String subject, String text, String fasong, String jieshou) throws MessagingException {

    System.out.println(html + subject + text + fasong + jieshou);
    //一个复杂的邮件
    MimeMessage mimeMessage = javaMailSender.createMimeMessage();

    //组装
    MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, html);

    // 正文
    helper.setSubject(subject);//发送标题
    helper.setText(text, true);//发送一个定义一个html的标签内容

    //附件
    helper.addAttachment("5746.jpg", new File("F:\\图片\\5746.jpg"));//File是磁盘的路径
    helper.addAttachment("9.jpg", new File("F:\\图片\\9.jpg"));

    //接收
    helper.setTo(jieshou);
    //发送的邮箱要和application.properties配置的一样
    helper.setFrom(fasong);

    javaMailSender.send(mimeMessage);
  }

}
