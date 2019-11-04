package id.faiz.www.cobadatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    EditText nipEdit,nameEdit;
    Button saveButton,showButton;
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
            Log.i("DB", String.format("Nama pegawai: %s, NIP: %d",p.getNama(), p.getNip()));
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
        DatabasePegawai db = new DatabasePegawai(this);
        db.createPegawai(p);
    }



}
