package edu.cnm.deepdive.viral.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import edu.cnm.deepdive.viral.databinding.ItemMessageFriendBinding;
import edu.cnm.deepdive.viral.model.entity.Action;
import edu.cnm.deepdive.viral.model.entity.ActionTaken;
import edu.cnm.deepdive.viral.model.entity.Friend;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;

public class MessagesRecyclerAdapter /* extends RecyclerView.Adapter */ { /*

  private final Context context;
  private final List<ActionTaken> messages;
  private final DateFormat formatter;
  private final LayoutInflater inflater;
  private final Action messageAction;
  private final Friend friend;

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
  public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
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
*/
}
