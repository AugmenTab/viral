package edu.cnm.deepdive.viral.generator;

import android.app.Application;
import android.content.res.AssetManager;
import androidx.lifecycle.LiveData;
import edu.cnm.deepdive.viral.R;
import edu.cnm.deepdive.viral.model.entity.Demeanor;
import edu.cnm.deepdive.viral.model.entity.Friend;
import edu.cnm.deepdive.viral.service.ViralDatabase;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import org.apache.commons.collections4.IterableUtils;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

public class FriendGenerator {

  private final Random rng;
  private final List<CSVRecord> femaleNames;
  private final List<CSVRecord> maleNames;
  private final List<CSVRecord> surnames;
  private final LiveData<List<Demeanor>> demeanors;

  public FriendGenerator(Application application) throws IOException {
    rng = new Random();
    AssetManager am = application.getAssets();
    femaleNames = CsvReader.parseCSV(am.open("friends/female.csv"));
    maleNames = CsvReader.parseCSV(am.open("friends/male.csv"));
    surnames = CsvReader.parseCSV(am.open("friends/surnames.csv"));
    demeanors = ViralDatabase.getInstance().getDemeanorDao().selectDemeanorsByInfectionLevel(0, 2);

  }

  public List<Friend> makeFriends(int n) {
    List<Friend> friends = new LinkedList<>();
    int females = n - rng.nextInt(n);
    int males = n - females;
    for (int i = 0; i < females; i++) {
      friends.add(makeFriend("female"));
    }
    for (int i = 0; i < males; i++) {
      friends.add(makeFriend("male"));
    }
    return friends;
  }

  private Friend makeFriend(String sex) {
    Friend friend = new Friend();
    String surname = surnames.get(rng.nextInt(surnames.size())).get(0);
    String name = "";
    if (sex.equals("female")) {
      name = femaleNames.get(rng.nextInt(femaleNames.size())).get(0);
      // TODO Get female profile pic reference.
    } else {
      name = maleNames.get(rng.nextInt(maleNames.size())).get(0);
      // TODO Get male profile pic reference.
    }
    friend.setName(String.format("%s %s", name, surname));
    friend.setProfilePicture("");
    friend.setDemeanor(demeanors.getValue().get(0).getId());
    friend.setActive(true); // TODO Update this when addresses exist.
    friend.setAddress(0);
    return friend;
  }

}
