package edu.cnm.deepdive.viral.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import edu.cnm.deepdive.viral.adapter.MessagesRecyclerAdapter.Holder;
import edu.cnm.deepdive.viral.databinding.ItemMessageFriendBinding;
import edu.cnm.deepdive.viral.model.entity.Action;
import edu.cnm.deepdive.viral.model.entity.ActionTaken;
import edu.cnm.deepdive.viral.model.entity.Friend;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * RecyclerView that displays the messages for the selected friend in the user's Messages window.
 */
public class MessagesRecyclerAdapter extends RecyclerView.Adapter<Holder> {

  private final Context context;
  private final List<ActionTaken> messages;
  private final DateFormat formatter;
  private final LayoutInflater inflater;
  private final Action messageAction;
  private final Friend friend;

  /**
   * The constructor initializes the context, the {@link Action} associated with the message, and
   * the {@link Friend} that sent the message.
   *
   * @param context
   * @param messageAction
   * @param friend
   */
  public MessagesRecyclerAdapter(
      @NonNull Context context, @NonNull Action messageAction, @NonNull Friend friend) {
    this.context = context;
    this.messageAction = messageAction;
    this.friend = friend;
    messages = new ArrayList<>();
    formatter = DateFormat.getInstance();
    inflater = LayoutInflater.from(context);
  }

  @NonNull
  @Override
  public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    ItemMessageFriendBinding binding = ItemMessageFriendBinding.inflate(inflater, parent, false);
    return new Holder(binding);
  }

  @Override
  public void onBindViewHolder(@NonNull Holder holder, int position) {
    holder.bind(position);
  }

  @Override
  public int getItemCount() {
    return messages.size();
  }

  /**
   * ViewHolder class for inflating each message in the message screen for the selected friend.
   */
  class Holder extends RecyclerView.ViewHolder {

    private final ItemMessageFriendBinding binding;

    private Holder(@NonNull ItemMessageFriendBinding binding) {
      super(binding.getRoot());
      this.binding = binding;
    }

    private void bind(int position) {
      ActionTaken message = messages.get(position);
      binding.messageFriend.setText(messageAction.getContent());
    }

  }

}
