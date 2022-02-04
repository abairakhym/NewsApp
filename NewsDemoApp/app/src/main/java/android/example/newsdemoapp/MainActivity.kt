package android.example.newsdemoapp

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var buttonChooseLan : Button
    private lateinit var buttonNextA : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonChooseLan = findViewById(R.id.buttonChooseLan)
        buttonNextA = findViewById(R.id.buttonNext)

    }

    fun onClickChooseButton(view: android.view.View) {
        showChangeLanguage()
    }

    fun onClickNext(view: android.view.View) {
        val intent = Intent(this, OnboardingActivity::class.java)
        startActivity(intent)
    }

    private fun showChangeLanguage(){
        val listLanguage = arrayOf("Қазақ тілі ","Русский язык")
        
        val mBuilder = AlertDialog.Builder(this)
        mBuilder.setTitle("Choose Language")
        mBuilder.setSingleChoiceItems(listLanguage, -1) { dialog, which ->
            if (which == 0) {
                setLocate("kz")
                recreate()
            } else if (which == 1) {
                setLocate("ru")
                recreate()
            }
            dialog.dismiss()
        }
        val mDialog = mBuilder.create()
        mDialog.show()
    }

    private fun setLocate(language: String) {
        val locale = Locale(language)

        Locale.setDefault(locale)

        val config = Configuration()

        config.locale = locale
        baseContext.resources.updateConfiguration(config, baseContext.resources.displayMetrics)

        val editor = getSharedPreferences("Settings", Context.MODE_PRIVATE).edit()
        editor.putString("My_Lang", language)
        editor.apply()
    }
}