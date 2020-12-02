package edu.cnm.deepdive.viral.service;

import android.app.Application;
import android.content.Context;
import androidx.lifecycle.LiveData;
import edu.cnm.deepdive.viral.generator.ActionGenerator;
import edu.cnm.deepdive.viral.generator.FriendGenerator;
import edu.cnm.deepdive.viral.model.dao.ActionTakenDao;
import edu.cnm.deepdive.viral.model.dao.FriendDao;
import edu.cnm.deepdive.viral.model.entity.Action;
import edu.cnm.deepdive.viral.model.entity.ActionTaken;
import edu.cnm.deepdive.viral.model.entity.Demeanor;
import edu.cnm.deepdive.viral.model.entity.Friend;
import io.reactivex.schedulers.Schedulers;
import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;

public class FriendRepository {

  private final Context context;
  private final FriendDao friendDao;
  private final ActionTakenDao actionTakenDao;
  private final ActionRepository actionRepository;
  private final DemeanorRepository demeanorRepository;

  public FriendRepository(Context context) {
    this.context = context;
    friendDao = ViralDatabase.getInstance().getFriendDao();
    actionTakenDao = ViralDatabase.getInstance().getActionTakenDao();
    actionRepository = new ActionRepository(context);
    demeanorRepository = new DemeanorRepository(context);
  }

  public LiveData<List<Friend>> getAll() {
    return friendDao.selectAll();
  }

  public List<Friend> getAllRemainingSync() throws InterruptedException, ExecutionException {
    Callable<List<Friend>> callable = () -> friendDao.selectAllRemainingSync(true);
    return Executors.newSingleThreadExecutor().submit(callable).get();
  }

  public List<Friend> getAllRemainingUninfectedSync()
      throws InterruptedException, ExecutionException {
    Callable<List<Friend>> callable = () -> friendDao.selectUninfectedFriendsSync(true);
    return Executors.newSingleThreadExecutor().submit(callable).get();
  }

  public LiveData<List<Friend>> getAllRemaining() {
    return friendDao.selectAllRemaining(true);
  }


  public LiveData<List<ActionTaken>> getPostsByFriend(long id) {
    return actionTakenDao.selectPostsByFriend(id);
  }

  public LiveData<List<ActionTaken>> getMessagesByFriend(long id) {
    return actionTakenDao.selectMessagesByFriend(id);
  }

  public void createFriendsListInDatabase(Application application, int n) throws IOException {
    FriendGenerator generator = new FriendGenerator(application);
    List<Friend> friends = generator.makeFriends(n);
    friendDao.insert(friends).subscribeOn(Schedulers.io()).subscribe();
  }

  public void createPost(Random rng) {
    try {
      List<Friend> friends = getAllRemainingSync();
      Friend poster = friends.get(rng.nextInt(friends.size()));
      List<Action> actions = actionRepository.getPostsSync(poster.getDemeanor());
      ActionGenerator generator = new ActionGenerator();
      ActionTaken post = generator.makePost(poster, actions);
      actionTakenDao.insert(post).subscribeOn(Schedulers.io()).subscribe();
    } catch (InterruptedException | ExecutionException e) {
      e.printStackTrace();
    }
  }

  public boolean spreadInfection(Random rng) {
    try {
      List<Friend> friends = getAllRemainingSync();
      Friend infectee = friends.get(rng.nextInt(friends.size()));
      int infectionLevel = infectee.getInfectionLevel();
      if (++infectionLevel >= 3) {
        infectee.setDemeanor(demeanorRepository.getDemeanorByNameSync("infected").getId());
        spreadInfection(rng);
      } else {
        List<Demeanor> demeanors =
            demeanorRepository.getDemeanorsByInfectionLevelSync(0, infectionLevel);
        infectee.setDemeanor(demeanors.get(rng.nextInt(demeanors.size())).getId());
      }
      infectee.setInfectionLevel(infectionLevel);
      friendDao.update(infectee).subscribeOn(Schedulers.io()).subscribe();
      return checkIfAllInfected();
    } catch (InterruptedException | ExecutionException e) {
      e.printStackTrace();
    }
    return false;
  }

  private boolean checkIfAllInfected() {
    try {
      return !getAllRemainingUninfectedSync().isEmpty();
    } catch (InterruptedException | ExecutionException e) {
      e.printStackTrace();
    }
    return false;
  }

}
