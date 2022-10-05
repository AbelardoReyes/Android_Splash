package com.example.splash;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    ImageView visor;
    Button btncamara;
    Button btnLink;
    Button DoCall;
    Button btnMapa;
    Button btnContactos;
    Button btnSMS;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //variables tipo boton
        btncamara = findViewById(R.id.btn1);
        visor = findViewById(R.id.iv_visor);
        btnLink = findViewById(R.id.btnLink);
        DoCall = findViewById(R.id.btnCall);
        btnMapa = findViewById(R.id.btnMapa);
        btnContactos = findViewById(R.id.btnContactos);
        btnSMS = findViewById(R.id.btnSMS);

        btncamara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ContextCompat.checkSelfPermission(MainActivity.this,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                        && ActivityCompat.checkSelfPermission(MainActivity.this,
                        Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(MainActivity.this,
                            new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                    Manifest.permission.CAMERA}, 1000);
                }
                camara();
            }
        });
        btnLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goLink();
            }
        });
        DoCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMovil();
            }
        });
        btnMapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abrirMapa();
            }
        });
        btnContactos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abrirContactos();
            }
        });
        btnSMS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enviarSMS();
            }
        });
    }

    //Metodo para Activar Camara
    private void camara() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, 1);
        }
    }

    //Capturar la imagen
    protected void onActivityResult(int requestcode, int resultCode, Intent data) {
        super.onActivityResult(requestcode, resultCode, data);
        if (requestcode == 1 && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imgBitmap = (Bitmap) extras.get("data");
            visor.setImageBitmap(imgBitmap);
        }
    }

    //Metodo para redigirse a un link
    private void goLink() {
        //Link

        String url = "https://laravel.com/docs/9.x";
        Uri _link = Uri.parse(url);
        Intent i = new Intent(Intent.ACTION_VIEW,_link);
        startActivity(i);
    }
    private void openMovil(){
        String phone = "tel:8714733996";
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse(phone));
        startActivity(intent);
    }
    private void abrirMapa(){
        Uri _link = Uri.parse("geo:-31.417,-64.183");
        Intent i = new Intent(Intent.ACTION_VIEW,_link);
        startActivity(i);
    }
    private void abrirContactos(){
        Intent contactos = new Intent(Intent.ACTION_VIEW,Uri.parse("content://contacts/people/"));
        startActivity(contactos);
    }
    private void enviarSMS(){
        Intent SMS = new Intent(Intent.ACTION_VIEW,Uri.parse("sms:8714733996"));
        startActivity(SMS);
    }
}

