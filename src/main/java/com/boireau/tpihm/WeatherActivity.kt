package com.boireau.tpihm

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.boireau.tpihm.databinding.ActivityWeatherBinding

class WeatherActivity : AppCompatActivity() {

    val binding by lazy {ActivityWeatherBinding.inflate(layoutInflater)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        //Exo plus rapides
        val texte =  intent.getStringExtra("toto")
        binding.tvData.setText(texte)
    }
}