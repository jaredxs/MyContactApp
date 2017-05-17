package com.example.smithj2058.mycontactapp;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper myDb;
    EditText editName;
    EditText editAge;
    EditText editAddress;
    EditText editSearch;
    String[] fields;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDb = new DatabaseHelper(this);

        editName = (EditText) findViewById(R.id.editText_Name);
        editAge = (EditText) findViewById(R.id.editText_Age);
        editAddress = (EditText) findViewById(R.id.editText_Address);
        editSearch = (EditText) findViewById(R.id.editText_Search);
        fields = new String[4];
        fields[0] = "ID - ";
        fields[1] = "Name - ";
        fields[2] = "Age - ";
        fields[3] = "Address - ";


    }
    public void addData(View view){
        boolean isInserted = myDb.insertData(editName.getText().toString(), editAge.getText().toString(), editAddress.getText().toString());
        Context context = getApplicationContext();
        CharSequence text;
        int duration = Toast.LENGTH_SHORT;

        if(isInserted){
            Log.d("MyContact", "Successfuly Inserted");
            text = "Inserted Data";
            Toast toast = Toast.makeText(context, text, duration);
            toast.setGravity(Gravity.TOP|Gravity.LEFT, 130, 720);
            toast.show();
        }
        else{
            text = "Failed to Insert Data";
            Log.d("MyContact", "Failure to Insert");
            Toast toast = Toast.makeText(context,text,duration);
            toast.setGravity(Gravity.TOP|Gravity.LEFT, 130, 720);
            toast.show();
        }

    }

    public void viewData(View view){
        Cursor res = myDb.getAllData();
        if(res.getCount() == 0){
            //Show Message || Error - No Data Found In Database
            showMessage("Error", "No Data Found in Database");
            //Output message using Log.D and Toast
            return;
        }
        StringBuffer buffer = new StringBuffer();
        res.moveToFirst();
       //Setup loop with cursor (res) using moveToNext
        for(int i = 0; i < res.getCount(); i++){
            for(int j = 0; j <= 3; j++) {
                buffer.append(fields[j]);
                buffer.append(res.getString(j));
                buffer.append("\n");
            }
            res.moveToNext();
        }

        showMessage("Data", buffer.toString());
        System.out.println(buffer);


    }

    private void showMessage(String title, String message){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setCancelable(true);
        alertDialogBuilder.setTitle(title);
        alertDialogBuilder.setMessage(message);
        alertDialogBuilder.show();
    }
    public void searchMessage(View view){
        String searcher = editSearch.getText().toString();
        StringBuffer buffer = new StringBuffer();
        Cursor res = myDb.getAllData();
        res.moveToFirst();
        for(int i = 0; i < res.getCount(); i++){
            if(res.getString(1).equals(searcher)){
                for(int j = 0; j <= 3; j++) {
                    buffer.append(fields[j]);
                    buffer.append(res.getString(j));
                    buffer.append("\n");
                }
            }
            res.moveToNext();
        }
        if(buffer.toString().equals("")){
            showMessage("No Search Results Found", "");
        }
        else {
            showMessage("Search Results", buffer.toString());
        }
    }

}
