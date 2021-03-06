package com.example.mycontactapp;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper myDb;
    EditText editName;
    EditText editPhone;
    EditText editAddress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editName = findViewById(R.id.editText_name);
        editPhone = findViewById(R.id.editText_phone);
        editAddress = findViewById(R.id.editText_address);
        myDb = new DatabaseHelper(this);
        Log.d("MyContactApp","DatabaseHelper: instantiated the DatabaseHelper");

    }
    public void addData(View view){
        boolean isInserted = myDb.insertData(editName.getText().toString(),editPhone.getText().toString(),editAddress.getText().toString());
        if(isInserted == true){
            Toast.makeText(MainActivity.this,"Success - contact inserted", Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(MainActivity.this, "Failed - contact not inserted", Toast.LENGTH_LONG).show();
        }
    }
    public void viewData (View view){
        Cursor res = myDb.getAllData();
        //put Log.d's here
        if(res.getCount() == 0){
            showMessage("Error", "No Data found in database");
            return;
        }
        StringBuffer buffer = new StringBuffer();
        while(res.moveToNext()){
            //Append res columns to the buffer- see StringBuffer and Cursor API
            buffer.append("ID: " + res.getString(0) + "\n");
            buffer.append("Name: " + res.getString(1) + "\n");
            buffer.append("Phone: " + res.getString(2) + "\n");
            buffer.append("Address: " + res.getString(3) + "\n");

        }
        showMessage("Data", buffer.toString());

    }
    public void showMessage(String title, String message){
        //put Lod.d's here
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
    public void clearAll(View view){
        Log.d("MyContactApp", "request to delete received");
        myDb.deleteAllData();

    }
    public void searchContact(View view){
        Log.d("MyContactApp","request to search received");
        Cursor res = myDb.getAllData();
        String name = editName.getText().toString();
        String phone = editPhone.getText().toString();
        String address = editAddress.getText().toString();
        StringBuffer buffer = new StringBuffer();
        if(name.length() <= 0 && phone.length() <= 0 && address.length() <= 0){
            Log.d("MyContactApp", "Error, no contact found");
            return;
        }
        while(res.moveToNext()) {
            if ((name.isEmpty() || name.equals(res.getString(1)))
                    && phone.isEmpty() || phone.equals(res.getString(2))
                    && address.isEmpty() || address.equals(res.getString(3))) {
                buffer.append("ID: " + res.getString(0) + "\n" +
                        "Name: " + res.getString(1) + "\n" +
                        "Phone Number: " + res.getString(2) + "\n" +
                        "Home Address: " + res.getString(3) + "\n\n");
            }
        }
        if(buffer.toString().isEmpty()){
            showMessage("Error","No matches found");
            return;
        }
        res.close();
        showMessage("Search Results", buffer.toString());
    }
}
