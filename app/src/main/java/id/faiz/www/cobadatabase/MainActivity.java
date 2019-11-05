package id.faiz.www.cobadatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    EditText nipEdit,nameEdit,housephone,cellulerphone;
    Button saveButton,showButton;
    DatePicker date_tanggallahir;
    String text_tanggallahir;
    int day,month,year;
    Spinner spinnerkawin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spinnerkawin = findViewById(R.id.spinner_perkawinan);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.perkwainan, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerkawin.setAdapter(adapter);

        nipEdit = findViewById(R.id.nipEdit);
        nameEdit= findViewById(R.id.nameEdit);
        date_tanggallahir = (DatePicker) findViewById(R.id.tanggallahir);
        day = date_tanggallahir.getDayOfMonth();
        month= date_tanggallahir.getMonth();
        year = date_tanggallahir.getYear();
        text_tanggallahir = String.valueOf(day) +"-"+ String.valueOf(month) +"-"+ String.valueOf(year);

        housephone = findViewById(R.id.houseNumber);
        cellulerphone = findViewById(R.id.phoneNumber);

        saveButton = findViewById(R.id.saveButton);
        showButton = findViewById(R.id.showButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveData();
                cleanFields();
            }
        });

        showButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                printDataToLog();
            }
        });
    }

    private void printDataToLog() {
        DatabasePegawai db = new DatabasePegawai(this);
        Log.i("DB","--- DATA PEGAWAI DI DATABASE ---");
        List<Pegawai> pegawaiList = db.getAllPegawai();
        for (Pegawai p : pegawaiList){
            Log.i("DB", String.format("Nama pegawai: %s, NIP: %d, Tanggal Lahir: %s, Status Kawin: %s",
                    p.getNama(), p.getNip(),p.getTanggallahir(),p.getPerkawinan()));
        }

    }

    private void cleanFields() {
        nipEdit.setText("");
        nameEdit.setText("");
    }

    private void saveData() {
    Pegawai p = new Pegawai();

        p.setNip(Integer.valueOf(nipEdit.getText().toString()));
        p.setNama(nameEdit.getText().toString());
        p.setTanggallahir(text_tanggallahir);
        p.setPerkawinan(spinnerkawin.getSelectedItem().toString());

        DatabasePegawai db = new DatabasePegawai(this);
        db.createPegawai(p);
    }



}
