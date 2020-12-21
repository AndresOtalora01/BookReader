package com.example.bookreader;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.bookreader.network.RestApiWrapper;
import com.example.bookreader.network.models.BookInfo;
import com.example.bookreader.network.models.Library;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CatalogActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog);

        RestApiWrapper.getInstance().getBooks(new Callback<Library>() {
            @Override
            public void onResponse(Call<Library> call, Response<Library> response) {

                for (BookInfo book : response.body().getResults()){
                    Log.d("onResponse", "okay " + book.getTitle());
                }
            }

            @Override
            public void onFailure(Call<Library> call, Throwable t) {
            Log.d("onFailure", "error: "+ t.getMessage());
            }
        });
    }

}