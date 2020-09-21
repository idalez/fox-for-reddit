package requests;

import android.util.Base64;

import io.reactivex.Observable;
import models.UserlessToken;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface TokenRequest {

    // concatenate client ID and empty string with colon for authentication
    //String credentials = Constants.CLIENT_ID + ":" + "";
    // create Base64 encode string
//    final String basic =
//            "Basic " + Base64.encodeToString(credentials.getBytes(), Base64.NO_WRAP);



    @FormUrlEncoded //Content-type: application/x-www-form-urlencoded
    @POST("/api/v1/access_token")
    Observable<UserlessToken> createToken(@Header("Authorization") String credentials, @Field("grant_type") String grant_type, @Field("device_id") String device_id);

}
