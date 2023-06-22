package org.example.services;

import okhttp3.ResponseBody;
import org.example.models.Movies;
import org.example.models.Title;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface MoviesService {

    @GET("en/API/Top250Movies/{api_kye}")
    Call<Movies> getTop250Movies(@Path("api_kye") String api_kye);

    @GET("en/API/Title/{api_kye}/{item_id}/Ratings")
    Call<Title> getItemTitle (@Path("api_kye") String api_kye, @Path("item_id") String item_id);

    @GET("en/API/Report/{api_kye}/{item_id}")
    Call<ResponseBody> getItemPoster (@Path("api_kye") String api_kye, @Path("item_id") String item_id);
}
