package kg.chinodev.retrofit;


import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.ViewHolder> {
    private final ArrayList<PostItem> data;
    private final Context context;
    private PostClickListener listener;

    public PostsAdapter(ArrayList<PostItem> data, Context context) {
        this.data = data;
        this.context = context;
    }

    public void setListener(PostClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public PostsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.rv_item_post, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull PostsAdapter.ViewHolder holder, int position) {
        PostItem item = data.get(position);
        holder.id.setText("User ID: " + item.getUserId());
        holder.title.setText(item.getTitle());
        holder.body.setText(item.getBody());
        holder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onPostClicked(item.getId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView id;
        private final TextView title;
        private final TextView body;
        private ConstraintLayout container;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.user_id_text);
            title = itemView.findViewById(R.id.title_text);
            body = itemView.findViewById(R.id.body_text);
            container = itemView.findViewById(R.id.item_container);
        }
    }

    public interface PostClickListener {
        void onPostClicked(int postId);
    }
}