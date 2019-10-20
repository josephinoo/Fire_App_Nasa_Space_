package com.galacticTurtle.fireapp;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import android.content.*;
import android.net.Uri;
import android.media.*;
import android.location.*;
public class pantallaReport extends AppCompatActivity {
    private ImageButton btn_llamar, btn_hospital;
    private Button btn_recibirAyuda;
    private ImageView imagen_Usu;
    private ImageButton btn_subir_imagen;
    private static final int NUMERO911 = 911;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_report);
        btn_hospital = (ImageButton) findViewById(R.id.btn_hospital);
        btn_llamar = (ImageButton) findViewById(R.id.btn_llamar);
        btn_recibirAyuda = (Button) findViewById(R.id.btn_ayuda);
        imagen_Usu= (ImageView) findViewById(R.id.img_incendio_sub);
        btn_subir_imagen=(ImageButton) findViewById(R.id.btn_camara);



        btn_llamar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                llamarEmergencia();
            }
        });
        btn_hospital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getApplicationContext(),lugares.class);
                startActivity(intent);
            }
        });
        btn_recibirAyuda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                llamarMapa(-2.134986,-79.903429);
            }
        });
    }
    private void llamarMapa(double lat, double lon){
        String uri = "http://maps.google.com/maps?saddr=" + 38.420517f + "," + -123.081858f+ "&daddr=" + 37.420517f + "," + -122.081858f;
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
        intent.setPackage("com.google.android.apps.maps");
        try
        {
            startActivity(intent);
        }
        catch(ActivityNotFoundException ex)
        {
            try
            {
                Intent unrestrictedIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                startActivity(unrestrictedIntent);
            }
            catch(ActivityNotFoundException innerEx)
            {
                Toast.makeText(this, "Please install a maps application", Toast.LENGTH_LONG).show();
            }
        }

    }
    private void llamarEmergencia() {

        Intent intent = new Intent(Intent.ACTION_DIAL,Uri.parse("tel:"+911));

        startActivity(intent);

    }

    private boolean verificarImagen(Image img){
        return false;
    }

    public Location getLocation() {
        boolean gps_enabled = false;
        boolean network_enabled = false;

        LocationManager lm = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

        gps_enabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
        network_enabled = lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

        Location net_loc = null, gps_loc = null, finalLoc = null;

            gps_loc = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        if (network_enabled)
            net_loc = lm.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

        if (gps_loc != null && net_loc != null) {

            //smaller the number more accurate result will
            if (gps_loc.getAccuracy() > net_loc.getAccuracy())
                finalLoc = net_loc;
            else
                finalLoc = gps_loc;

            // I used this just to get an idea (if both avail, its upto you which you want to take as I've taken location with more accuracy)

        } else {

            if (gps_loc != null) {
                finalLoc = gps_loc;
            } else if (net_loc != null) {
                finalLoc = net_loc;
            }
        }
        return finalLoc;
    }
}
