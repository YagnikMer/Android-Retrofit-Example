package com.worldmer.retrofitexample;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.lsalphabets);
        getAlphabets();
    }

    private void getAlphabets() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(WebApi.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        WebApi api = retrofit.create(WebApi.class);
        Call<AlphabetResponse> call = api.getAlphabets();

        call.enqueue(new Callback<AlphabetResponse>() {
            @Override
            public void onResponse(Call<AlphabetResponse> call, Response<AlphabetResponse> response) {
                AlphabetResponse AlphabetList = response.body();
                createAlphabetsList(AlphabetList.alphabets);
            }

            @Override
            public void onFailure(Call<AlphabetResponse> call, Throwable t) {
            }
        });
    }

    public void createAlphabetsList(List<Alphabets> list) {
        String[] alphaArr = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            alphaArr[i] = list.get(i).getName();
        }
        listView.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, alphaArr));
    }
}