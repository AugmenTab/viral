package edu.cnm.deepdive.viral.service;

import android.content.Context;
import android.util.Log;
import androidx.lifecycle.LiveData;
import edu.cnm.deepdive.viral.generator.ActionGenerator;
import edu.cnm.deepdive.viral.generator.FriendGenerator;
import edu.cnm.deepdive.viral.model.dao.ActionTakenDao;
import edu.cnm.deepdive.viral.model.dao.FriendDao;
import edu.cnm.deepdive.viral.model.entity.Action;
import edu.cnm.deepdive.viral.model.entity.ActionTaken;
import edu.cnm.deepdive.viral.model.entity.Friend;
import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Single;
import io.reactivex.functions.Function;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

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

  public Single<List<Friend>> getAllRemainingSync() {
    return friendDao.selectAllRemainingSync(true);
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

  public Completable createFriendsListInDatabase(Context context, int startingFriends) {
    return Single.fromCallable(() -> new FriendGenerator(context))
        .flatMap((generator) -> demeanorRepository.getDemeanorsByInfectionLevelSync(0, 2)
            .map((demeanors) -> generator.makeFriends(startingFriends, demeanors))
        )
        .flatMap(friendDao::insert)
        .ignoreElement();
  }

  public Completable createPost(Random rng, int postsToMake) {
    ActionGenerator generator = new ActionGenerator();
    return getAllRemainingSync()
        .flatMapObservable((friends) -> {
          List<Friend> selection = new LinkedList<>();
          for (int i = 0; i < postsToMake; i++) {
            selection.add(friends.get(rng.nextInt(friends.size())));
          }
          return Observable.fromIterable(selection);
        })
        .map((friend) -> actionRepository.getPostsSync(friend.getDemeanor())
            .flatMap((actions) ->
                actionTakenDao.insert(generator.makePostOrMessage(friend, actions))))
        .concatMap(
            (Function<Single<Long>, ObservableSource<Long>>) Single::toObservable)
        .ignoreElements();
  }

  public void spreadInfection(Random rng) {

  }

}
