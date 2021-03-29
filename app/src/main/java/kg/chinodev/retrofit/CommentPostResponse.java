package kg.chinodev.retrofit;

import com.google.gson.annotations.SerializedName;

public class CommentPostResponse {
    @SerializedName("postId")
    private int postId;
    @SerializedName("id")
    private int id;

    public int getPostId() {
        return postId;
    }

    public int getId() {
        return id;
    }
}
