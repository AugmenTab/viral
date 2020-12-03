package edu.cnm.deepdive.viral.service;

import android.content.Context;
import androidx.lifecycle.LiveData;
import edu.cnm.deepdive.viral.model.dao.ActionDao;
import edu.cnm.deepdive.viral.model.entity.Action;
import io.reactivex.Single;
import java.util.List;

public class ActionRepository {

  private final Context context;
  private final ActionDao actionDao;

  public ActionRepository(Context context) {
    this.context = context;
    actionDao = ViralDatabase.getInstance().getActionDao();
  }

  public LiveData<Action> getSpecificAction(long id) {
    return actionDao.selectSpecificAction(id);
  }

  public LiveData<List<Action>> getPosts(long id) {
    return actionDao.selectActionsByVisibility(true, id);
  }

  public Single<List<Action>> getPostsSync(long demeanor) {
    return actionDao.selectActionsByVisibilitySync(true, demeanor);
  }

  public LiveData<List<Action>> getMessages(long id) {
    return actionDao.selectActionsByVisibility(false, id);
  }

}
