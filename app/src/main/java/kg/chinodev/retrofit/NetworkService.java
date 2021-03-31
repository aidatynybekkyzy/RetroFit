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
    } //точка входа в приложение

    public static NetworkService getInstance() {
        if (service == null) {
            service = new NetworkService();
        }
        return service;
    } //получить экземпляр

    public JsonPlaceHolderApi getApi() {
        return mRetrofit.create(JsonPlaceHolderApi.class);
    } //Api для держателя места Json
}
