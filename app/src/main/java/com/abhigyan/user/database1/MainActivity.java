package com.abhigyan.user.database1;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    MyDBHelper myDBHelper;
    EditText nameText, redgnoText, idText;

    public void insertFunction(View view)
    {//this will inser data  in the db
        myDBHelper.insertData(nameText.getText().toString(),redgnoText.getText().toString());
    }

    public void deleteFunction(View view)
    {
        myDBHelper.deleteData(idText.getText().toString());
    }
    public void viewFunction(View view)
    {
       Cursor res = myDBHelper.getAllData();
       if(res.getCount()==0)
       {// if cursor has a count of elements = 0 then show no data

           Toast.makeText(this, "No data!", Toast.LENGTH_SHORT).show();
       }
       else
       {
           StringBuffer buffer = new StringBuffer();
           while(res.moveToNext())
           {// a loop that will iterate through all the items
               buffer.append("ID: "+res.getString(0)+"\n");
               buffer.append("NAME: "+res.getString(1)+"\n");
               buffer.append("Reg: "+res.getString(2)+"\n\n");
           }
            Log.i("Message- ",buffer.toString());
       }
    }
    public void updateFunction(View view)
    {
        Boolean isUpdated = myDBHelper.updateData(idText.getText().toString(),nameText.getText().toString(),redgnoText.getText().toString());

        if(isUpdated == true)
        {
            Toast.makeText(this, "UPDATED!", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(this, "FAILED!", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDBHelper = new MyDBHelper(this);
        nameText = findViewById(R.id.nameText);
        nameText.setText(null);
        redgnoText = findViewById(R.id.regdText);
        redgnoText.setText(null);
        idText = findViewById(R.id.idText);

    }
}
