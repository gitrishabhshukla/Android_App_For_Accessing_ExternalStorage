package com.example.rishabh.sdcardacess;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
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
import android.widget.Toast;

import java.io.File;

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
       if(Build.VERSION.SDK_INT >= 23 ){
           if(checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                   == PackageManager.PERMISSION_GRANTED){
               Log.v("Log","Permission is granted");
               return true;
           } else {
               Log.v("Log","Permission is Not Granted");
               ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
               return false;
           }
       } else {
           Log.v("Log","Permission is granted");
           return true;
       }
   }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
            Log.v("LOG","Permission: " + permissions[0] + "was" + grantResults[0]);
            //Application will resume task after getting this permission
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

    public File returnStorageDirectoryForFolderName(String directoryName, String nameofFolder){
       File filepath = new File(Environment.getExternalStoragePublicDirectory(directoryName),nameofFolder);

       if(!filepath.mkdirs()){
           letsCreateToast("There is no such Directory in the SDCard");
       } else {
           letsCreateToast("Your folder is created and it's name is:"+ nameofFolder);
       }
       return filepath;
    }

    public void letsCreateToast(String string){
        Toast.makeText(MainActivity.this, string,Toast.LENGTH_SHORT).show();
    }



    @Override
    public void onClick(View v) {

       switch (v.getId()){
           case R.id.btnDownloadsDirectory:
               returnStorageDirectoryForFolderName(Environment.DIRECTORY_DOWNLOADS,
                       "My DownLoads");
               break;
           case R.id.btnMusicDirectory:
               returnStorageDirectoryForFolderName(Environment.DIRECTORY_MUSIC,"My Music");
               break;
           case R.id.btnAlarmDirectory:
               returnStorageDirectoryForFolderName(Environment.DIRECTORY_ALARMS,"My Alarms");
               break;
           case R.id.btnMoviesDirectory:
               returnStorageDirectoryForFolderName(Environment.DIRECTORY_MOVIES,"My Favourite Movies");
               break;
           case R.id.btnDocumentDirectory:
               returnStorageDirectoryForFolderName(Environment.DIRECTORY_DOCUMENTS,"My Important Documents");
               break;
           case R.id.btnRingTonesDirectory:
               returnStorageDirectoryForFolderName(Environment.DIRECTORY_RINGTONES,"My RingTones");
               break;
           case R.id.btnPodcasteDirectory:
               returnStorageDirectoryForFolderName(Environment.DIRECTORY_PODCASTS,"My Postcasts");
               break;
           case R.id.btnPictureDirectory:
               returnStorageDirectoryForFolderName(Environment.DIRECTORY_PICTURES,"My Photos");
               break;
       }
    }
}
