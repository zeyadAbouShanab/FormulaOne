package com.aboushanab.formulaone.activities

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.aboushanab.formulaone.R
import com.aboushanab.formulaone.responses.Driver
import com.aboushanab.formulaone.responses.GoogleResponse
import com.aboushanab.formulaone.services.RequestService
import com.aboushanab.formulaone.utilities.RetroFitCallGoogle
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.activity_driver.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class DriverActivity : AppCompatActivity() {
    private val driver = Driver()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_driver)
        getDriverClickedDetails()
        initializeDriver(driver)
        getImages()
    }

    private fun getImages() {
        //Getting Driver Image
        val service = RetroFitCallGoogle.retrofit().create(RequestService::class.java)
        val callDriverImage = service.getImage(driver.fullName)
        callDriverImage.enqueue(object : Callback<GoogleResponse> {
            override fun onResponse(
                callDriverImage: Call<GoogleResponse>,
                response: Response<GoogleResponse>
            ) {
                if (response.code() == 200) {
                    Log.e("Success", response.body().toString())
                    val image =
                        response.body()?.results?.get(0)?.pagemap?.images?.get(0)?.driverImage
                    Log.e("Picture", image.toString())
                    Glide.with(applicationContext)
                        .load(image)
                        .centerCrop()
                        .apply(
                            RequestOptions().override(300, 300)
                                .error(R.drawable.driver)
                        )
                        .into(driverImage)


                } else {
                    Log.e("Failure", response.code().toString())
                    Log.e("Failure", response.errorBody().toString())
                }
            }

            override fun onFailure(call: Call<GoogleResponse>, t: Throwable) {
                Log.e("Failure", t.message.toString())
            }
        })

        //Getting the flag
        val callFlagImage = service.getImage(driver.nationality.toString())
        callFlagImage.enqueue(object : Callback<GoogleResponse> {
            override fun onResponse(
                callFlagImage: Call<GoogleResponse>,
                response: Response<GoogleResponse>
            ) {
                if (response.code() == 200) {
                    Log.e("Success", response.body().toString())
                    val image =
                        response.body()?.results?.get(0)?.pagemap?.images?.get(0)?.driverImage
                    Glide.with(applicationContext)
                        .load(image)
                        .centerCrop()
                        .apply(
                            RequestOptions().override(50, 50)
                                .error(R.drawable.unknown_flag)
                        )
                        .into(singleDriverNationality)
                } else {
                    Log.e("Failure", response.code().toString())
                    Log.e("Failure", response.errorBody().toString())
                }
            }

            override fun onFailure(call: Call<GoogleResponse>, t: Throwable) {
                Log.e("Failure", t.message.toString())
            }
        })


    }


    private fun initializeDriver(driver: Driver) {
        singleDriverName.text = driver.fullName
        singleDriverAbb.append(driver.code)
        singleDriverDOB.append(driver.dateOfBirth)
        singleDriverStartNumber.append(driver.permanentNumber)
        singleDriverInfo.setOnClickListener {
            val webIntent = Intent(Intent.ACTION_VIEW, Uri.parse(driver.url))
            this.startActivity(webIntent)
        }
    }

    private fun getDriverClickedDetails() {
        val intent = intent
        driver.fullName = intent.getStringExtra("DriverClickedName") as String
        driver.code = intent.getStringExtra("DriverClickedAbb") as String
        driver.dateOfBirth = intent.getStringExtra("DriverClickedDOB") as String
        driver.nationality = intent.getStringExtra("DriverClickedNationality") as String
        driver.permanentNumber = intent.getStringExtra("DriverClickedNumber")
        driver.url = intent.getStringExtra("DriverClickedURL") as String
    }
}