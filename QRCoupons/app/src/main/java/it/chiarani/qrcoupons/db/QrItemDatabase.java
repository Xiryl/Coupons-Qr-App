package it.chiarani.qrcoupons.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import it.chiarani.qrcoupons.db.dao.QrItemDao;
import it.chiarani.qrcoupons.db.entity.QrItemEntity;

@Database(entities = {QrItemEntity.class}, version = 1, exportSchema = false)
public abstract class QrItemDatabase extends RoomDatabase {

  public abstract QrItemDao qrItemDao();

  private static QrItemDatabase INSTANCE;


  static QrItemDatabase getDatabase(final Context context) {
    if (INSTANCE == null) {
      synchronized (QrItemDatabase.class) {
        if (INSTANCE == null) {
          INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
              QrItemDatabase.class, "qr_table")
              .build();

        }
      }
    }
    return INSTANCE;
  }

}