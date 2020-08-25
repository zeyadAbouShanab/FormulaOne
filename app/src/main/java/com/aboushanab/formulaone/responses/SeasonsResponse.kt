package com.aboushanab.formulaone.responses


import com.google.gson.annotations.SerializedName

class SeasonResponse {
    @SerializedName("MRData")
    var results: Results? = null
}

class Results{
    @SerializedName("SeasonTable")
    var table: SeasonMatches? = null
}

class SeasonMatches{
    @SerializedName("Seasons")
    var seasons : Array<Season>? = null
}

class Season {
    @SerializedName("season")
    var year : String? = null
    @SerializedName("url")
    var url : String? = null
}
