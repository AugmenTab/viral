package edu.cnm.deepdive.viral.model.service;

import android.content.Context;
import androidx.lifecycle.LiveData;
import edu.cnm.deepdive.viral.model.dao.DemeanorDao;
import edu.cnm.deepdive.viral.model.entity.Demeanor;
import io.reactivex.Single;
import java.util.List;

public class DemeanorRepository {

  private final Context context;
  private final DemeanorDao demeanorDao;

  public DemeanorRepository(Context context, DemeanorDao demeanorDao) {
    this.context = context;
    ViralDatabase database = ViralDatabase.getInstance();
    demeanorDao = database.getDemeanorDao();
  }

  public LiveData<Demeanor> getDemeanor(long id) {
    return demeanorDao.selectSpecificDemeanor(id);
  }

  public LiveData<List<Demeanor>> getAvailableDemeanors(int min, int max) {
    return demeanorDao.selectDemeanorsByInfectionLevel(min, max);
  }

  public Single<Long> createDemeanor(Demeanor demeanor) {
    return demeanorDao.insert(demeanor);
  }

  public Single<Integer> updateDemeanor(Demeanor demeanor) {
    return demeanorDao.update(demeanor);
  }

  // TODO: Ask if delete is necessary here. Not sure if Demeanors will be deleted, or remain in the
  //  database forever.

}
