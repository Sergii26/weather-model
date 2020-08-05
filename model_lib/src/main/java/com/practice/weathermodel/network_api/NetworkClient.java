package com.practice.weathermodel.network_api;


import com.practice.weathermodel.pojo.City;
import com.practice.weathermodel.pojo.Response;

import io.reactivex.Single;

public interface NetworkClient {
    Single<Response> getCitiesWeatherWithinRectangle(String coordinates);

    Single<City> getCityWeatherById(String cityId);
}
