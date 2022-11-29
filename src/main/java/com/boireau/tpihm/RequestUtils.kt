package com.boireau.tpihm
import com.boireau.tpihm.RequestUtils.gson
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.InputStreamReader

const val URL_API_WEATHER =
    "https://api.openweathermap.org/data/2.5/weather?q=%s&appid=b80967f0a6bd10d23e44848547b26550&units=metric&lang=fr"
const val URL_POK = "https://www.amonteiro.fr/api/pokemonN3"
object RequestUtils {

    val client = OkHttpClient()
    val gson = Gson()


    fun loadWeatherOpti(city: String) = sendGetOpti(URL_API_WEATHER.format(city)).use { //it:Response
        var isr = InputStreamReader(it.body.byteStream())
        gson.fromJson(isr, WeatherBean::class.java)
    }


    fun sendGetOpti(url: String): Response {
        println("url : $url")
        //Création de la requête
        val request = Request.Builder().url(url).build()
        //Execution de la requête
        val response = client.newCall(request).execute()
        //Analyse du code retour
        return if (response.isSuccessful) {
            //On ferme la réponse qui n'est plus fermé par le use
            response.close()
            throw Exception("Réponse du serveur incorrect : ${response.code}")
        } else {
            response
        }
    }
    data class WeatherBean(var name: String,@SerializedName("main")var temperature: TempBean,var wind : WindBean
    )
    class WindBean (var speed:Double )
    class TempBean (var temp:Double)
   /* class Pokemon (@SerializedName("name")var name: String,@SerializedName("type") var type:Collection<String>)
    fun loadPoke(): Pokemon {
        val json = sendGet(URL_POK)
        return gson.fromJson(json, Pokemon::class.java)
    }

    fun sendGet(url: String): String {
        println("url : $url")
        //Création de la requête
        val request = Request.Builder().url(url).build()
        //Execution de la requête
        return client.newCall(request).execute().use {
            //Analyse du code retour
            if (!it.isSuccessful) {
                throw Exception("Réponse du serveur incorrect :${it.code}")
            }
            //Résultat de la requête
            it.body.string()
        }
}*/
}