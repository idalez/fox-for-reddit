package requests;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;


public interface APIRequest {

    @GET("/{listing}")
    Observable<ResponseBody> getListing(@Header("Authorization") String authorization, @Path("listing") String listing);


}
