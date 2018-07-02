package it.chiarani.qrcoupons.models;

import java.util.Date;

public class QRItem {

  // private fileds
  private int idItem;
  private String name;
  private String description;
  private Date createdDate;
  private Date expirationDate;
  private String code;
  private String uri;


  // constructor
  public QRItem () {

  }

  // add new item
  public boolean addQR() {
    return true;
  }
}
