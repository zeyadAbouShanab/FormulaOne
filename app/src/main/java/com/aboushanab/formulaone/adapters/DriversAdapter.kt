package com.aboushanab.formulaone.adapters

import android.content.Intent
import android.net.Uri
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aboushanab.formulaone.activities.DriverActivity
import com.aboushanab.formulaone.R
import com.aboushanab.formulaone.responses.Driver
import com.aboushanab.formulaone.inflate
import kotlinx.android.synthetic.main.driver_list_item.view.*

class DriversAdapter(private val drivers: MutableList<Driver>) :
    RecyclerView.Adapter<DriversAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(parent.inflate(R.layout.driver_list_item))
    }

    override fun getItemCount(): Int {
        return drivers.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(drivers[position])
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private lateinit var driver: Driver

        init {
            itemView.setOnClickListener {
                val context = itemView.context
                val intent = Intent(context, DriverActivity::class.java)
                intent.putExtra("DriverClickedName", driver.givenName + " " + driver.familyName)
                if (driver.code != null) {
                    intent.putExtra("DriverClickedAbb", driver.code)
                } else {
                    intent.putExtra("DriverClickedAbb", "NA")
                }
                intent.putExtra("DriverClickedDOB", driver.dateOfBirth)
                intent.putExtra("DriverClickedNationality", driver.nationality)
                if (driver.code != null) {
                    intent.putExtra("DriverClickedNumber", driver.permanentNumber)
                } else {
                    intent.putExtra("DriverClickedNumber", "NA")
                }
                intent.putExtra("DriverClickedURL", driver.url)
                context.startActivity(intent)
            }
            itemView.driverInfoButton.setOnClickListener {
                val webIntent= Intent(Intent.ACTION_VIEW, Uri.parse(driver.url))
                itemView.context.startActivity(webIntent)
            }
        }

        fun bind(driver: Driver) {
            this.driver = driver
            itemView.driverName.text = driver.givenName + " " + driver.familyName
            itemView.driverDOB.text = "Birthdate: " + driver.dateOfBirth
            if (driver.permanentNumber != null) {
                itemView.driverNumber.text = "Number: " + driver.permanentNumber
            } else {
                itemView.driverNumber.text = "Number: NA"
            }

            itemView.driverNation.text = driver.nationality
        }
    }


}