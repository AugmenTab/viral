package edu.cnm.deepdive.viral.model.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

/**
 * The entity class for friends that can perform actions and form the theoretical "antagonists" of
 * the game.
 */
@SuppressWarnings({"NullableProblems", "NotNullFieldNotInitialized"})
@Entity(
    foreignKeys = @ForeignKey(
        entity = Demeanor.class,
        parentColumns = "demeanor_id",
        childColumns = "demeanor_id"
        // what sort of onDeletes are required if dependent entities are deleted together?
    ),
    indices = {
        @Index(value = {"name"}, unique = true),
//        @Index(value = {"address"}),
        @Index(value = {"profile_picture"}),
        @Index(value = {"demeanor_id"})
    }
)
public class Friend {

  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "friend_id")
  private long id;

  @NonNull
  private String name;

  @ColumnInfo(name = "infection_level")
  private int infectionLevel;

  @ColumnInfo(name = "demeanor_id")
  private long demeanor;

  private boolean active;

  private int address;

  @NonNull
  @ColumnInfo(name = "profile_picture")
  private String profilePicture;

  /**
   * Get the ID of the friend.
   *
   * @return The ID.
   */
  public long getId() {
    return id;
  }

  /**
   * Sets the ID of the friend.
   *
   * @param id The new ID.
   */
  public void setId(long id) {
    this.id = id;
  }

  /**
   * Gets the friend's name.
   *
   * @return The friend's name.
   */
  public String getName() {
    return name;
  }

  /**
   * Sets the friend's name.
   *
   * @param name The friend's new name.
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Gets the friend's current infection level.
   *
   * @return The friend's current infection level.
   */
  public int getInfectionLevel() {
    return infectionLevel;
  }

  /**
   * Sets the friend's current infection level.
   *
   * @param infectionLevel The friend's new infection level.
   */
  public void setInfectionLevel(int infectionLevel) {
    this.infectionLevel = infectionLevel;
  }

  /**
   * Gets the ID of the friend's current {@link Demeanor}.
   *
   * @return The ID of the friend's current {@link Demeanor}.
   */
  public long getDemeanor() {
    return demeanor;
  }

  /**
   * Sets the ID of the friend's current {@link Demeanor}.
   *
   * @param demeanor The ID of the friend's new {@link Demeanor}.
   */
  public void setDemeanor(long demeanor) {
    this.demeanor = demeanor;
  }

  /**
   * Gets whether or not the user has "deleted" the friend.
   *
   * @return The boolean representing whether the friend has been "deleted."
   */
  public boolean isActive() {
    return active;
  }

  /**
   * Sets the user's active status.
   *
   * @param active The friend's new active status.
   */
  public void setActive(boolean active) {
    this.active = active;
  }

  /**
   * Gets the friend's address ordinal.
   *
   * @return The friend's address ordinal.
   */
  public int getAddress() {
    return address;
  }

  /**
   * Sets the friend's address ordinal.
   *
   * @param address The friend's new address ordinal.
   */
  public void setAddress(int address) {
    this.address = address;
  }

  /**
   * Gets the path to the friend's profile picture.
   *
   * @return The path to the friend's profile picture.
   */
  public String getProfilePicture() {
    return profilePicture;
  }

  /**
   * Sets the path to the friend's profile picture.
   *
   * @param profilePicture The path to the friend's new profile picture.
   */
  public void setProfilePicture(String profilePicture) {
    this.profilePicture = profilePicture;
  }

  @NonNull
  @Override
  public String toString() {
    return name;
  }

}
