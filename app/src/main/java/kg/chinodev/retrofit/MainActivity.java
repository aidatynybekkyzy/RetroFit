package kg.chinodev.retrofit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    RecyclerView postsRV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        postsRV = findViewById(R.id.posts_rv);
        postsRV.setLayoutManager(new LinearLayoutManager(this));
        loadPosts();
    }

    private void loadPosts() {
        NetworkService.getInstance()
                .getApi()
                .getPosts()
                .enqueue(new Callback<List<PostItem>>() {
                    @Override
                    public void onResponse(Call<List<PostItem>> call, Response<List<PostItem>> response) { //после получения данных
                        if (response.isSuccessful()) {
                            displayPosts(response.body());
                        } else {
                            System.out.println(response.code() + " " + response.message());
                        }
                    }

                    @Override
                    public void onFailure(Call<List<PostItem>> call, Throwable t) {
                        t.printStackTrace();
                    }
                });
    }

    private void displayPosts(List<PostItem> data) {
        PostsAdapter adapter = new PostsAdapter( //Адаптер сообщений
                new ArrayList<>(data),
                MainActivity.this
        );
        adapter.setListener(new PostsAdapter.PostClickListener() {
            @Override
            public void onPostClicked(int postId) {
                Intent intent = new Intent(MainActivity.this, CommentActivity.class);
                intent.putExtra("post_id", postId);
                startActivity(intent);
            }
        });
        postsRV.setAdapter(adapter);
    } //отображать сообщения
}