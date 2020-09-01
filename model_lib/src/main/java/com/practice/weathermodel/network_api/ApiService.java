package com.practice.weathermodel.network_api;


import com.practice.weathermodel.pojo.City;
import com.practice.weathermodel.pojo.Response;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    @GET("box/city")
    Single<Response> getCitiesWeatherWithinRectangle(@Query("bbox") String coordinates, @Query("units") String units, @Query("appid") String key);

    @GET("forecast")
    Single<City> getCityWeatherById(@Query("id") String cityId, @Query("appid") String key);
}
