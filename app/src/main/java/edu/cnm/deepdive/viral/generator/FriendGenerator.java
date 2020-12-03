package edu.cnm.deepdive.viral.generator;

import android.content.Context;
import android.content.res.AssetManager;
import edu.cnm.deepdive.viral.model.entity.Demeanor;
import edu.cnm.deepdive.viral.model.entity.Friend;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import org.apache.commons.csv.CSVRecord;

/**
 * This class generates one or more instances of {@link Friend}.
 */
public class FriendGenerator {

  private static final String FILEPATH_FORMAT = "friends/pictures/%s/%03d.jpg";

  private final Random rng;
  private final List<CSVRecord> femaleNames;
  private final List<CSVRecord> maleNames;
  private final List<CSVRecord> surnames;

  /**
   * The constructor receives the application context, creates a new {@code AssetManager}, and
   * receives the information parsed from multiple CSV files used in {@link Friend} generation.
   *
   * @param context The application context.
   * @throws IOException The constructor calls a method from {@link CsvReader} to read a CSV file.
   */
  public FriendGenerator(Context context) throws IOException {
    rng = new Random();
    AssetManager am = context.getAssets();
    femaleNames = CsvReader.parseCSV(am.open("friends/names/female.csv"));
    maleNames = CsvReader.parseCSV(am.open("friends/names/male.csv"));
    surnames = CsvReader.parseCSV(am.open("friends/names/surnames.csv"));
  }

  /**
   * Generates a number of {@link Friend} objects using the {@link Demeanor} objects provided.
   *
   * @param friendsToMake The number of {@link Friend} objects to make.
   * @param demeanors A list of {@link Demeanor} objects.
   * @return A list of {@link Friend} generated.
   */
  public List<Friend> makeFriends(int friendsToMake, List<Demeanor> demeanors) {
    List<Friend> friendsMade = new LinkedList<>();
    int females = friendsToMake - rng.nextInt(friendsToMake);
    int males = friendsToMake - females;
    for (int i = 0; i < females; i++) {
      friendsMade.add(makeFriend("female", friendsToMake, demeanors));
    }
    for (int i = 0; i < males; i++) {
      friendsMade.add(makeFriend("male", friendsToMake, demeanors));
    }
    return friendsMade;
  }

  private Friend makeFriend(String sex, int addresses, List<Demeanor> demeanors) {
    Friend friend = new Friend();
    String surname = surnames.get(rng.nextInt(surnames.size())).get(0);
    String name;
    if (sex.equals("female")) {
      name = femaleNames.get(rng.nextInt(femaleNames.size())).get(0);
    } else {
      name = maleNames.get(rng.nextInt(maleNames.size())).get(0);
    }
    friend.setName(String.format("%s %s", name, surname));
    friend.setProfilePicture(String.format(FILEPATH_FORMAT, sex, rng.nextInt(addresses)));
    friend.setDemeanor(demeanors.get(rng.nextInt(demeanors.size())).getId());
    friend.setActive(true);
    friend.setAddress(rng.nextInt(addresses) + 1); // TODO Update this when addresses exist.
    return friend;
  }

}
