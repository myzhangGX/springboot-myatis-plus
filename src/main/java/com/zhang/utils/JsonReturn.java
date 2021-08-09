package com.zhang.utils;

import java.io.Serializable;

public class JsonReturn implements Serializable {
  /**
   *
   */
  private static final long serialVersionUID = 5863100019332084445L;
  private Boolean state;
  private String msg;
  private int id;
  private String dentitycard;
  private String certificatetype;
  private String xingbie;
  private String kimslhao;

  public JsonReturn() {
    super();
    // TODO Auto-generated constructor stub
  }

  public String getKimslhao() {
    return kimslhao;
  }

  public void setKimslhao(String kimslhao) {
    this.kimslhao = kimslhao;
  }

  public String getXingbie() {
    return xingbie;
  }

  public void setXingbie(String xingbie) {
    this.xingbie = xingbie;
  }

  public String getCertificatetype() {
    return certificatetype;
  }

  public void setCertificatetype(String certificatetype) {
    this.certificatetype = certificatetype;
  }

  public String getDentitycard() {
    return dentitycard;
  }

  public void setDentitycard(String dentitycard) {
    this.dentitycard = dentitycard;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public Boolean getState() {
    return state;
  }

  public void setState(Boolean state) {
    this.state = state;
  }

  public String getMsg() {
    return msg;
  }

  public void setMsg(String msg) {
    this.msg = msg;
  }


  public void toString(Integer userid) {
    // TODO Auto-generated method stub

  }

}
