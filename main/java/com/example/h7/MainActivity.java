package com.example.h7;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    Context context = null;

    TextView text2;
    TextView text3;
    TextView text4;
    EditText edit3;
    EditText edit5_file;
    EditText edit5_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text2 = (TextView) findViewById(R.id.textView);
        text3 = (TextView) findViewById(R.id.textView3);
        text4 = (TextView) findViewById(R.id.textView4);
        edit3 = (EditText) findViewById(R.id.editTextTextPersonName);
        edit5_text = (EditText) findViewById(R.id.editTextTextPersonName2);
        edit5_file = (EditText) findViewById(R.id.editTextTextPersonName3);
        context = MainActivity.this;

        System.out.println(context.getFilesDir());

        edit3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                String teksti = edit3.getText().toString();
                text4.setText(teksti);
            }
        });
    }



    public void printStuff(View v) {
        System.out.println("Hello World!");
    }

    public void teht2(View v){
        text2.setText("Hello World!");
    }

    public void teht3(View v) {
        String teksti = edit3.getText().toString();
        text3.setText(teksti);
    }

    public void readFile(View v){
        String fileName = edit5_file.getText().toString();

        try {
            InputStream ins = context.openFileInput(fileName);     // TODO Näin voi ilmaista, että pitää tehdä juttuja.
            BufferedReader br = new BufferedReader(new InputStreamReader(ins));
            String s_temp = "sdf";
            String s = "Default_value";
            while((s_temp=br.readLine()) != null){
                s = s_temp;
            }
            ins.close();
            System.out.println("Stringin säslö:"+s);
            edit5_text.setText(s, TextView.BufferType.EDITABLE);
        } catch (IOException e) {
            Log.e("IOException", "Virhe syötteessä");
        } finally {
            System.out.println("readFile meni läpi");
        }

    }

    public void writeFile(View v){
        String fileName = edit5_file.getText().toString();
        try {
            OutputStreamWriter ows = new OutputStreamWriter(context.openFileOutput(fileName, Context.MODE_PRIVATE));

            String s = edit5_text.getText().toString();
            ows.write(s);
            ows.close();

        } catch (IOException e) {
            Log.e("IOException", "Virhe syötteessä");
        } finally {
            System.out.println("writeFile meni läpi");
        }
    }

}