package com.practice.weathermodel.network_api;


import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.practice.weathermodel.pojo.City;
import com.practice.weathermodel.pojo.Response;


import io.reactivex.Single;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient implements NetworkClient {
    public static final String API_KEY = "10ffe9e6913b2ac1529992c5618ca106";
    private static final String BASE_URL = "https://api.openweathermap.org/data/2.5/";
    private static final String UNIT_TYPE = "metric";
    private Retrofit retrofit = null;
    private final ApiService apiService;

    public ApiClient() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        apiService = retrofit.create(ApiService.class);
    }

    @Override
    public Single<Response> getCitiesWeatherWithinRectangle(String coordinates) {
        return apiService.getCitiesWeatherWithinRectangle(coordinates, UNIT_TYPE, API_KEY);
    }

    @Override
    public Single<Response> getCityWeatherById(String cityId) {
        return apiService.getCityWeatherById(cityId, UNIT_TYPE, API_KEY);
    }
}
