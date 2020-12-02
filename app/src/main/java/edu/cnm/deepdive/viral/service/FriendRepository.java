package edu.cnm.deepdive.viral.service;

import android.app.Application;
import android.content.Context;
import androidx.lifecycle.LiveData;
import edu.cnm.deepdive.viral.generator.FriendGenerator;
import edu.cnm.deepdive.viral.model.dao.ActionTakenDao;
import edu.cnm.deepdive.viral.model.dao.FriendDao;
import edu.cnm.deepdive.viral.model.entity.ActionTaken;
import edu.cnm.deepdive.viral.model.entity.Friend;
import io.reactivex.schedulers.Schedulers;
import java.io.IOException;
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
    return friendDao.selectAll();
  }

  public LiveData<List<Friend>> getAllRemaining() {
    return friendDao.selectAllRemaining(true);
  }

  public LiveData<List<ActionTaken>> getMessagesByFriend(long id) {
    return actionTakenDao.selectMessagesByFriend(id);
  }

  public void createFriendsListInDatabase(Application application, int n) throws IOException {
    FriendGenerator generator = new FriendGenerator(application);
    List<Friend> friends = generator.makeFriends(n);
    friendDao.insert(friends).subscribeOn(Schedulers.io()).subscribe();
  }

}
