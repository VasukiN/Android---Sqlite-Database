package com.example.vasuki.database;

import android.content.Intent;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText textView1,textView2;
    Button insert,update,delete,select;
    DatabaseHelp db ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView1 =(EditText) findViewById(R.id.name1);
        textView2 =(EditText) findViewById(R.id.phnum1);
        insert = (Button)findViewById(R.id.insert);
        update =(Button)findViewById(R.id.update);
        delete =(Button)findViewById(R.id.delet);
        select =(Button)findViewById(R.id.select);
        db = new DatabaseHelp(MainActivity.this);
        insert.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                String name,phnum;
                try
                {
                    name = textView1.getText().toString();
                    phnum = textView2.getText().toString();
                    if((name=="")||(phnum==""))
                    {
                        Toast.makeText(MainActivity.this, "Please enter the field", Toast.LENGTH_SHORT).show();
                    }

                    else
                        db.addDetails(name, phnum);
                        Toast.makeText(MainActivity.this, "inserted successfully", Toast.LENGTH_SHORT).show();

                }catch (Exception e)
                {
                    e.printStackTrace();
                    //Toast.makeText(MainActivity.this,"Please enter the text box values",Toast.LENGTH_SHORT).show();
                }
            }
        });
        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,RetrieveActivity.class);

                startActivity(intent);
            }
        });

    }
}
