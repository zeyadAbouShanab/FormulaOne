package com.aboushanab.formulaone.responses

import com.google.gson.annotations.SerializedName


class DriversResponse {
    @SerializedName("MRData")
    var results: DriversResults? = null
}

class DriversResults{
    @SerializedName("DriverTable")
    var table: DriverMatches? = null
}

class DriverMatches{
    @SerializedName("Drivers")
    var drivers : Array<Driver>? = null
}
class Driver {
    @SerializedName("permanentNumber")
    var permanentNumber : String? = null
    @SerializedName("url")
    var url : String? = null
    @SerializedName("code")
    var code : String? = null
    @SerializedName("givenName")
    var givenName : String? = null
    @SerializedName("familyName")
    var familyName : String? = null
    @SerializedName("dateOfBirth")
    var dateOfBirth : String? = null
    @SerializedName("nationality")
    var nationality : String? = null
    var fullName = "$givenName $familyName"
}