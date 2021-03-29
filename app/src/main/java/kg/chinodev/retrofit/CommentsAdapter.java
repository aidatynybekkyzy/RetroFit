package kg.chinodev.retrofit;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class CommentsAdapter extends RecyclerView.Adapter<CommentsAdapter.ViewHolder> {
    private final ArrayList<CommentItem> data;
    private final Context context;

    public CommentsAdapter(ArrayList<CommentItem> data, Context context) {
        this.data = data;
        this.context = context;
    }

    public void addComment(CommentItem item) {
        data.add(item);
        notifyItemInserted(data.size());
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.rv_item_comment, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CommentItem item = data.get(position);
        holder.name.setText(item.getName());
        holder.email.setText(item.getEmail());
        holder.body.setText(item.getBody());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView name;
        private final TextView email;
        private final TextView body;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.user_name_text);
            email = itemView.findViewById(R.id.user_email_text);
            body = itemView.findViewById(R.id.body_text);
        }
    }
}
