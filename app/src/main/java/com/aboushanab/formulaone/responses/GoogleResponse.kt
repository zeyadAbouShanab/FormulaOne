package com.aboushanab.formulaone.responses

import com.google.gson.annotations.SerializedName

class GoogleResponse{
    @SerializedName("items")
    var results: Array<GoogleResult>? = null
}
class GoogleResult {
    @SerializedName("pagemap")
    var pagemap : PageMap? = null
}
class PageMap{
    @SerializedName("cse_image")
    var images : Array<DriverPicture>? = null
}
class DriverPicture{
    @SerializedName("src")
    var driverImage : String? = null
}