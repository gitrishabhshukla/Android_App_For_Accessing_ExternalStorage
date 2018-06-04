package com.example.rishabh.sdcardacess;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
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
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,ViewSwitcher.ViewFactory{

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

    ImageView imgAnimal;

    Button btnAllowAccessToThePictures;

    LinearLayout linearLayoutHorizontal;
    ImageSwitcher imageSwitcher;

    ArrayList<String> filePathNames;
    File[] files;

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

      imgAnimal = findViewById(R.id.imgAnimal);

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
        imgAnimal.setOnClickListener(MainActivity.this);

        imageSwitcher.setFactory(MainActivity.this);
        imageSwitcher.setInAnimation(AnimationUtils.loadAnimation(MainActivity.this,android.R.anim.slide_in_left));
        imageSwitcher.setOutAnimation(AnimationUtils.loadAnimation(MainActivity.this,android.R.anim.slide_out_right));

        btnAllowAccessToThePictures.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(isStoragePermissionGranted()){

                    filePathNames = new ArrayList<String>();
                    File filePath = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
                            "AnimalImages");
                    if(filePath.isDirectory() && filePath != null){
                        files = filePath.listFiles();

                        for(int index = 0; index < files.length;index++){
                            filePathNames.add(files[index].getAbsolutePath());
                        }
                    }

                    for(int index = 0;index < filePathNames.size();index++){

                        final ImageView imageView = new ImageView(MainActivity.this);
                        imageView.setImageURI(Uri.parse(filePathNames.get(index)));
                        imageView.setLayoutParams( new LinearLayout.LayoutParams(200,200));

                        final int i = index;
                        imageView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                imageSwitcher.setImageURI(Uri.parse(filePathNames.get(i)));
                            }
                        });
                        linearLayoutHorizontal.addView(imageView);
                    }
                }
            }
        });

    }

    @Override
    public View makeView() {
        ImageView imageView = new ImageView(MainActivity.this);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        imageView.setLayoutParams(new ImageSwitcher.LayoutParams(500,500));
        return imageView;
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

    public  void letsSaveFiletoDocumentFolder(){

       File filePath = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS),
               "Mytext.txt");

       try{
           FileOutputStream fileOutputStream = new FileOutputStream(filePath);
           OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
           outputStreamWriter.append(edtValues.getText().toString());
           outputStreamWriter.close();
           fileOutputStream.close();
           letsCreateToast("Saved Successfully");
       } catch (Exception e){
          Log.i("LOG",e.toString());
          letsCreateToast("Exception Occured Check the logs for More details about the exception");
       }
    }

    public void letsRetrieveFileDatafromDocuments(){

        File filePath = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS),
                "Mytext.txt");
        try{
            FileInputStream fileInputStream = new FileInputStream(filePath);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
            String filedata = "";
            String bufferData = "";
            while((filedata = bufferedReader.readLine()) != null){
                bufferData = bufferData + filedata + "\n";
            }
           txtValues.setText(bufferData);
            bufferedReader.close();
        } catch (Exception e){
            Log.i("LOG",e.toString());
            letsCreateToast("Exception Occured Check the logs for More details about the exception");
        }


    }

    public void letsSaveimagetothePicturefolder(){

       try{
           Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.tiger);
           File filePath = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
                   "tigerphoto.png");
           OutputStream outputStream = new FileOutputStream(filePath);
           bitmap.compress(Bitmap.CompressFormat.PNG,100,outputStream);
           outputStream.flush();
           outputStream.close();
           letsCreateToast("Image Saved SuccessFully");
           } catch (Exception e){
           Log.i("LOG",e.toString());
           letsCreateToast("Exception Occured Check the logs for More details about the exception");
       }

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
               returnStorageDirectoryForFolderName(Environment.DIRECTORY_PICTURES,"AnimalImages");
               break;
           case R.id.btnSaveValue:
               letsSaveFiletoDocumentFolder();
               break;
           case R.id.btnRetrieveValue:
               letsRetrieveFileDatafromDocuments();
               break;
           case R.id.imgAnimal:
               letsSaveimagetothePicturefolder();
               break;
       }
    }
}
