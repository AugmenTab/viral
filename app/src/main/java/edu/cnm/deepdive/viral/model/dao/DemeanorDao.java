package edu.cnm.deepdive.viral.model.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import edu.cnm.deepdive.viral.model.entity.Demeanor;
import io.reactivex.Single;
import java.util.Collection;
import java.util.List;

@Dao
public interface DemeanorDao {

  String SELECT_DEMEANOR_BY_INFECTION_LEVEL_QUERY =
      "SELECT * "
          + "FROM Demeanor "
          + "WHERE infection_min >= :min AND infection_max <= :max "
          + "ORDER BY infection_min ASC";

  @Insert
  Single<Long> insert(Demeanor demeanor);

  @Insert
  Single<List<Long>> insert(Demeanor... demeanors);

  @Insert
  Single<List<Long>> insert(Collection<Demeanor> demeanors);

  @Update
  Single<Integer> update(Demeanor demeanor);

  @Update
  Single<Integer> update(Demeanor... demeanors);

  @Update
  Single<Integer> update(Collection<Demeanor> demeanors);

  @Query("SELECT * FROM Demeanor WHERE demeanor_id = :id")
  LiveData<Demeanor> selectSpecificDemeanor(long id);

  @Query("SELECT * FROM Demeanor WHERE name = :name")
  LiveData<Demeanor> selectDemeanorByName(String name);

  @Query("SELECT * FROM Demeanor WHERE name = :name")
  Demeanor selectDemeanorByNameSync(String name);

  @Query(SELECT_DEMEANOR_BY_INFECTION_LEVEL_QUERY)
  LiveData<List<Demeanor>> selectDemeanorsByInfectionLevel(int min, int max);

  @Query(SELECT_DEMEANOR_BY_INFECTION_LEVEL_QUERY)
  List<Demeanor> selectDemeanorsByInfectionLevelSync(int min, int max);

}
