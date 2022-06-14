package gini.udihaguel.day22coins.models

data class ApiResponse(
    val success:Boolean,
    val timestamp:Long,
    val base:String?,
    val date:String?,
    val rates:Map<String,Double>?
)