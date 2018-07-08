package it.chiarani.qrcoupons.db.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import it.chiarani.qrcoupons.db.entity.QrItemEntity;
import it.chiarani.qrcoupons.models.QRItem;

@Dao
public interface QrItemDao {

  @Insert
  void insert(QrItemEntity qrItem);

  @Query("SELECT * FROM qr_table")
  LiveData<List<QrItemEntity>> getAll();
  
}
