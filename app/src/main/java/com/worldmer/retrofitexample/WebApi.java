package com.worldmer.retrofitexample;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Yagnik on 30-Jul-18.
 */

public interface WebApi {
    String BASE_URL = "http://www.worldmer.com/service/demo/";
    String ALPHABETS_LIST = "get_alphabet.php";

    @GET(ALPHABETS_LIST)
    Call<AlphabetResponse> getAlphabets();
}