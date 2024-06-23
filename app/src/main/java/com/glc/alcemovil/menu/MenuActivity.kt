package com.glc.alcemovil.menu
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.glc.alcemovil.R
import com.glc.alcemovil.accesos.AccesosActivity
import com.glc.alcemovil.fruta.FrutaActivity
import com.glc.alcemovil.siniestros.SiniestrosActivity

class MenuActivityKt : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        /*val btnAccesos = findViewById<Button>(R.id.btnAccesos)
        val btnFruta = findViewById<Button>(R.id.btnFruta)
        val btnSiniestros = findViewById<Button>(R.id.btnSiniestros)
        btnAccesos.setOnClickListener{onClickBtnAccesos()}
        btnFruta.setOnClickListener{onClickBtnFruta()}
        btnSiniestros.setOnClickListener{onClickBtnSiniestros()}*/
    }

    private fun onClickBtnSiniestros() {
        val intent = Intent(this, SiniestrosActivity::class.java)
        startActivity(intent)
    }

    private fun onClickBtnFruta() {
        val intent = Intent(this, FrutaActivity::class.java)
        startActivity(intent)
    }

    private fun onClickBtnAccesos() {
        val intent = Intent(this, AccesosActivity::class.java)
        startActivity(intent)
    }
}