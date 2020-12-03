package edu.cnm.deepdive.viral.model.entity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

/**
 * The entity class for demeanors that a {@link Friend} can have, and that an {@link Action} can be
 * associated with.
 */
@Entity(indices = @Index(value = {"name"}, unique = true))
@SuppressWarnings({"NullableProblems", "NotNullFieldNotInitialized"})
public class Demeanor {

  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "demeanor_id")
  private long id;

  @NonNull
  private String name;

  @ColumnInfo(name = "infection_min")
  private int infectionMin;

  @ColumnInfo(name = "infection_max")
  private int infectionMax;

  /**
   * Gets the ID of a demeanor.
   *
   * @return The ID.
   */
  public long getId() {
    return id;
  }

  /**
   * Sets the ID of a demeanor.
   *
   * @param id The new ID.
   */
  public void setId(long id) {
    this.id = id;
  }

  /**
   * Gets the name of the demeanor.
   *
   * @return The name of the demeanor.
   */
  public String getName() {
    return name;
  }

  /**
   * Sets the name of the demeanor.
   *
   * @param name The new name of the demeanor.
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Gets the minimum infection level for a demeanor.
   *
   * @return The minimum infection level for a demeanor.
   */
  public int getInfectionMin() {
    return infectionMin;
  }

  /**
   * Sets the minimum infection level for a demeanor.
   *
   * @param infectionMin The new minimum infection level for a demeanor.
   */
  public void setInfectionMin(int infectionMin) {
    this.infectionMin = infectionMin;
  }

  /**
   * Gets the maximum infection level for a demeanor.
   *
   * @return The maximum infection level for a demeanor.
   */
  public int getInfectionMax() {
    return infectionMax;
  }

  /**
   * Sets the maximum infection level for a demeanor.
   *
   * @param infectionMax The new maximum infection level for a demeanor.
   */
  public void setInfectionMax(int infectionMax) {
    this.infectionMax = infectionMax;
  }

  @Override
  public int hashCode() {
    return (int) (id * 37 + name.hashCode()); // Objects.hashCode(id, name); is equivalent
  }

  /**
   * Verifies if one demeanor object equals another.
   *
   * @param obj The demeanor to be compared.
   * @return The boolean denoting whether the objects were equal.
   */
  @Override
  public boolean equals(@Nullable Object obj) {
    boolean comparison;
    if (this == obj) {
      comparison = true;
    } else if (obj instanceof Demeanor) {
      Demeanor other = (Demeanor) obj;
      comparison = id == other.id && (id == 0 || name.equals(other.name));
    } else {
      comparison = false;
    }
    return comparison;
  }

}
