package edu.cnm.deepdive.viral.service;

import android.content.Context;
import androidx.lifecycle.LiveData;
import edu.cnm.deepdive.viral.model.dao.ActionDao;
import edu.cnm.deepdive.viral.model.entity.Action;
import io.reactivex.Single;
import java.util.List;

/**
 * The repository class for the {@link ActionDao}, allowing the view model to access the
 * methods contained within the DAO.
 */
public class ActionRepository {

  private final Context context;
  private final ActionDao actionDao;

  /**
   * The construction initializes the context and the Action DAO.
   *
   * @param context The application context.
   */
  public ActionRepository(Context context) {
    this.context = context;
    actionDao = ViralDatabase.getInstance().getActionDao();
  }

  /**
   * Provides a specific action based on an ID.
   *
   * @param id The ID for the {@link Action}.
   * @return A {@code LiveData} containing an {@link Action} object.
   */
  public LiveData<Action> getSpecificAction(long id) {
    return actionDao.selectSpecificAction(id);
  }

  /**
   * Provides a list of post actions, for use outside the view model.
   *
   * @param demeanor The ID of the {@link edu.cnm.deepdive.viral.model.entity.Demeanor}.
   * @return A {@code Single} {@code List} of {@link Action} objects.
   */
  public Single<List<Action>> getPostsSync(long demeanor) {
    return actionDao.selectActionsByVisibilitySync(true, demeanor);
  }

  /**
   * Provides a list of message actions, for use outside the view model.
   *
   * @param demeanor The ID of the {@link edu.cnm.deepdive.viral.model.entity.Demeanor}.
   * @return A {@code LiveData} containing a {@code List} of {@link Action} objects.
   */
  public LiveData<List<Action>> getMessages(long demeanor) {
    return actionDao.selectActionsByVisibility(false, demeanor);
  }

}
