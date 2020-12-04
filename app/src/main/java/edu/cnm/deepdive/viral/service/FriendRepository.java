package edu.cnm.deepdive.viral.service;

import android.content.Context;
import androidx.lifecycle.LiveData;
import edu.cnm.deepdive.viral.generator.ActionGenerator;
import edu.cnm.deepdive.viral.generator.FriendGenerator;
import edu.cnm.deepdive.viral.model.dao.ActionTakenDao;
import edu.cnm.deepdive.viral.model.dao.FriendDao;
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

/**
 * The repository class for the {@link FriendDao}, allowing the view model to access the
 * methods contained within the DAO.
 */
public class FriendRepository {

  private final Context context;
  private final FriendDao friendDao;
  private final ActionTakenDao actionTakenDao;
  private final ActionRepository actionRepository;
  private final DemeanorRepository demeanorRepository;

  /**
   * The constructor initializes the context, the {@link FriendDao}, the {@link ActionTakenDao},
   * the {@link ActionRepository}, and the {@link DemeanorRepository}.
   *
   * @param context The application context.
   */
  public FriendRepository(Context context) {
    this.context = context;
    friendDao = ViralDatabase.getInstance().getFriendDao();
    actionTakenDao = ViralDatabase.getInstance().getActionTakenDao();
    actionRepository = new ActionRepository(context);
    demeanorRepository = new DemeanorRepository(context);
  }

  /**
   * Provides a list of all friends in the database.
   *
   * @return A {@code LiveData} containing a {@code List} of {@link Friend} objects.
   */
  public LiveData<List<Friend>> getAll() {
    return friendDao.selectAll();
  }

  /**
   * Provides a list of friends that are active in the friends list.
   *
   * @return A {@code LiveData} containing a {@code List} of {@link Friend} objects.
   */
  public LiveData<List<Friend>> getAllRemaining() {
    return friendDao.selectAllRemaining(true);
  }

  /**
   * Provides a list of all friends that are active in the friends list, for use outside the
   * view model.
   *
   * @return A {@code Single} containing a {@code List} of {@link Friend} objects.
   */
  public Single<List<Friend>> getAllRemainingSync() {
    return friendDao.selectAllRemainingSync(true);
  }

  /**
   * Provides a list of posts made by a specific friend.
   *
   * @param id The ID of the {@link Friend} who made the posts.
   * @return A {@code LiveData} containing a {@code List} of {@link ActionTaken} objects.
   */
  public LiveData<List<ActionTaken>> getPostsByFriend(long id) {
    return actionTakenDao.selectPostsByFriend(id);
  }

  /**
   * Provides a list of messages sent by a specific friend.
   *
   * @param id The ID of the {@link Friend} who sent the messages.
   * @return A {@code LiveData} containing a {@code List} of {@link ActionTaken} objects.
   */
  public LiveData<List<ActionTaken>> getMessagesByFriend(long id) {
    return actionTakenDao.selectMessagesByFriend(id);
  }

  /**
   * Generates a number of new {@link Friend} objects for the game, and inserts them into the
   * database.
   *
   * @param context The application context.
   * @param startingFriends The number of friends to generate on new game.
   * @return The result of the attempt as a {@code Completable}.
   */
  public Completable createFriendsListInDatabase(Context context, int startingFriends) {
    return Single.fromCallable(() -> new FriendGenerator(context))
        .flatMap((generator) -> demeanorRepository.getDemeanorsByInfectionLevelSync(0, 2)
            .map((demeanors) -> generator.makeFriends(startingFriends, demeanors))
        )
        .flatMap(friendDao::insert)
        .ignoreElement();
  }

  /**
   * Generates a number of new {@link ActionTaken} objects and inserts them into the database.
   *
   * @param rng An instance of {@code Random}.
   * @param postsToMake The number of posts to generate.
   * @return The result of the attempt as a {@code Completable}.
   */
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

}
