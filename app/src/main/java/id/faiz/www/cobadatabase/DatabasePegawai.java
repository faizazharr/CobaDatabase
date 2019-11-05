package id.faiz.www.cobadatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DatabasePegawai extends SQLiteOpenHelper {
    Log log;
    private static final String DB_NAME = "db_pegawai";
    private static final int DB_VERSION = 2;
    private String CREATE_TABLE_PEGAWAI = "CREATE TABLE pegawai("+
            "_id INTEGER PRIMARY KEY, "+
            "nip INTEGER UNIQUE, "+
            "nama TEXT,"+
            "tgl_lahir TEXT," +
            "perkawinan TEXT);";
    private String CREATE_TABLE_TELEPON= "CREATE TABLE telepon(" +
            "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "id_pegawai INTEGER NOT NULL," +
            "kategori TEXT NOT NULL," +
            "nomor TEXT NOT NULL," +
            "FOREIGN KEY(id_pegawai) REFERENCES pegawai(_id)); ";

    private String SELECT_ALL_PEGAWAI = "SELECT * FROM pegawai LEFT JOIN telepon ON pegawai._id = TELEPON._id";
    public String databasePath ="";

    public DatabasePegawai(Context context) {
        super(context,DB_NAME, null,DB_VERSION);
        databasePath = context.getDatabasePath("db_pegawai").getPath();
    }

    public DatabasePegawai(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public DatabasePegawai(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version, @Nullable DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        log.d("DB","Membuat database");
        sqLiteDatabase.execSQL(CREATE_TABLE_PEGAWAI);
        sqLiteDatabase.execSQL(CREATE_TABLE_TELEPON);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        log.d("DB","Mengupgrade database " + i +" ke "+ i1);
        if (i1 == 2 && i == 1){
            sqLiteDatabase.execSQL(CREATE_TABLE_TELEPON);
        }
    }

    public void createPegawai(Pegawai pegawai){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("nip", pegawai.getNip());
        cv.put("nama", pegawai.getNama());
        cv.put("tgl_lahir", pegawai.getTanggallahir());
        cv.put("perkawinan", pegawai.getPerkawinan());
        db.insert("pegawai", null,cv);
        db.close();
    }

    public void createNomorTelpon(Telepon telepon){
        SQLiteDatabase db = getWritableDatabase();
    }

    public void deletePegawai(int nip){
        SQLiteDatabase db = getWritableDatabase();
        db.delete("pegawai","nip"+ String.valueOf(nip),null);
        db.close();
    }
    public void updatePegawai(Pegawai pegawai){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("nip",pegawai.getNip());
        cv.put("nama",pegawai.getNama());
        db.update("pegawai",cv,
                "nip="+ String.valueOf(pegawai.getNip()),
                null);
        db.close();
    }
    public List<Pegawai> getAllPegawai(){
        SQLiteDatabase db = getWritableDatabase();
        List<Pegawai> employees = new ArrayList<>();
        Cursor cursor = db.rawQuery(SELECT_ALL_PEGAWAI, null);

        if (cursor.moveToFirst()){
            do {
                Pegawai pegawai = new Pegawai();
                pegawai.setId(cursor.getInt(0));
                pegawai.setNip(cursor.getInt(1));
                pegawai.setNama(cursor.getString(2));
                pegawai.setTanggallahir(cursor.getString(3));
                pegawai.setPerkawinan(cursor.getString(4));
                employees.add(pegawai);
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return employees;
    }

}
