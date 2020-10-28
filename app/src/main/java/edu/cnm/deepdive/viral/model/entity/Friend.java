package edu.cnm.deepdive.viral.model.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(
    foreignKeys = @ForeignKey(
        entity = Demeanor.class,
        parentColumns = "demeanor_id",
        childColumns = "demeanor_id"
    ),
    indices = {
        @Index(value = "name", unique = true),
        @Index(value = "address", unique = true),
        @Index(value = "profile_picture", unique = true)
    }
)
public class Friend { // Rather than deleting Friends, maybe create boolean "active?"

  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "friend_id")
  private long id;

  @NonNull
  private String name;

  private int infectionLevel;

  private int demeanor;

  private boolean active;

  private int address;

  @NonNull
  private String profilePicture;

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

  public int getInfectionLevel() {
    return infectionLevel;
  }

  public void setInfectionLevel(int infectionLevel) {
    this.infectionLevel = infectionLevel;
  }

  public int getDemeanor() {
    return demeanor;
  }

  public void setDemeanor(int demeanor) {
    this.demeanor = demeanor;
  }

  public boolean isActive() {
    return active;
  }

  public void setActive(boolean active) {
    this.active = active;
  }

  public int getAddress() {
    return address;
  }

  public void setAddress(int address) {
    this.address = address;
  }

  public String getProfilePicture() {
    return profilePicture;
  }

  public void setProfilePicture(String profilePicture) {
    this.profilePicture = profilePicture;
  }

}
