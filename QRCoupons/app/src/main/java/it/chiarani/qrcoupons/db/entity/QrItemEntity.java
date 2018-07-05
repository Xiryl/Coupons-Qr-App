package it.chiarani.qrcoupons.db.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity (tableName = "qr_table")
public class QrItemEntity {

  // -- FIEDS --
  @PrimaryKey (autoGenerate = true)
  @NonNull
  private int    idItem;
  @NonNull
  private String name;
  @NonNull
  private String description;
  @NonNull
  private String   createdDate;

  private String   expirationDate;
  private String code;
  private String uri;

  // -- GETTER METHODS --
  @NonNull
  public int getIdItem() {
    return  this.idItem;
  }

  @NonNull
  public String getName() {
    return this.name;
  }

  @NonNull
  public String getDescription() {
    return this.description;
  }

  @NonNull
  public String getCreatedDate() {
    return this.createdDate;
  }

  @NonNull
  public String getExpirationDate() {
    return this.expirationDate;
  }

  @NonNull
  public String getCode() {
    return this.code;
  }

  @NonNull
  public String getUri() {
    return this.uri;
  }

  // -- SETTER METHODS --
  @NonNull
  public void setIdItem(int idItem) {
    this.idItem = idItem;
  }

  @NonNull
  public void setName(String name) {
     this.name = name;
  }

  @NonNull
  public void setDescription(String description) {
     this.description = description;
  }

  @NonNull
  public void setCreatedDate(String createdDate) {
     this.createdDate = createdDate;
  }

  @NonNull
  public void setExpirationDate(String expirationDate) {
    this.expirationDate = expirationDate;
  }

  @NonNull
  public void gsetCode(String code) {
     this.code = code;
  }

  @NonNull
  public void setUri(String uri) {
     this.uri = uri;
  }

  public QrItemEntity(@NonNull  int idItem,@NonNull  String name,@NonNull  String description,@NonNull  String createdDate, String expirationDate, String code, String uri) {
    this.idItem         = idItem;
    this.name           = name;
    this.description    = description;
    this.createdDate    = createdDate;
    this.expirationDate = expirationDate;
    this.code           = code;
    this.uri            = uri;
  }
}
