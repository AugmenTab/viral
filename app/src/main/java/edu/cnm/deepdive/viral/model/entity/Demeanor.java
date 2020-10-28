package edu.cnm.deepdive.viral.model.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(indices = @Index(value = "name", unique = true))
public class Demeanor {

  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "demeanor_id")
  private long id;

  @NonNull
  private String name;

  @ColumnInfo(name = "starting_chance")
  private int startingChance;

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

  public int getStartingChance() {
    return startingChance;
  }

  public void setStartingChance(int startingChance) {
    this.startingChance = startingChance;
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

}
