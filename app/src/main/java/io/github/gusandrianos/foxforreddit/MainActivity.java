package io.github.gusandrianos.foxforreddit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import RetroBuilder.RetroToken;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import models.UserlessToken;
import okhttp3.Credentials;
import requests.TokenRequest;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {

    //    private final String BASE_URL = "https://www.reddit.com/";
//    String authString = "n1R0bc_lPPTtVg" + ":" + "";
//    String basic = "Basic " + Base64.encodeToString(authString.getBytes(), Base64.NO_WRAP);
//    TextView txt_token;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        //ACCESS_TOKEN for "userless" requests
//
//        txt_token = findViewById(R.id.access_token);
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(BASE_URL)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//
//        TokenRequest userlessRequest = retrofit.create(TokenRequest.class);
//
//        Call<UserlessToken> userlessToken = userlessRequest.createToken(Credentials.basic("n1R0bc_lPPTtVg", ""), "https://oauth.reddit.com/grants/installed_client", "DO_NOT_TRACK_THIS_DEVICE");
//        userlessToken.enqueue(new Callback<UserlessToken>() {
//            @Override
//            public void onResponse(Call<UserlessToken> call, Response<UserlessToken> response) {
//                try {
//                    Log.i("Userless_Token: ", response.body().getAccess_token());
//                    Log.i("Userless_Type: ", response.body().getToken_type());
//                    Log.i("Userless_Expires: ", response.body().getExpires_in());
//                    Log.i("Userless_Scope: ", response.body().getScope());
//
//                    txt_token.setText(response.body().getAccess_token());
//                } catch (NullPointerException e) {
//                    Log.i("Userless_Token: ", e.getMessage());
//                }
//
//                //GET listing using oauth
//
//                Retrofit retrofit1 = new Retrofit.Builder()
//                        .baseUrl("https://oauth.reddit.com/")
//                        .addConverterFactory(GsonConverterFactory.create())
//                        .build();
//                APIRequest listingsRequest = retrofit1.create(APIRequest.class);
//
//                String token = txt_token.getText().toString();
//                Log.i("TOKEN", token);
//                String BEARER =  " "+response.body().getToken_type() +" "+ response.body().getAccess_token();
//                Log.i("Brearer", BEARER);
//
//                Call<JsonObject> listing = listingsRequest.getListing( BEARER, "best");
//
//
//                listing.enqueue(new Callback<JsonObject>() {
//                    @Override
//                    public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
//                        Log.d("res", response.body().toString());
//                    }
//
//                    @Override
//                    public void onFailure(Call<JsonObject> call, Throwable t) {
//
//                        Log.d("error",t.getMessage());
//
//                    }
//                });
//            }
//
//            @Override
//            public void onFailure(Call<UserlessToken> call, Throwable t) {
//                Log.i("Userless_Token: ", "FAILED");
//            }
//        });
//
    TokenRequest tokenRequest;
    String BEARER;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        TokenRequest retroToken = RetroToken.getInstance();
//        Call<UserlessToken> userlessToken =  retroToken.createToken(Credentials.basic("n1R0bc_lPPTtVg", ""), "https://oauth.reddit.com/grants/installed_client", "DO_NOT_TRACK_THIS_DEVICE");
//        userlessToken.enqueue(new Callback<UserlessToken>() {
//            @Override
//            public void onResponse(Call<UserlessToken> call, Response<UserlessToken> response) {
//                try {
//                    Log.i("Userless_Token: ", response.body().getAccess_token());
//                    Log.i("Userless_Type: ", response.body().getToken_type());
//                    Log.i("Userless_Expires: ", response.body().getExpires_in());
//                    Log.i("Userless_Scope: ", response.body().getScope());
//
//                } catch (NullPointerException e) {
//                    Log.i("Userless_Token: ", e.getMessage());
//                }
//
//            }
//
//            @Override
//            public void onFailure(Call<UserlessToken> call, Throwable t) {
//                Log.d("error",t.getMessage());
//            }
//        });


        getUserlessToken();

    }

    void getUserlessToken() {
        tokenRequest = RetroToken.getTokenInstance();

        Observable<UserlessToken> userlessTokenObservable = tokenRequest.createToken(
                Credentials.basic("n1R0bc_lPPTtVg", "")
                ,"https://oauth.reddit.com/grants/installed_client"
                , "DO_NOT_TRACK_THIS_DEVICE"
        );


        userlessTokenObservable.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<UserlessToken>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(UserlessToken value) {
                        Log.i("Token","Access token ---> " + value.getAccess_token());
                        Log.i("Token","Token type ---> " + value.getToken_type() );
                        Log.i("Token","Expires in ---> " + value.getExpires_in());
                        Log.i("Token","Scope ---> " + value.getScope());
                        BEARER = value.getToken_type() + " " + value.getAccess_token();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("Token", "Error: " + e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        
                    }
                });
    }
}







