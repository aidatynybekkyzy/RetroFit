package kg.chinodev.retrofit;

import com.google.gson.annotations.SerializedName;

public class CommentPostItem {
    @SerializedName("name")
    private String name;
    @SerializedName("email")
    private String email;
    @SerializedName("body")
    private String body;

    public CommentPostItem(String name, String email, String body) {
        this.name = name;
        this.email = email;
        this.body = body;
    }
}
