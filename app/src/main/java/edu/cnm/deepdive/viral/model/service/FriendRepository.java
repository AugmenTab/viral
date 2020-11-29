package edu.cnm.deepdive.viral.model.service;

import android.content.Context;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import edu.cnm.deepdive.viral.model.dao.ActionTakenDao;
import edu.cnm.deepdive.viral.model.dao.FriendDao;
import edu.cnm.deepdive.viral.model.entity.ActionTaken;
import edu.cnm.deepdive.viral.model.entity.Friend;
import java.util.List;

public class FriendRepository {

  private final Context context;
  private final FriendDao friendDao;
  private final ActionTakenDao actionTakenDao;

  public FriendRepository(Context context) {
    this.context = context;
    friendDao = ViralDatabase.getInstance().getFriendDao();
    actionTakenDao = ViralDatabase.getInstance().getActionTakenDao();
  }

  public LiveData<List<Friend>> getAll() {
    return friendDao.selectAllRemaining(false /* TODO Change back to true. */);
  }

  public LiveData<List<ActionTaken>> getMessagesByFriend(long id) {
    return actionTakenDao.selectMessagesByFriend(id);
  }

}
