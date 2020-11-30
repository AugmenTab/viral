package edu.cnm.deepdive.viral.service;

import android.content.Context;
import androidx.lifecycle.LiveData;
import edu.cnm.deepdive.viral.model.dao.ActionDao;
import edu.cnm.deepdive.viral.model.dao.DemeanorDao;
import edu.cnm.deepdive.viral.model.entity.Demeanor;
import edu.cnm.deepdive.viral.model.pojo.DemeanorWithActions;
import io.reactivex.Completable;
import java.util.List;

public class DemeanorRepository {

  private final Context context;
  private final DemeanorDao demeanorDao;
  private final ActionDao actionDao;

  public DemeanorRepository(Context context) {
    this.context = context;
    ViralDatabase database = ViralDatabase.getInstance();
    demeanorDao = database.getDemeanorDao();
    actionDao = database.getActionDao();
  }

  public LiveData<Demeanor> getSpecificDemeanor(long id) {
    return demeanorDao.selectSpecificDemeanor(id);
  }

  public LiveData<List<Demeanor>> getDemeanorsByInfectionLevel(int min, int max) {
    return demeanorDao.selectDemeanorsByInfectionLevel(min, max);
  }

  public LiveData<DemeanorWithActions> getAllDemeanorActions(long id) {
    return actionDao.selectAllDemeanorActions(id);
  }

  public Completable save(Demeanor demeanor) {
    return (demeanor.getId() == 0)
        ? demeanorDao.insert(demeanor).doAfterSuccess(demeanor::setId).ignoreElement()
        : demeanorDao.update(demeanor).ignoreElement();
  }

}
