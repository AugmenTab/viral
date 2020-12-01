package edu.cnm.deepdive.viral.generator;

import android.app.Application;
import android.content.res.AssetManager;
import androidx.lifecycle.LiveData;
import edu.cnm.deepdive.viral.model.entity.Demeanor;
import edu.cnm.deepdive.viral.model.entity.Friend;
import edu.cnm.deepdive.viral.service.ViralDatabase;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import org.apache.commons.csv.CSVRecord;

public class FriendGenerator {

  public static final String FILEPATH_FORMAT = "friends/pictures/%s/%03d.jpg";
  private final Random rng;
  private final AssetManager am;
  private final List<CSVRecord> femaleNames;
  private final List<CSVRecord> maleNames;
  private final List<CSVRecord> surnames;
  private final LiveData<List<Demeanor>> demeanors;

  public FriendGenerator(Application application) throws IOException {
    rng = new Random();
    am = application.getAssets();
    femaleNames = CsvReader.parseCSV(am.open("friends/names/female.csv"));
    maleNames = CsvReader.parseCSV(am.open("friends/names/male.csv"));
    surnames = CsvReader.parseCSV(am.open("friends/names/surnames.csv"));
    demeanors = ViralDatabase.getInstance().getDemeanorDao().selectDemeanorsByInfectionLevel(0, 2);
  }

  public List<Friend> makeFriends(int n) {
    List<Friend> friends = new LinkedList<>();
    int females = n - rng.nextInt(n);
    int males = n - females;
    for (int i = 0; i < females; i++) {
      friends.add(makeFriend("female", n));
    }
    for (int i = 0; i < males; i++) {
      friends.add(makeFriend("male", n));
    }
    return friends;
  }

  private Friend makeFriend(String sex, int n) {
    Friend friend = new Friend();
    String surname = surnames.get(rng.nextInt(surnames.size())).get(0);
    String name;
    if (sex.equals("female")) {
      name = femaleNames.get(rng.nextInt(femaleNames.size())).get(0);
    } else {
      name = maleNames.get(rng.nextInt(maleNames.size())).get(0);
    }
    friend.setName(String.format("%s %s", name, surname));
    friend.setProfilePicture(String.format(FILEPATH_FORMAT, sex, rng.nextInt(n))); // TODO What does this mean?
    friend.setDemeanor(demeanors.getValue().get(0).getId()); // TODO Related to other null pointer exception?
    friend.setActive(true);
    friend.setAddress(1); // TODO Update this when addresses exist.
    return friend;
  }

}
