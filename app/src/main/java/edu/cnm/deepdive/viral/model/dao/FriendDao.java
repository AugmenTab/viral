package edu.cnm.deepdive.viral.model.dao;

import androidx.room.Dao;
import androidx.room.Insert;
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

  // TODO Write queries.

}
