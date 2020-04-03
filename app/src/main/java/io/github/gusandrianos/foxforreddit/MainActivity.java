package io.github.gusandrianos.foxforreddit;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    Button bt;
    int LAUNCH_SECOND_ACTIVITY = 1;
    String BEARER = "65888787-vQS73TapvTc5oABrMf_lOLkXyvA";
    OkHttpClient client = new OkHttpClient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bt = findViewById(R.id.login);
    }

    public void loadWebPage(View view) {
        Intent load = new Intent(this, Main2Activity.class);
        load.putExtra("URL", "https://www.reddit.com/api/v1/authorize.compact?client_id=n1R0bc_lPPTtVg&response_type=code&state=ggfgfgfgga&redirect_uri=https://gusandrianos.github.io/login&duration=temporary&scope=identity,mysubreddits");
        startActivityForResult(load, LAUNCH_SECOND_ACTIVITY);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == LAUNCH_SECOND_ACTIVITY) {
            if (resultCode == Activity.RESULT_OK) {
                String result = data.getStringExtra("result");
                String[] inputs = result.split("\\?")[1].split("&");
                String code = inputs[1].split("=")[1];
                String state = inputs[0].split("=")[1];
                Toast.makeText(this, state + " " + code, Toast.LENGTH_LONG).show();

                loadWebPage(code);
            }
        }
    }

    void loadWebPage(String c) {
        OkHttpHandler okHttpHandler = new OkHttpHandler();
        okHttpHandler.execute(c);
    }

    public void me(View view) {
        OkHttpHandler okHttpHandler = new OkHttpHandler();
        okHttpHandler.execute();
    }


    class OkHttpHandler extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            if (strings.length == 0) {
                String url = "https://oauth.reddit.com/api/v1/scopes";

                Request request = new Request.Builder()
                        .url(url)
                        .addHeader("User-Agent", "thesis_andrianos_dalezios")
                        .addHeader("Authorization", " Bearer " + BEARER)
                        .build();
                Log.i("requesturl", request.toString());
                try (Response response = client.newCall(request).execute()) {
                    if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

                    return response.body().string();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                String url = "https://www.reddit.com/api/v1/access_token";
                String authString =  "n1R0bc_lPPTtVg" + ":";
                String encodedAuthString = Base64.encodeToString(authString.getBytes(),
                        Base64.NO_WRAP);
                RequestBody formBody = new FormBody.Builder()
                        .add("redirect_uri", "https://gusandrianos.github.io/login")
                        .add("grant_type", "authorization_code")
                        .add("code", strings[0])
                        .build();
                Request request = new Request.Builder()
                        .url(url)
                        .addHeader("User-Agent", "thesis_andrianos_dalezios")
                        .addHeader("Authorization", "Basic " + encodedAuthString)
                        .post(formBody)
                        .build();
                Log.i("requesturl", request.toString());
                try (Response response = client.newCall(request).execute()) {
                    if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

                    return response.body().string();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return "ela giorgh";
        }


        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.d("token", s);
            bt.setText(s);
        }
    }
}
