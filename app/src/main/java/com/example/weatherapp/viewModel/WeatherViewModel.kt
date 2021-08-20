package com.example.weatherapp.viewModel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.weatherapp.DataClassWeather
import com.example.weatherapp.RetrofitSingleton
import com.fasterxml.jackson.databind.ObjectMapper
import com.google.gson.Gson
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WeatherViewModel(application: Application) : AndroidViewModel(application) {

    val context: Context = application.getApplicationContext()
    //data class weatherLiveData
    val dataClassWeatherLiveData: MutableLiveData<DataClassWeather> =
        MutableLiveData<DataClassWeather>()


    fun loadBloodBankList(long:Double, lat:Double) {
        val callBloodBankList = RetrofitSingleton.instance.getWeatherInfo(
            long, lat
            )

        callBloodBankList.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                val gson = Gson()
                if ( response.code() == 200 ) {
                    val dataClassWeather: DataClassWeather = gson.fromJson(response.body()?.string(), DataClassWeather::class.java)
                    dataClassWeatherLiveData.postValue(dataClassWeather)
             }
                else {
                    dataClassWeatherLiveData.postValue(null)
                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                dataClassWeatherLiveData.postValue(null)
            }

//            override fun onResponse(
//                call: Call<ResponseBody>,
//                response: Response<DataClassWeather>
//            ) {
//
//               if ( response.code() == 200 ) {
//                   dataClassWeather.postValue(response.body())
//                   println("Live Data ${response.body()?.data}")
//               }
//                else
//                   dataClassWeather.postValue(null)
//            }
//
//            override fun onFailure(call: Call<DataClassWeather>, t: Throwable) {
//                dataClassWeather.postValue(null)
//            }
//

        })
    }
}