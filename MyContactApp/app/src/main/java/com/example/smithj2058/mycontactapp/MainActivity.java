package com.example.smithj2058.mycontactapp;

import android.content.Context;
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
    Button btnAddData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDb = new DatabaseHelper(this);

        editName = (EditText) findViewById(R.id.editText_Name);
        editAge = (EditText) findViewById(R.id.editText_Age);
        editAddress = (EditText) findViewById(R.id.editText_Address);
        btnAddData = (Button) findViewById(R.id.button_Add);

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



}
