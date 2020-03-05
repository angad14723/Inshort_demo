package com.terasoltechnologies.inshortdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.terasoltechnologies.inshortdemo.services.ApiInterface;
import com.terasoltechnologies.inshortdemo.services.ApiModel;
import com.terasoltechnologies.inshortdemo.services.ServicesClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    VerticalViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = findViewById(R.id.card_view);

        getApiData();
      //  viewPager.setAdapter(new SlidePageAdapter(this));
    }

    private void getApiData() {

        ApiInterface apiInterface= ServicesClient.getRetrofit(this).create(ApiInterface.class);

        Call<List<ApiModel>> call=apiInterface.apiResponse();

        call.enqueue(new Callback<List<ApiModel>>() {
            @Override
            public void onResponse(Call<List<ApiModel>> call, Response<List<ApiModel>> response) {

                if (response.isSuccessful()){

                    List<ApiModel> apiModelArrayList=  response.body();

                    Log.i("apiModelValue",apiModelArrayList.get(0).getTitle());

                    viewPager.setAdapter(new SlidePageAdapter(MainActivity.this,apiModelArrayList));

                }

            }

            @Override
            public void onFailure(Call<List<ApiModel>> call, Throwable t) {
                    t.printStackTrace();
            }
        });

    }
}
