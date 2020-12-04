package edu.cnm.deepdive.viral.service;

import android.content.Context;
import androidx.lifecycle.LiveData;
import edu.cnm.deepdive.viral.model.dao.ActionDao;
import edu.cnm.deepdive.viral.model.dao.DemeanorDao;
import edu.cnm.deepdive.viral.model.entity.Demeanor;
import edu.cnm.deepdive.viral.model.pojo.DemeanorWithActions;
import io.reactivex.Completable;
import io.reactivex.Single;
import java.util.List;

/**
 * The repository class for the {@link Demeanor}, allowing the view model to access the
 * methods contained within the DAO.
 */
public class DemeanorRepository {

  private final Context context;
  private final DemeanorDao demeanorDao;
  private final ActionDao actionDao;

  /**
   * The constructor initializes the context, the {@link DemeanorDao}, and the {@link ActionDao}.
   *
   * @param context The application context.
   */
  public DemeanorRepository(Context context) {
    this.context = context;
    ViralDatabase database = ViralDatabase.getInstance();
    demeanorDao = database.getDemeanorDao();
    actionDao = database.getActionDao();
  }

  /**
   * Provides a specific demeanor based on its ID.
   *
   * @param id The ID of the {@link Demeanor} object.
   * @return A {@code LiveData} containing a {@link Demeanor} object.
   */
  public LiveData<Demeanor> getSpecificDemeanor(long id) {
    return demeanorDao.selectSpecificDemeanor(id);
  }

  /**
   * Provides a list of demeanors within a range of infection levels.
   *
   * @param min The minimum infection level of the {@link Demeanor}.
   * @param max The maximum infection level of the {@link Demeanor}.
   * @return A {@code LiveData} containing a {@code List} of {@link Demeanor} objects.
   */
  public LiveData<List<Demeanor>> getDemeanorsByInfectionLevel(int min, int max) {
    return demeanorDao.selectDemeanorsByInfectionLevel(min, max);
  }

  /**
   * Provides a list of demeanors within a range of infection levels, for use outside the view
   * model.
   *
   * @param min The minimum infection level of the {@link Demeanor}.
   * @param max The maximum infection level of the {@link Demeanor}.
   * @return A {@code Single} containing a {@code List} of {@link Demeanor} objects.
   */
  public Single<List<Demeanor>> getDemeanorsByInfectionLevelSync(int min, int max) {
    return demeanorDao.selectDemeanorsByInfectionLevelSync(min, max);
  }

  /**
   * Provides a demeanor with a specific name.
   *
   * @param name The String name of the demeanor.
   * @return A {@code LiveData} containing a {@link Demeanor} object.
   */
  public Single<Demeanor> getDemeanorByNameSync(String name) {
    return demeanorDao.selectDemeanorByNameSync(name);
  }

  /**
   * Provides a list of actions associated with a particular demeanor.
   *
   * @param id The ID of the {@link Demeanor}.
   * @return A {@code LiveData} containing a {@link DemeanorWithActions} object.
   */
  public LiveData<DemeanorWithActions> getAllDemeanorActions(long id) {
    return actionDao.selectAllDemeanorActions(id);
  }

  /**
   * Saves a {@link Demeanor} record in the database.
   *
   * @param demeanor
   * @return The result of the attempt as a {@code Completable}.
   */
  public Completable save(Demeanor demeanor) {
    return (demeanor.getId() == 0)
        ? demeanorDao.insert(demeanor).doAfterSuccess(demeanor::setId).ignoreElement()
        : demeanorDao.update(demeanor).ignoreElement();
  }

}
