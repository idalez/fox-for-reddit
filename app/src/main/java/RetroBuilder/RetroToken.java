package RetroBuilder;

import com.google.gson.Gson;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import requests.TokenRequest;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public final class RetroToken {

    private static final String BASE_URL = "https://www.reddit.com/";
    private static TokenRequest sTokenRequest;



    public static TokenRequest getTokenInstance(){
        if (sTokenRequest == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(new Gson()))
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
            sTokenRequest = retrofit.create(TokenRequest.class);
        }
        return sTokenRequest;
    }

    private RetroToken() {
    }

}
