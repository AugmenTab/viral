package edu.cnm.deepdive.viral.model.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import edu.cnm.deepdive.viral.model.entity.Friend;
import io.reactivex.Single;
import java.util.Collection;
import java.util.List;

/**
 * An interface containing various methods for interacting with the database table for the
 * {@link Friend} object.
 */
@Dao
public interface FriendDao {

  /**
   * Inserts a single friend.
   *
   * @param friend The {@link Friend} to be inserted.
   * @return A {@code Single} containing a {@code Long}.
   */
  @Insert
  Single<Long> insert(Friend friend);

  /**
   * Inserts several friends passed via varargs.
   *
   * @param friends Varargs of {@link Friend} to be inserted.
   * @return A {@code Single} {@code List} of {@code Long}.
   */
  @Insert
  Single<List<Long>> insert(Friend... friends);

  /**
   * Inserts a collection of demeanors.
   *
   * @param friends A {@code Collection} of {@link Friend} to be inserted.
   * @return A {@code Single} {@code List} of {@code Long}.
   */
  @Insert
  Single<List<Long>> insert(Collection<Friend> friends);

  /**
   * Updates a single friend.
   *
   * @param friend The {@link Friend} to be updated.
   * @return A {@code Single} containing an {@code Integer}.
   */
  @Update
  Single<Integer> update(Friend friend);

  /**
   * Updates several friends passed via varargs.
   *
   * @param friends Varargs of {@link Friend} to be updated.
   * @return A {@code Single} containing an {@code Integer}.
   */
  @Update
  Single<Integer> update(Friend... friends);

  /**
   * Updates a collection of demeanors.
   *
   * @param friends A {@code Collection} of {@link Friend} to be updated.
   * @return A {@code Single} containing an {@code Integer}.
   */
  @Update
  Single<Integer> update(Collection<Friend> friends);

  /**
   * Deletes a single friend.
   *
   * @param friend The {@link Friend} to be deleted.
   * @return A {@code Single} containing an {@code Integer}.
   */
  @Delete
  Single<Integer> delete(Friend friend);

  /**
   * Deletes several friends passed via varargs.
   *
   * @param friends Varargs of {@link Friend} to be deleted.
   * @return A {@code Single} containing an {@code Integer}.
   */
  @Delete
  Single<Integer> delete(Friend... friends);

  /**
   * Deletes a collection of demeanors.
   *
   * @param friends A {@code Collection} of {@link Friend} to be deleted.
   * @return A {@code Single} containing an {@code Integer}.
   */
  @Delete
  Single<Integer> delete(Collection<Friend> friends);

  /**
   * Query to provide a specific friend.
   *
   * @param id The ID of the {@link Friend}.
   * @return A {@code LiveData} containing the {@link Friend}.
   */
  @Query("SELECT * FROM Friend WHERE friend_id = :id")
  LiveData<Friend> selectSpecificFriend(long id);

  /**
   * Query to provide all friends.
   *
   * @return A {@code LiveData} containing a {@code List} of {@link Friend} objects.
   */
  @Query("SELECT * FROM Friend")
  LiveData<List<Friend>> selectAll();

  /**
   * Query to provide all friends who have not been "deleted" by the user.
   *
   * @param active Whether or not the {@link Friend} has been "deleted" by the user.
   * @return A {@code LiveData} containing a {@code List} of {@link Friend} objects.
   */
  @Query("SELECT * FROM Friend WHERE active = :active ORDER BY infection_level ASC")
  LiveData<List<Friend>> selectAllRemaining(boolean active);

  /**
   * Query to provide all friends who have not been "deleted" by the user, for use outside the
   * view model.
   *
   * @param active Whether or not the {@link Friend} has been "deleted" by the user.
   * @return A {@code Single} containing a {@code List} of {@link Friend} objects.
   */
  @Query("SELECT * FROM Friend WHERE active = :active ORDER BY infection_level ASC")
  Single<List<Friend>> selectAllRemainingSync(boolean active);

  /**
   * Query to select all infected friends.
   *
   * @param active Whether or not the {@link Friend} has been "deleted" by the user.
   * @return A {@code LiveData} containing a {@code List} of {@link Friend} objects.
   */
  @Query("SELECT * FROM Friend WHERE infection_level > 0 AND active = :active ORDER BY infection_level DESC")
  LiveData<List<Friend>> selectInfectedFriends(boolean active);

  /**
   * Query to select friends infected at the highest possible infection level.
   *
   * @param active Whether or not the {@link Friend} has been "deleted" by the user.
   * @return A {@code LiveData} containing a {@code List} of {@link Friend} objects.
   */
  @Query("SELECT * FROM Friend WHERE infection_level = 3 AND active = :active")
  LiveData<List<Friend>> selectMaxInfected(boolean active);

}
