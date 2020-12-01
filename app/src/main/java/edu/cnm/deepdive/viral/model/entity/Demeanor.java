package edu.cnm.deepdive.viral.model.entity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

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

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getInfectionMin() {
    return infectionMin;
  }

  public void setInfectionMin(int infectionMin) {
    this.infectionMin = infectionMin;
  }

  public int getInfectionMax() {
    return infectionMax;
  }

  public void setInfectionMax(int infectionMax) {
    this.infectionMax = infectionMax;
  }

  @Override
  public int hashCode() {
    return (int) (id * 37 + name.hashCode()); // Objects.hashCode(id, name); is equivalent
  }

  @Override
  public boolean equals(@Nullable Object obj) {
    boolean comparison;
    if (this == obj) {
      comparison = true;
    } else if (obj instanceof Demeanor) {
      Demeanor other = (Demeanor) obj;
      comparison = this.id == other.id && (this.id == 0 || name.equals(other.name)); // TODO Remove "this" on this line only
    } else {
      comparison = false;
    }
    return comparison;
  }

}
