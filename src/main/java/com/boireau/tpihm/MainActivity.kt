package com.boireau.tpihm

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.graphics.Color
import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.DatePicker
import android.widget.TimePicker
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.boireau.tpihm.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity(), View.OnClickListener, TimePickerDialog.OnTimeSetListener,
    DatePickerDialog.OnDateSetListener {

    private val MENU_ID_TP = 1
    private val MENU_ID_DP = 10
    private val MENU_ID_AD = 30
    private val SDF: SimpleDateFormat = SimpleDateFormat("dd/MM/yyyy HH:mm")
    private val MENU_ID_WEATHER = 60

    var calendar = Calendar.getInstance()
    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContentView(binding.root)


        binding.btValider.setOnClickListener(this)
        binding.btAnnuler.setOnClickListener(this)
        binding.rdLike.setOnClickListener(this)
        binding.rdDislike.setOnClickListener(this)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menu.add(0,MENU_ID_TP,0,"TimePicker")
        menu.add(0,MENU_ID_DP,0,"Datepicker")
        menu.add(0, MENU_ID_AD,0,"AlertDialog")
        menu.add(0, MENU_ID_WEATHER,0,"Météo")

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == MENU_ID_TP) {
            TimePickerDialog(this, this, calendar.get(Calendar.HOUR_OF_DAY),calendar.get(Calendar.MINUTE), true).show()
        }
        else if(item.itemId == MENU_ID_DP) {
            DatePickerDialog(this, this, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show()
        }
        else if(item.itemId == MENU_ID_AD) {
            //Préparation de la fenêtre
            val adb = AlertDialog.Builder(this)
            //Message / titre
            adb.setMessage("Mon message").setTitle("Mon titre")
            //bouton ok
            adb.setPositiveButton("ok") { dialog, which ->
                //Affiche un toast apres le clic sur le bouton ok
                Toast.makeText(this, "Ok", Toast.LENGTH_SHORT).show()
            }
            //Icone
            adb.setIcon(R.mipmap.ic_launcher);
            //Afficher la fenêtre
            adb.show();
        }
        else if(item.itemId == MENU_ID_WEATHER) {
            //redirection vers WeatherActivty
            val intent = Intent(this, WeatherActivity::class.java)
            //Passage de paramètre
            intent.putExtra("toto", binding.editTextTextPersonName2.text.toString())
            startActivity(intent)
        }
        return super.onOptionsItemSelected(item)
    }
    override fun onClick(v: View?) {

        if(v === binding.btValider ){
            binding.rdLike.text = "J'aime"
            binding.rdDislike.text = "J'aime pas"
        }
        if(v === binding.btAnnuler ){
            binding.rdg.clearCheck()
            binding.rdLike.text = ""
            binding.rdDislike.text = ""
        }
        if(v === binding.rdLike ) {
            binding.imgV1.setImageResource(R.drawable.ic_baseline_flag_24);
            binding.imgV1.setColorFilter(Color.GREEN);
        }
        if(v === binding.rdDislike ) {
            binding.imgV1.setImageResource(R.drawable.ic_baseline_delete_forever_24);
            binding.imgV1.setColorFilter(Color.RED);
        }
    }

    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay)
        calendar.set(Calendar.MINUTE, minute)

        Toast.makeText(this, SDF.format(calendar.time), Toast.LENGTH_SHORT).show()
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        calendar.set(year, month, dayOfMonth)
        Toast.makeText(this, SDF.format(calendar.time), Toast.LENGTH_SHORT).show()
    }


}