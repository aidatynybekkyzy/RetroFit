package kg.chinodev.retrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface JsonPlaceHolderApi {
    @GET("/posts/")
    public Call<List<PostItem>> getPosts();

    @GET("/posts/{post_id}/comments/")
    public Call<List<CommentItem>> getComments(@Path("post_id") int id);

    @POST("/posts/{post_id}/comments/") //Для нужных значений, типо нам именно это нужно
    public Call<CommentPostResponse> postComment(@Path("post_id") int id, @Body CommentPostItem item);
}