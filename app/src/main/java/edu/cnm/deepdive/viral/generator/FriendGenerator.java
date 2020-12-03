package edu.cnm.deepdive.viral.generator;

import android.app.Application;
import android.content.Context;
import android.content.res.AssetManager;
import edu.cnm.deepdive.viral.model.entity.Demeanor;
import edu.cnm.deepdive.viral.model.entity.Friend;
import edu.cnm.deepdive.viral.service.DemeanorRepository;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import org.apache.commons.csv.CSVRecord;

public class FriendGenerator {

  public static final String FILEPATH_FORMAT = "friends/pictures/%s/%03d.jpg";

  private final Random rng;
  private final AssetManager am;
  private final List<CSVRecord> femaleNames;
  private final List<CSVRecord> maleNames;
  private final List<CSVRecord> surnames;
  private final DemeanorRepository demeanorRepository;

  public FriendGenerator(Context context) throws IOException {
    rng = new Random();
    am = context.getAssets();
    femaleNames = CsvReader.parseCSV(am.open("friends/names/female.csv"));
    maleNames = CsvReader.parseCSV(am.open("friends/names/male.csv"));
    surnames = CsvReader.parseCSV(am.open("friends/names/surnames.csv"));
    demeanorRepository = new DemeanorRepository(context);
  }

  public List<Friend> makeFriends(int n, List<Demeanor> demeanors) {
    List<Friend> friends = new LinkedList<>();
    int females = n - rng.nextInt(n);
    int males = n - females;
    for (int i = 0; i < females; i++) {
      friends.add(makeFriend("female", n, demeanors));
    }
    for (int i = 0; i < males; i++) {
      friends.add(makeFriend("male", n, demeanors));
    }
    return friends;
  }

  private Friend makeFriend(String sex, int n, List<Demeanor> demeanors) {
    Friend friend = new Friend();
    String surname = surnames.get(rng.nextInt(surnames.size())).get(0);
    String name;
    if (sex.equals("female")) {
      name = femaleNames.get(rng.nextInt(femaleNames.size())).get(0);
    } else {
      name = maleNames.get(rng.nextInt(maleNames.size())).get(0);
    }
    friend.setName(String.format("%s %s", name, surname));
    friend.setProfilePicture(String.format(FILEPATH_FORMAT, sex, rng.nextInt(n)));
    friend.setDemeanor(demeanors.get(rng.nextInt(demeanors.size())).getId());
    friend.setActive(true);
    friend.setAddress(rng.nextInt(n) + 1); // TODO Update this when addresses exist.
    return friend;
  }

}
