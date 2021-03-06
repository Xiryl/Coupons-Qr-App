package it.chiarani.qrcoupons.repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.Executors;

import it.chiarani.qrcoupons.db.QrItemDatabase;
import it.chiarani.qrcoupons.db.dao.QrItemDao;
import it.chiarani.qrcoupons.db.entity.QrItemEntity;

public class QrItemRepository {
  private QrItemDao                    qrItemDao;   /* DAO OBJ */
  private LiveData<List<QrItemEntity>> qrItemsEntry;/* ENTRY LIVEDATA */

  public QrItemRepository(Application application) {
    QrItemDatabase db = QrItemDatabase.getDatabase(application);
    qrItemDao         = db.qrItemDao();
    qrItemsEntry      = qrItemDao.getAll();
  }

  public LiveData<List<QrItemEntity>> getAll() {
    return qrItemsEntry;
  }

  public void deleteAll() {
    // start on a new thread
    Executors.newSingleThreadExecutor().execute(() -> {
      qrItemDao.deleteAll();
    });
  }

  public void deleteById(String pos) {
    // start on a new thread
    Executors.newSingleThreadExecutor().execute(() -> {
      qrItemDao.deleteById(pos);
    });
  }

  public void deleteByNameDescriptionCode(String name, String description, String code){
    Executors.newSingleThreadExecutor().execute(() -> {
      qrItemDao.deleteByNameDescriptionCode(name, description, code);
    });
  }

  public void insertIt(final QrItemEntity entity) {
    // start on a new thread
    Executors.newSingleThreadExecutor().execute(() -> {
      qrItemDao.insert(entity);
    });
  }
}


