package com.aboushanab.formulaone.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.aboushanab.formulaone.adapters.DriversAdapter
import com.aboushanab.formulaone.R
import com.aboushanab.formulaone.responses.DriversResponse
import com.aboushanab.formulaone.services.RequestService
import kotlinx.android.synthetic.main.activity_season.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.aboushanab.formulaone.responses.Driver
import com.aboushanab.formulaone.utilities.RetrofitCall
import kotlin.collections.ArrayList

class SeasonActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_season)
        val intent = intent
        val year = intent.getStringExtra("year") as String
        getSeasonDrivers(year)
    }
    private fun getSeasonDrivers(year:String) {
        val service = RetrofitCall.retrofit().create(RequestService::class.java)
        val call = service.getDrivers(year)
        call.enqueue(object : Callback<DriversResponse> {
            override fun onResponse(call: Call<DriversResponse>, response: Response<DriversResponse>) {
                if (response.code() == 200) {
                    Log.e("Success",response.body().toString())
                    val drivers1 = response.body()?.results?.table?.drivers
                    val drivers = response.body()?.results?.table?.drivers?.toMutableList()!!
                    driversRecyclerView.layoutManager = LinearLayoutManager(this@SeasonActivity,LinearLayoutManager.VERTICAL,false)
                    driversRecyclerView.adapter = DriversAdapter(drivers)
                    summaryText.text = nationsSummary(drivers1 as Array<Driver>)
                }
                else {
                    Log.e("Failure",response.code().toString())
                    Log.e("Failure",response.errorBody().toString())
                }
            }

            override fun onFailure(call: Call<DriversResponse>, t: Throwable) {
                Log.e("Failure",t.message.toString())
            }
        })
    }

    fun nationsSummary(drivers : Array<Driver>):String{
        val nationalities = ArrayList<String>()
        val nationalitiesMap = mutableMapOf<String, Int>()
        var finalText  = ""
        for(i in drivers){
            if(nationalitiesMap.containsKey(i.nationality)){
                nationalitiesMap[i.nationality]?.plus(1)?.let { nationalitiesMap.put(i.nationality.toString() , it) }
            }
            else{
                nationalitiesMap[i.nationality.toString()] = 1
                nationalities.add(i.nationality.toString())
            }
        }
        val x = nationalitiesMap.toList()
            .sortedBy { (_, value) -> value }
            .toMap()
        for ((k, v) in x) {
            finalText = "$k:$v \n\n$finalText"
        }
        return  finalText
    }
}