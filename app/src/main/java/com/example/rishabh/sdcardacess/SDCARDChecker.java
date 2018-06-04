package com.example.rishabh.sdcardacess;

import android.content.Context;
import android.os.Environment;
import android.widget.Toast;

public class SDCARDChecker {

    public static void checkWheatherExternalStorageAvailableOrNot(Context context){

        boolean isExternalStorageAvailable = false;
        boolean isExternalStorageWriteable = false;

        String state = Environment.getExternalStorageState();

        if(Environment.MEDIA_MOUNTED.equals(state)){
            //we can read and write the media
            isExternalStorageAvailable = isExternalStorageWriteable = true;
            Toast.makeText(context,"Read and Write",Toast.LENGTH_LONG).show();
        } else  if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)){
            //In this state we can only read the data
            isExternalStorageAvailable = true;
            isExternalStorageWriteable = false;
            Toast.makeText(context,"Read and Write",Toast.LENGTH_LONG).show();

        } else {
            //we acn neither read and write
            isExternalStorageAvailable = isExternalStorageWriteable = false;
            Toast.makeText(context,"Read and Write",Toast.LENGTH_LONG).show();
        }
    }
}
