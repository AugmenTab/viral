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

@Dao
public interface FriendDao {

  @Insert
  Single<Long> insert(Friend friend);

  @Insert
  Single<List<Long>> insert(Friend... friends);

  @Insert
  Single<List<Long>> insert(Collection<Friend> friends);

  @Update
  Single<Integer> update(Friend friend);

  @Update
  Single<Integer> update(Friend... friends);

  @Update
  Single<Integer> update(Collection<Friend> friends);

  @Delete
  Single<Integer> delete(Friend friend);

  @Delete
  Single<Integer> delete(Friend... friends);

  @Delete
  Single<Integer> delete(Collection<Friend> friends);

  @Query("SELECT * FROM Friend WHERE friend_id = :id ORDER BY name ASC")
  LiveData<Friend> selectSpecificFriend(long id);

  @Query("SELECT * FROM Friend WHERE active = :active ORDER BY infection_level ASC")
  LiveData<List<Friend>> selectAllRemaining(boolean active);

  @Query("SELECT * FROM Friend WHERE infection_level > 0 AND active = :active ORDER BY infection_level DESC")
  LiveData<List<Friend>> selectInfectedFriends(boolean active);

  @Query("SELECT * FROM Friend WHERE infection_level = 3 AND active = :active")
  LiveData<List<Friend>> selectMaxInfected(boolean active);

}
