package com.zhang.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.sql.Blob;

/**
 * <p>
 *
 * </p>
 *
 * @author YiHui
 * @since 2021-05-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Userk implements Serializable {

  private static final long serialVersionUID = 1L;

  /**
   * 主键ID
   */
  private Long id;

  /**
   * 姓名
   */
  private String name;

  /**
   * 年龄
   */
  private Integer age;

  /**
   * 邮箱
   */
  private String email;

  /**
   * 图片
   */
  private Blob img;


}
