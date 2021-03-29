package kg.chinodev.retrofit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CommentActivity extends AppCompatActivity {
    RecyclerView commentsRv;
    CommentsAdapter adapter;
    EditText commentEt;
    Button sendBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        commentsRv = findViewById(R.id.comments_rv);
        commentEt = findViewById(R.id.comment_edit);
        sendBtn = findViewById(R.id.comment_send_btn);
        commentsRv.setLayoutManager(new LinearLayoutManager(this));
        int postId = getIntent().getIntExtra("post_id", 1);
        loadComments(postId);
        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String comment = commentEt.getText().toString().trim();
                sendComment(comment, postId);
            }
        });

    }

    private void loadComments(int postId) {
        NetworkService.getInstance()
                .getApi()
                .getComments(postId)
                .enqueue(new Callback<List<CommentItem>>() {
                    @Override
                    public void onResponse(Call<List<CommentItem>> call, Response<List<CommentItem>> response) {
                        if (response.isSuccessful()) {
                            adapter = new CommentsAdapter(
                                    new ArrayList<>(response.body()),
                                    CommentActivity.this
                            );
                            commentsRv.setAdapter(adapter);
                        }
                    }

                    @Override
                    public void onFailure(Call<List<CommentItem>> call, Throwable t) {

                    }
                });
    }

    private void sendComment(String comment, int postId) {
        String name = "Tynystan";
        String email = "tinistan@mail.ru";
        CommentPostItem postItem = new CommentPostItem(name, email, comment);

        NetworkService.getInstance()
                .getApi()
                .postComment(postId, postItem)
                .enqueue(new Callback<CommentPostResponse>() {
                    @Override
                    public void onResponse(Call<CommentPostResponse> call, Response<CommentPostResponse> response) {
                        if (response.isSuccessful()) {
                            CommentItem item = new CommentItem(
                                    response.body().getPostId(),
                                    response.body().getId(),
                                    name,
                                    email,
                                    comment
                            );
                            if (adapter != null) {
                                adapter.addComment(item);
                            }
                        } else  {
                            System.out.println(response.code() + " " + response.message());
                        }
                    }

                    @Override
                    public void onFailure(Call<CommentPostResponse> call, Throwable t) {
                        t.printStackTrace();
                    }
                });
    }
}