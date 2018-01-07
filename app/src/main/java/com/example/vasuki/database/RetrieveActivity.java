package com.example.vasuki.database;

import android.content.Context;
import android.content.DialogInterface;
import android.preference.DialogPreference;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class RetrieveActivity extends AppCompatActivity {

    ListView listView;
    DatabaseHelp db ;
    ArrayList<ModuleClass> moduleClass;
    EditText name, phonum;
    Button update,delete;
    AlertDialog alertDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrieve);

        listView = (ListView) findViewById(R.id.list_item);
        Customized adpater = new Customized(RetrieveActivity.this,0);
        db=new DatabaseHelp(RetrieveActivity.this);

        moduleClass = new ArrayList<>();
        moduleClass = db.retriveData();
        listView.setAdapter(adpater);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int i, long l) {
                view = LayoutInflater.from(RetrieveActivity.this).inflate(R.layout.upate_count, null);
                name = (EditText) view.findViewById(R.id.upname);
                phonum = (EditText) view.findViewById(R.id.upphnum);
                name.setText(moduleClass.get(i).getName());
                phonum.setText(moduleClass.get(i).getPhnumber());
                update = (Button) view.findViewById(R.id.update2);
                delete = (Button) view.findViewById(R.id.delete2);

                AlertDialog.Builder builder;
                builder = new AlertDialog.Builder(RetrieveActivity.this);
                update.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {

                        String name1 = name.getText().toString();
                        String phnum1 = phonum.getText().toString();
                        int id = moduleClass.get(i).getId();
                        db.update(name1, phnum1, id);
                        alertDialog.cancel();

                    }
                });
                delete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int id=moduleClass.get(i).getId();
                        db.delete(id);
                        alertDialog.cancel();
                    }
                });


            }

        }); }



    public class Customized extends ArrayAdapter
    {
        TextView name,phoneNum;
        public Customized(Context context, int resource) {
            super(context, resource);
        }

        @Override
        public int getCount() {
            return moduleClass.size();
        }

        @NonNull
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            if(convertView==null)
            {
                LayoutInflater inflater= LayoutInflater.from(RetrieveActivity.this);
                convertView = inflater.inflate(R.layout.row_count,null);
                name=(TextView) convertView.findViewById(R.id.name);
                phoneNum=(TextView) convertView.findViewById(R.id.phonenum);
            }
            name.setText(moduleClass.get(position).getName());
            phoneNum.setText(moduleClass.get(position).getPhnumber());
            return convertView;

        }
    }
}

