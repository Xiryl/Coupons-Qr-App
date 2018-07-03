package it.chiarani.qrcoupons.models;

import java.util.Date;

public class QRItem {

  // private fileds
  private int    idItem;
  private String name;
  private String description;
  private Date   createdDate;
  private Date   expirationDate;
  private String code;
  private String uri;


  // empty constructor
  public QRItem () {

  }

  public QRItem(int idItem, String name, String description, Date createdDate, Date expirationDate, String code, String uri) {
    this.idItem         = idItem;
    this.name           = name;
    this.description    = description;
    this.createdDate    = createdDate;
    this.expirationDate = expirationDate;
    this.code           = code;
    this.uri            = uri;
  }



}
