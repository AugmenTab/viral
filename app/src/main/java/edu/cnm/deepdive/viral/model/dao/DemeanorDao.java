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

/**
 * An interface containing various methods for interacting with the database table for the
 * {@link Demeanor} object.
 */
@Dao
public interface DemeanorDao {

  String SELECT_DEMEANOR_BY_INFECTION_LEVEL_QUERY =
      "SELECT * "
          + "FROM Demeanor "
          + "WHERE infection_min >= :min AND infection_max <= :max "
          + "ORDER BY infection_min ASC";

  /**
   * Inserts a single Demeanor.
   *
   * @param demeanor The {@link Demeanor} to be inserted.
   * @return A {@code Single} containing a {@code Long}.
   */
  @Insert
  Single<Long> insert(Demeanor demeanor);

  /**
   * Inserts several demeanors passed via varargs.
   *
   * @param demeanors Varargs of {@link Demeanor} to be inserted.
   * @return A {@code Single} {@code List} of {@code Long}.
   */
  @Insert
  Single<List<Long>> insert(Demeanor... demeanors);

  /**
   * Inserts a collection of demeanors.
   *
   * @param demeanors A {@code Collection} of {@link Demeanor} to be inserted.
   * @return A {@code Single} {@code List} of {@code Long}.
   */
  @Insert
  Single<List<Long>> insert(Collection<Demeanor> demeanors);

  /**
   * Updates a single Demeanor.
   *
   * @param demeanor
   * @return A {@code Single} containing an {@code Integer}.
   */
  @Update
  Single<Integer> update(Demeanor demeanor);

  /**
   * Updates several demeanors passed via varargs.
   *
   * @param demeanors Varargs of {@link Demeanor} to be updated.
   * @return A {@code Single} containing an {@code Integer}.
   */
  @Update
  Single<Integer> update(Demeanor... demeanors);

  /**
   * Updates a collection of demeanors.
   *
   * @param demeanors A {@code Collection} of {@link Demeanor} updated.
   * @return A {@code Single} containing an {@code Integer}.
   */
  @Update
  Single<Integer> update(Collection<Demeanor> demeanors);

  /**
   * Query to provide a specific demeanor.
   *
   * @param id The ID of the {@link Demeanor}.
   * @return A {@code LiveData} containing the {@link Demeanor}.
   */
  @Query("SELECT * FROM Demeanor WHERE demeanor_id = :id")
  LiveData<Demeanor> selectSpecificDemeanor(long id);

  /**
   * Query to provide a demeanor by its name.
   *
   * @param name The name of the {@link Demeanor}.
   * @return A {@code LiveData} containing the {@link Demeanor}.
   */
  @Query("SELECT * FROM Demeanor WHERE name = :name")
  LiveData<Demeanor> selectDemeanorByName(String name);

  /**
   * Query to provide a demeanor by its name, for use outside the view model.
   *
   * @param name The name of the {@link Demeanor}.
   * @return A {@code Single} containing the {@link Demeanor}.
   */
  @Query("SELECT * FROM Demeanor WHERE name = :name")
  Single<Demeanor> selectDemeanorByNameSync(String name);

  /**
   * Query to provide demeanors that fall within an infection range.
   *
   * @param min The minimum infection level value for the {@link Demeanor}.
   * @param max The maximum infection level value for the {@link Demeanor}.
   * @return A {@code LiveData} containing a {@code List} of {@link Demeanor} objects.
   */
  @Query(SELECT_DEMEANOR_BY_INFECTION_LEVEL_QUERY)
  LiveData<List<Demeanor>> selectDemeanorsByInfectionLevel(int min, int max);

  /**
   * Query to provide demeanors that fall within an infection range, for use outside the view model.
   *
   * @param min The minimum infection level value for the {@link Demeanor}.
   * @param max The maximum infection level value for the {@link Demeanor}.
   * @return A {@code Single} containing a {@code List} of {@link Demeanor} objects.
   */
  @Query(SELECT_DEMEANOR_BY_INFECTION_LEVEL_QUERY)
  Single<List<Demeanor>> selectDemeanorsByInfectionLevelSync(int min, int max);

}
