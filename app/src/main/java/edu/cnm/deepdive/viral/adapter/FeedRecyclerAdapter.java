package edu.cnm.deepdive.viral.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.squareup.picasso.Picasso;
import edu.cnm.deepdive.viral.R;
import edu.cnm.deepdive.viral.adapter.FeedRecyclerAdapter.Holder;
import edu.cnm.deepdive.viral.databinding.ItemPostBinding;
import edu.cnm.deepdive.viral.model.entity.Action;
import edu.cnm.deepdive.viral.model.entity.ActionTaken;
import edu.cnm.deepdive.viral.model.entity.Friend;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * RecyclerView that displays the posts in the user's Feed.
 */
public class FeedRecyclerAdapter extends RecyclerView.Adapter<Holder> {

  private final Context context;
  private final List<ActionTaken> posts;
  private final DateFormat formatter;
  private final LayoutInflater inflater;
  private final Action postAction;
  private final Friend poster;

  /**
   * The constructor initializes the context, the post {@link Action}, and the {@link Friend} that
   * posted it.
   *
   * @param context The application context.
   * @param postAction The {@link Action} that the post represents.
   * @param poster The {@link Friend} that made the post.
   */
  public FeedRecyclerAdapter(
      @NonNull Context context, @NonNull Action postAction, @NonNull Friend poster) {
    this.context = context;
    this.postAction = postAction;
    this.poster = poster;
    posts = new ArrayList<>();
    formatter = DateFormat.getInstance();
    inflater = LayoutInflater.from(context);
  }

  @NonNull
  @Override
  public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    ItemPostBinding binding = ItemPostBinding.inflate(inflater, parent, false);
    return new Holder(binding);
  }

  @Override
  public void onBindViewHolder(@NonNull Holder holder, int position) {
    holder.bind(position);
  }

  @Override
  public int getItemCount() {
    return posts.size();
  }

  /**
   * ViewHolder class for inflating each post in the feed.
   */
  class Holder extends RecyclerView.ViewHolder {

    private final ItemPostBinding binding;

    private Holder(@NonNull ItemPostBinding binding) {
      super(binding.getRoot());
      this.binding = binding;
    }

    private void bind(int position) {
      ActionTaken post = posts.get(position);
      Picasso picasso = new Picasso.Builder(context).listener((picasso1, uri, exception) ->
          Toast.makeText(context, R.string.image_load_failure_message, Toast.LENGTH_SHORT)).build();
      picasso.load(poster.getProfilePicture()).into(binding.postProfilePicture);
      binding.postPoster.setText(poster.getName());
      binding.postContents.setText(postAction.getContent());
      String timestamp = String.format(
          "Posted on %s @ %s", formatter.format(post.getTimestamp()), poster.getAddress());
      binding.postTimestamp.setText(timestamp);
      binding.buttonFocus.setOnClickListener((v) -> {
        // TODO Redirect to the feed for that friend.
      });
      binding.buttonMessage.setOnClickListener(v -> {
        // TODO Create a message to that friend, then redirect to messages and set them as the
        //  selected friend.
      });
      // TODO Add comments.
    }

  }

}
