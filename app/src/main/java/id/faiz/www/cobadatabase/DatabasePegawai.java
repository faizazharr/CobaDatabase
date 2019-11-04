package id.faiz.www.cobadatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DatabasePegawai extends SQLiteOpenHelper {

    private static final String DB_NAME = "db_pegawai";
    private static final int DB_VERSION = 1;

    public DatabasePegawai(Context context) {
        super(context,DB_NAME, null,DB_VERSION);
    }

    public DatabasePegawai(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public DatabasePegawai(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version, @Nullable DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createTable = "CREATE TABLE pegawai("+
                "_id INTEGER PRIMARY KEY, "+
                "nip INTEGER UNIQUE, "+
                "nama TEXT);";
        sqLiteDatabase.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        if (i1 == 2 && i == 1){
            String sql = "ALTER TABLE pegawai ADD COLUMN jenis_kelamin INTEGER";
            sqLiteDatabase.execSQL(sql);
        }
    }

    public void createPegawai(Pegawai pegawai){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("nip", pegawai.getNip());
        cv.put("nama", pegawai.getNama());
        db.insert("pegawai", null,cv);
        db.close();
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
        Cursor cursor = db.rawQuery("SELECT * FROM pegawai", null);

        if (cursor.moveToFirst()){
            do {
                Pegawai pegawai = new Pegawai();
                pegawai.setId(cursor.getInt(0));
                pegawai.setNip(cursor.getInt(1));
                pegawai.setNama(cursor.getString(2));
                employees.add(pegawai);
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return employees;
    }

}
