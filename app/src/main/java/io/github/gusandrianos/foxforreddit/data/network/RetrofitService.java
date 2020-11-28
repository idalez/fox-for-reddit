package io.github.gusandrianos.foxforreddit.data.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/*
    Sets up retrofit for usage with the OAuthToken interface or the RedditAPI interface
 */
public class RetrofitService {
    private RetrofitService() {
    }

    private static final String mOAuthRequestURL = "https://www.reddit.com/";
    private static OAuthToken sOAuthTokenRequest;

    private static final String mRedditRequestURL = "https://oauth.reddit.com/";
    private static RedditAPI sRedditAPIRequest;

    /**
     * Used for requesting a login OAuth token from the Reddit API. Due to limiting factors of
     * Retrofit to .baseUrl changes at runtime, we need two identical methods to talk to different
     * links like mOAuthRequestURL and mRedditRequestURL
     * @return The constructed instance of a class base upon the OAuthToken interface.
     * The class is generated by Retrofit to avoid writing cumbersome boilerplate code.
     */
    public static OAuthToken getTokenRequestInstance() {
        if (sOAuthTokenRequest == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(mOAuthRequestURL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            sOAuthTokenRequest = retrofit.create(OAuthToken.class);
        }
        return sOAuthTokenRequest;
    }

    /**
     * Used for talking to Reddit with a token after a successful log-in or with a user-less token
     * @return The constructed instance of a class base upon the RedditAPI interface.
     * The class is generated by Retrofit to avoid writing cumbersome boilerplate code.
     */
    public static RedditAPI getRedditAPIInstance() {
        if (sRedditAPIRequest == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(mRedditRequestURL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            sRedditAPIRequest = retrofit.create(RedditAPI.class);
        }
        return sRedditAPIRequest;
    }
}
