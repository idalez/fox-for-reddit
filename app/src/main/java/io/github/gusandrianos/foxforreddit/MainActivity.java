package io.github.gusandrianos.foxforreddit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.widget.TextView;

import models.UserlessToken;
import okhttp3.Credentials;
import requests.UserlessRequest;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private final String BASE_URL = "https://www.reddit.com/";
    String authString = "n1R0bc_lPPTtVg" + ":" + "";
    String basic = "Basic " + Base64.encodeToString(authString.getBytes(), Base64.NO_WRAP);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        UserlessRequest userlessRequest = retrofit.create(UserlessRequest.class);

        Call<UserlessToken> userlessToken = userlessRequest.createToken(Credentials.basic("n1R0bc_lPPTtVg", ""), "https://oauth.reddit.com/grants/installed_client", "DO_NOT_TRACK_THIS_DEVICE");
        userlessToken.enqueue(new Callback<UserlessToken>() {
            @Override
            public void onResponse(Call<UserlessToken> call, Response<UserlessToken> response) {
                try {
                    Log.i("Userless_Token: ", response.body().getAccess_token());
                    Log.i("Userless_Type: ", response.body().getToken_type());
                    Log.i("Userless_Expires: ", response.body().getExpires_in());
                    Log.i("Userless_Scope: ", response.body().getScope());
                    TextView txt_token = findViewById(R.id.access_token);
                    txt_token.setText(response.body().getAccess_token());
                } catch (NullPointerException e) {
                    Log.i("Userless_Token: ", e.getMessage());
                }
            }

            @Override
            public void onFailure(Call<UserlessToken> call, Throwable t) {
                Log.i("Userless_Token: ", "FAILED");
            }
        });
    }






}
