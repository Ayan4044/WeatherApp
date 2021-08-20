package com.example.weatherapp

import com.google.gson.annotations.SerializedName

data class DataClassWeather (
    @SerializedName("data") var data : List<DataClassWeatherData>,
    @SerializedName("count") var count : Int
)


data class DataClassWeatherData (

    @SerializedName("rh") var rh : Double,
    @SerializedName("pod") var pod : String,
    @SerializedName("lon") var lon : Double,
    @SerializedName("pres") var pres : Double,
    @SerializedName("timezone") var timezone : String,
    @SerializedName("ob_time") var obTime : String,
    @SerializedName("country_code") var countryCode : String,
    @SerializedName("clouds") var clouds : Double,
    @SerializedName("ts") var ts : Double,
    @SerializedName("solar_rad") var solarRad : Double,
    @SerializedName("state_code") var stateCode : String,
    @SerializedName("city_name") var cityName : String,
    @SerializedName("wind_spd") var windSpd : Double,
    @SerializedName("wind_cdir_full") var windCdirFull : String,
    @SerializedName("wind_cdir") var windCdir : String,
    @SerializedName("slp") var slp : Double,
    @SerializedName("vis") var vis : Double,
    @SerializedName("h_angle") var hAngle : Double,
    @SerializedName("sunset") var sunset : String,
    @SerializedName("dni") var dni : Double,
    @SerializedName("dewpt") var dewpt : Double,
    @SerializedName("snow") var snow : Double,
    @SerializedName("uv") var uv : Double,
    @SerializedName("precip") var precip : Double,
    @SerializedName("wind_dir") var windDir : Double,
    @SerializedName("sunrise") var sunrise : String,
    @SerializedName("ghi") var ghi : Double,
    @SerializedName("dhi") var dhi : Double,
    @SerializedName("aqi") var aqi : Double,
    @SerializedName("lat") var lat : Double,
    @SerializedName("weather") var weather : DataClassWeatherInfo,
    @SerializedName("datetime") var datetime : String,
    @SerializedName("temp") var temp : Double,
    @SerializedName("station") var station : String,
    @SerializedName("elev_angle") var elevAngle : Double,
    @SerializedName("app_temp") var appTemp : Double

)


data class DataClassWeatherInfo (

    @SerializedName("icon") var icon : String,
    @SerializedName("code") var code : Int,
    @SerializedName("description") var description : String

)