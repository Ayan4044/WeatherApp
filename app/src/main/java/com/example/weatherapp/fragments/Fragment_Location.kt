package com.example.weatherapp.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ViewModelProvider
import com.example.weatherapp.DataClassWeather
import com.example.weatherapp.DataClassWeatherData
import com.example.weatherapp.DataClassWeatherInfo
import com.example.weatherapp.R
import com.example.weatherapp.viewModel.WeatherViewModel
import org.json.JSONArray
import org.w3c.dom.Text

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private val ACCESS_FINE_LOCATION_PERMISSION = 1
/**
 * A simple [Fragment] subclass.
 * Use the [Fragment_Location.newInstance] factory method to
 * create an instance of this fragment.
 */
class Fragment_Location : Fragment(),LifecycleObserver {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var weatherViewModel: WeatherViewModel
    private lateinit var locationView: View
    private lateinit var dataClassWeather: DataClassWeather
    private lateinit var dataWeather: DataClassWeatherData
    private lateinit var weatherList : ArrayList<DataClassWeatherData>
    private lateinit var textViewWeatherTemp: TextView
    private lateinit var textViewWeatherFeel: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        locationView =  inflater.inflate(R.layout.fragment__location, container, false)

        textViewWeatherTemp = locationView.findViewById(R.id.textViewWeatherTemp)
        textViewWeatherFeel = locationView.findViewById(R.id.textViewWeatherFeel)
        return locationView
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Fragment_Location.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Fragment_Location().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        lifecycle.addObserver(this)

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onCreated(){
        weatherViewModel = ViewModelProvider(requireActivity()).get(WeatherViewModel::class.java)
        weatherViewModel.dataClassWeatherLiveData.observe(viewLifecycleOwner, {
                weatherliveData ->
            if( weatherliveData != null ){
                dataClassWeather = weatherliveData
               // println("Weather Data ${dataClassWeather.data}")
                weatherList = dataClassWeather.data as ArrayList<DataClassWeatherData>

             for (iitems in weatherList) {
                //  val item = jsonarray.getJSONObject(i)

                 val dataClassWeatherData: DataClassWeatherData = iitems
                 textViewWeatherTemp.text = dataClassWeatherData.temp.toString()
                 val dataclassinfo: DataClassWeatherInfo = dataClassWeatherData.weather
                 textViewWeatherFeel.text = "${dataclassinfo.description} ,  ${dataClassWeatherData.cityName} "
//                    // Your code here
            }
            }

//            if(weatherliveData.count == 1)
//            {
//                dataClassWeather = weatherliveData
//                println("Weather Data ${weatherliveData.data}")
//            }


        })
    }

    override fun onDetach() {
        super.onDetach()
        lifecycle.removeObserver(this)
    }
}