package kg.chinodev.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkService {
    private static NetworkService service;
    private final Retrofit mRetrofit;

    private NetworkService() {
        mRetrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static NetworkService getInstance() {
        if (service == null) {
            service = new NetworkService();
        }
        return service;
    }

    public JsonPlaceHolderApi getApi() {
        return mRetrofit.create(JsonPlaceHolderApi.class);
    }
}
