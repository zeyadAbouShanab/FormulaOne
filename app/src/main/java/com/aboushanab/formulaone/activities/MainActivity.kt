package com.aboushanab.formulaone.activities

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.aboushanab.formulaone.adapters.SeasonsAdapter
import com.aboushanab.formulaone.R
import com.aboushanab.formulaone.responses.SeasonResponse
import com.aboushanab.formulaone.utilities.RetrofitCall
import com.aboushanab.formulaone.services.RequestService
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getAllSeasons()
    }

    private fun getAllSeasons() {
        val service = RetrofitCall.retrofit().create(RequestService::class.java)
        val call = service.getSeasons()
        call.enqueue(object : Callback<SeasonResponse> {
            override fun onResponse(call: Call<SeasonResponse>, response: Response<SeasonResponse>) {
                if (response.code() == 200) {
                    Log.e("Success",response.body().toString())
                    val seasons = response.body()?.results?.table?.seasons?.toMutableList()!!
                    seasonsRecyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
                    seasonsRecyclerView.adapter = SeasonsAdapter(seasons)
                }
                else {
                    Log.e("Failure",response.code().toString())
                    Log.e("Failure",response.errorBody().toString())
                }
            }

            override fun onFailure(call: Call<SeasonResponse>, t: Throwable) {
                Log.e("Failure",t.message.toString())
            }
        })
    }
}