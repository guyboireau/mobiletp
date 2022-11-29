package com.boireau.tprequest

import RequestUtils

fun main() {
     /*  var html = sendGet("https://www.google.com")
    println("html:$html")*/

       /* val weather = RequestUtils.loadWeather("Toulouse")
        println("Il fait ${weather.temperature.temp}° à ${weather.name} avec un vent de ${weather.wind.speed} km/h")*/
    val pokemon= RequestUtils.loadPoke()
    println("Le pokemon s'appelle ${pokemon.name} et il est de type ${pokemon.type} ")
}
