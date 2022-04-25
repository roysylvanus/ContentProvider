package com.malikali.contentproviderapp.data;


import android.net.Uri;
import android.provider.BaseColumns;

//Contract is designed to keep track of constants that will help you access data in a given database
public class ContractClass {



    //e.g
    public static final String AUTHORITY = "com.malikali.contentproviderapp";

    public static final Uri BASE_CONTENT_URI = Uri.parse("content://"+ AUTHORITY);

    public static final String PATH = "pathname";


    //define an inner class that displays the contents of the table

    public static final class NameOfClassEntry implements BaseColumns {

        public static final URI CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH).build();

        public static final String TABLE_NAME = "tABLE NAME";

    }
}
