package com.zhang.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.Date;

@Service
public class Timer {
  /*在一个特定的一个时间执行这个方法 Timer*/
//cron表达式
  // 秒 分 时 日 月 周几
  //(cron = "0 29 10 * * ?")

  @Autowired
  JavaMailSenderImpl javaMailSender;

  /*
      0 49 11 * * ?   每天的11点49分00秒执行
      0 0/5 11,12 * * ?   每天的11点和12点每个五分钟执行一次
      0 15 10 ? * 1-6     每个月的周一到周六的10点15分执行一次
      0/2 * * * * ?     每2秒执行一次
   */
  @Scheduled(cron = "50 09 16 * * ?")
  public void hello() throws MessagingException {
    Date date = new Date();
    System.out.println("hello,被执行了张" + date);

    for (int i = 0; i < 2; i++) {
      System.out.println("你哈价");
    }

  }

  public void kksk(String youxiang) throws MessagingException {
    String subject = "你好张先生";
    String text = "<p style='color:red'>谢谢张先生学习Java系列课程</p>";
    String fasong = "1972724673@qq.com";
    String jieshou = "1904414592@qq.com";//965627837@qq.com
    senMail(true, subject, text, fasong, jieshou);
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
