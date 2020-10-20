package com.tutorialsbuzz.recyclerviewbottomnavshowhide

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        recyclerView.addItemDecoration(SimpleDividerItemDecoration(this))

        val adapter = CustomAdapter(getCountries(), this)
        recyclerView.adapter = adapter;

    }

    private fun getCountries(): MutableList<Model> {
        val mdList = mutableListOf<Model>()
        for (countryISO in Locale.getISOCountries()) {
            val locale = Locale("", countryISO)
            if (!locale.displayCountry.isEmpty()) {
                mdList.add(Model(locale.displayCountry + "  " + counrtyFlag(countryISO)) )
            }
        }
        return mdList
    }

    private fun counrtyFlag(countryCode: String): String {
        val flagOffset = 0x1F1E6
        val asciiOffset = 0x41
        val firstChar = Character.codePointAt(countryCode, 0) - asciiOffset + flagOffset
        val secondChar = Character.codePointAt(countryCode, 1) - asciiOffset + flagOffset
        val flag =
            (String(Character.toChars(firstChar)) + String(Character.toChars(secondChar)))

        return flag
    }

}