package com.malikali.contentproviderapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.net.Uri;
import android.database.Cursor;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }




    public void btnGetContactPressed(View view) {
        getContacts();
    }






    private void getContacts(){

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_CONTACTS},0);
        }


        /*
        * Content provider is a part of android application that is used to help to provide its data to other applications

A content provider manages access to a central repository of data. A provider is part of an Android application, which often provides its own UI for working with the data. However, content providers are primarily intended to be used by other applications, which access the provider using a provider client object. Together, providers and provider clients offer a consistent, standard interface to data that also handles inter-process communication and secure data access.

e.g Your app can get contacts from Contacts App using the content provider

Typically you work with content providers in one of two scenarios; you may want to implement code to access an existing content provider in another application, or you may want to create a new content provider in your application to share data with other applications.

* A content URI is a URI that identifies data in a provider. Content URIs include the symbolic name of the entire provider (its authority) and a name that points to a table (a path)


        *  */

        ContentResolver contentResolver = getContentResolver();

        Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        Cursor cursor = contentResolver.query(uri,null,null,null,null);
        Log.e("CONTACTS",Integer.toString(cursor.getCount()));
        if (cursor.getCount()>0){

            //moveToNext() move to next is a function to get data from each row in a cursor - returns true ot false
            //moveToFirst() gets data on the first row
            //getColumnIndex() gets the column index of the table
            //getCount() returns number of rows in a cursor
            //get<Type>(int ColumnIndex) returns the value at the column Index

            while (cursor.moveToNext()){

                String contactName = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                String contactNumber = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));

                Log.e("CONTACT NAME & nUMBER" , contactName + " " + contactNumber);
            }
        }
        cursor.close();
    }

    public void btnInsertPressed(View view) {

        insertContacts();
    }
}