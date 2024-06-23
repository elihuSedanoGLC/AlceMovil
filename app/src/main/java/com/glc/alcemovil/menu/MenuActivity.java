package com.glc.alcemovil.menu;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.glc.alcemovil.R;
import com.glc.alcemovil.accesos.AccesosActivity;
import com.glc.alcemovil.fruta.FrutaActivity;
import com.glc.alcemovil.siniestros.SiniestrosActivity;
import com.google.android.material.navigation.NavigationView;

public class MenuActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle drawerToggle;

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(drawerToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        drawerToggle = new ActionBarDrawerToggle(this,drawerLayout, R.string.openNav,R.string.closeNav);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Log.d("Debuger","Item: "+item);
                switch (item.getItemId()){
                    case 1 :{
                        Toast.makeText(MenuActivity.this, "HOME CLICKED", Toast.LENGTH_SHORT).show();
                        break ;
                    }
                    case 2 :{
                        Toast.makeText(MenuActivity.this, "HOME 2 CLICKED", Toast.LENGTH_SHORT).show();
                        break ;
                    }
                }
                return false;
            }
        });
    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }
    }


/*private void onClickBtnSiniestros() {
        Intent intent = new Intent(this, SiniestrosActivity.class);
        startActivity(intent);
    }

    private void onClickBtnFruta() {
        Intent intent = new Intent(this, FrutaActivity.class);
        startActivity(intent);
    }

    private void onClickBtnAccesos() {
        Intent intent = new Intent(this, AccesosActivity.class);
        startActivity(intent);
    }*/
}//Cierre de la clase