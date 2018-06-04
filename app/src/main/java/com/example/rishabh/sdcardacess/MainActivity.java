package com.example.rishabh.sdcardacess;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button btnDownloadDirectory;
    Button btnMusicsDirectory;
    Button btnDocumntDirectory;
    Button btnRingTonesDirectory;
    Button btnPodcateDirectory;
    Button btnMovieDirectory;
    Button btnAlarmsDirectory;
    Button btnPicturesDirectory;

    Button btnSaveFile;
    EditText edtValues;

    Button btnRetrieveInfo;
    TextView txtValues;

    ImageView imageView;

    Button btnAllowAccessToThePictures;

    LinearLayout linearLayoutHorizontal;
    ImageSwitcher imageSwitcher;

   public static final int REQUEST_CODE = 1234;

   public boolean isStoragePermissionGranted(){
       if(Build.VERSION.SDK_INT >= 22){
           if(checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                   == PackageManager.PERMISSION_GRANTED){
               Log.v("Log","Permission is granted");
               return true;
           }
       }
   }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        SDCARDChecker.checkWheatherExternalStorageAvailableOrNot(MainActivity.this);

      btnDownloadDirectory = findViewById(R.id.btnDownloadsDirectory);
      btnMusicsDirectory = findViewById(R.id.btnMusicDirectory);
      btnDocumntDirectory = findViewById(R.id.btnDocumentDirectory);
      btnRingTonesDirectory = findViewById(R.id.btnRingTonesDirectory);
      btnPodcateDirectory = findViewById(R.id.btnPodcasteDirectory);
      btnMovieDirectory = findViewById(R.id.btnMoviesDirectory);
      btnAlarmsDirectory = findViewById(R.id.btnAlarmDirectory);
      btnPicturesDirectory = findViewById(R.id.btnPictureDirectory);

      btnSaveFile = findViewById(R.id.btnSaveValue);
      edtValues = findViewById(R.id.edtValue);

      btnRetrieveInfo = findViewById(R.id.btnRetrieveValue);
      txtValues = findViewById(R.id.txtValue);

      imageView = findViewById(R.id.imageView);

      btnAllowAccessToThePictures = findViewById(R.id.btnAllowAcessToPictures);

      linearLayoutHorizontal = findViewById(R.id.linearLayoutHorizontal);
      imageSwitcher = findViewById(R.id.imageSwitcher);

        btnDownloadDirectory.setOnClickListener(MainActivity.this);
        btnMusicsDirectory.setOnClickListener(MainActivity.this);
        btnDocumntDirectory.setOnClickListener(MainActivity.this);
        btnRingTonesDirectory.setOnClickListener(MainActivity.this);
        btnPodcateDirectory.setOnClickListener(MainActivity.this);
        btnMovieDirectory.setOnClickListener(MainActivity.this);
        btnAlarmsDirectory.setOnClickListener(MainActivity.this);
        btnPicturesDirectory.setOnClickListener(MainActivity.this);
        btnSaveFile.setOnClickListener(MainActivity.this);
        btnRetrieveInfo.setOnClickListener(MainActivity.this);
        imageView.setOnClickListener(MainActivity.this);

    }

    @Override
    public void onClick(View v) {


    }
}
