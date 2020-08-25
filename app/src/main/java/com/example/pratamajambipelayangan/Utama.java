package com.example.pratamajambipelayangan;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Utama extends AppCompatActivity {

    private Button btnMasuk;
    private EditText isiNPWP;
    private ImageButton btnExit;

    private String TAG_WRITE_READ_FILE = "TAG_WRITE_READ_FILE";

    private String userNPWPFileName = "npwpuser.txt";

    int success;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_utama);

        btnMasuk = findViewById(R.id.btnMasuk);
        btnExit = findViewById(R.id.btnExit);
        final Context ctx = getApplicationContext();

        isiNPWP = findViewById(R.id.txtNPWP);
        isiNPWP.addTextChangedListener(loginTextWatcher);


        try {

            FileInputStream fileInputStream = ctx.openFileInput(userNPWPFileName);

            String fileData = readFromFileInputStream(fileInputStream);

            if(fileData.length()>0) {
                isiNPWP.setText(fileData);
                isiNPWP.setSelection(fileData.length());
            }
        }catch(FileNotFoundException ex)
        {
            Log.e(TAG_WRITE_READ_FILE, ex.getMessage(), ex);
        }

        btnMasuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputNPWP = isiNPWP.getText().toString().trim();
                String npwp15digit = inputNPWP.replaceAll("[^0-9]","");

                if(success == 1) {
                    try
                    {
                        FileOutputStream fileOutputStream = ctx.openFileOutput(userNPWPFileName, Context.MODE_PRIVATE);
                        writeDataToFile(fileOutputStream, inputNPWP);
                    }catch(FileNotFoundException ex)
                    {
                        Log.e(TAG_WRITE_READ_FILE, ex.getMessage(), ex);
                    }

                    Toast.makeText(ctx, "NPWP berhasil login!", Toast.LENGTH_LONG).show();

                    Intent intent = new Intent(Utama.this, MainActivity.class);
                    intent.putExtra(konfigurasi.EMP_ID,npwp15digit);
                    startActivity(intent);
                }   else{
                    Toast.makeText(ctx, "NPWP Tidak Terdaftar!", Toast.LENGTH_LONG).show();

                }}
        });

        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Utama.this);
                builder.setMessage("Tutup Aplikasi?")
                        .setCancelable(false)
                        .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                            }
                        })
                        .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();

            }
        });
    }

    private TextWatcher loginTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String inputNPWP = isiNPWP.getText().toString().trim();

            if(inputNPWP.length() == 20)
            {
                btnMasuk.setEnabled(true);
                getDataWP();
            }

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };


    // This method will write data to FileOutputStream.
    private void writeDataToFile(FileOutputStream fileOutputStream, String data)
    {
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
            BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);

            bufferedWriter.write(data);

            bufferedWriter.flush();
            bufferedWriter.close();
            outputStreamWriter.close();
        }catch(FileNotFoundException ex)
        {
            Log.e(TAG_WRITE_READ_FILE, ex.getMessage(), ex);
        }catch(IOException ex)
        {
            Log.e(TAG_WRITE_READ_FILE, ex.getMessage(), ex);
        }
    }

    // This method will read data from FileInputStream.
    private String readFromFileInputStream(FileInputStream fileInputStream)
    {
        StringBuffer retBuf = new StringBuffer();

        try {
            if (fileInputStream != null) {
                InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                String lineData = bufferedReader.readLine();
                while (lineData != null) {
                    retBuf.append(lineData);
                    lineData = bufferedReader.readLine();
                }
            }
        }catch(IOException ex)
        {
            Log.e(TAG_WRITE_READ_FILE, ex.getMessage(), ex);
        }finally
        {
            return retBuf.toString();
        }
    }

    private void getDataWP(){

        String inputNPWP = isiNPWP.getText().toString().trim();
        final String npwp15digit = inputNPWP.replaceAll("[^0-9]","");

        class GetDataWP extends AsyncTask<Void, Void, String> {
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(Utama.this,"Mengambil data...","Silahkan tunggu...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                showDataWP(s);
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequestParam(konfigurasi.URL_CHECK_NPWP,npwp15digit);
                return s;
            }
        }
        GetDataWP ge = new GetDataWP();
        ge.execute();
    }

    private void showDataWP(String json){
        try {
            JSONObject c = new JSONObject(json).getJSONArray(konfigurasi.TAG_JSON_ARRAY).getJSONObject(0);
            success = c.getInt(konfigurasi.TAG_HITUNG);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void goToTanpaNPWP (View view)    {
        Intent tanpaNPWP = new Intent(Utama.this, MainActivity.class);
        startActivity(tanpaNPWP);
    }

}
