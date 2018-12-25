package com.penduduk.penduduk;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class DBHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "PendudukDB";
    private static final String TABLE_NAME = "Penduduk";
    private static final String TABLE_NAME2 = "ART";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_ISBOOKMARK = "isBookmark";
    private static final String[] COLUMNS = { KEY_ID, KEY_NAME, KEY_ISBOOKMARK};

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATION_TABLE_ART = "create table ART ("
                + "id_ruta text null, "
                + "b4_k1 text null, "
                + "b4_k2a text null, "
                + "b4_k2b text null, "
                + "b4_k3 text null, "
                + "b4_k4 text null, "
                + "b4_k5 text null, "
                + "b4_k6 text null, "
                + "b4_k7 text null, "
                + "b4_k8 text null, "
                + "b4_k9 text null, "
                + "b4_k10 text null, "
                + "b4_k11 text null, "
                + "b4_k12 text null, "
                + "b4_k13 text null, "
                + "b4_k14 text null, "
                + "b4_k15 text null, "
                + "b4_k16 text null, "
                + "b4_k17 text null, "
                + "b4_k18 text null, "
                + "b4_k19a text null, "
                + "b4_k19b text null, "
                + "b4_k20 text null, "
                + "b4_k21 text null)";
        String CREATION_TABLE_PENDUDUK = "create table Penduduk ("
                        + "id_ruta text null, "
                        + "b1_k1a text null, "
                        + "b1_k1b text null, "
                        + "b1_k2a text null, "
                        + "b1_k2b text null, "
                        + "b1_k3a text null, "
                        + "b1_k3b text null, "
                        + "b1_k4a text null, "
                        + "b1_k4b text null, "
                        + "b1_k5 text null, "
                        + "b1_k6 text null, "
                        + "b1_k8 text null, "
                        + "b1_k9 text null, "
                        + "b1_k10 text null, "
                        + "b3_k1a text null, "
                        + "b3_k1b text null, "
                        + "b3_k2 text null, "
                        + "b3_k3 text null, "
                        + "b3_k4a text null, "
                        + "b3_k4b text null, "
                        + "b3_k5a text null, "
                        + "b3_k5b text null, "
                        + "b3_k6 text null, "
                        + "b3_k7 text null, "
                        + "b3_k8 text null, "
                        + "b3_k9a text null, "
                        + "b3_k9b text null, "
                        + "b3_k10 text null, "
                        + "b3_k11a text null, "
                        + "b3_k11b text null, "
                        + "b3_k12 text null, "
                        + "b5_k1a text null, "
                        + "b5_k1b text null, "
                        + "b5_k1c text null, "
                        + "b5_k1d text null, "
                        + "b5_k1e text null, "
                        + "b5_k1f text null, "
                        + "b5_k1g text null, "
                        + "b5_k1h text null, "
                        + "b5_k1i text null, "
                        + "b5_k1j text null, "
                        + "b5_k1k text null, "
                        + "b5_k1l text null, "
                        + "b5_k1m text null, "
                        + "b5_k1n text null, "
                        + "b5_k1o text null, "
                        + "b5_k2a1 text null, "
                        + "b5_k2a2 text null, "
                        + "b5_k2b text null, "
                        + "b5_k3a text null, "
                        + "b5_k3b text null, "
                        + "b5_k3c text null, "
                        + "b5_k3d text null, "
                        + "b5_k3e text null, "
                        + "b5_k4a text null, "
                        + "b5_k5a text null, "
                        + "b5_k5b text null, "
                        + "b5_k5c text null, "
                        + "b5_k5d text null, "
                        + "b5_k5e text null, "
                        + "b5_k5f text null, "
                        + "b5_k5g text null, "
                        + "b5_k5h text null, "
                        + "b5_k5i text null)";
        db.execSQL(CREATION_TABLE_PENDUDUK);
        db.execSQL(CREATION_TABLE_ART);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME2);
        this.onCreate(db);
    }

    public long updatePlayerPenduduk(String id_ruta, String b1_k1a, int b1_k1b, String b1_k2a, int b1_k2b, String b1_k3a, int b1_k3b, String b1_k4a, int b1_k4b, String b1_k5, String b1_k6, String b1_k8, int b1_k9, int b1_k10, int b3_k1a, int b3_k1b, String b3_k2, int b3_k3, int b3_k4a, int b3_k4b, int b3_k5a, int b3_k5b, int b3_k6, int b3_k7, int b3_k8, int b3_k9a, int b3_k9b, int b3_k10, int b3_k11a, int b3_k11b, int b3_k12, int b5_k1a, int b5_k1b, int b5_k1c, int b5_k1d, int b5_k1e, int b5_k1f, int b5_k1g, int b5_k1h, int b5_k1i, int b5_k1j, int b5_k1k, int b5_k1l, int b5_k1m, int b5_k1n, int b5_k1o, int b5_k2a1, int b5_k2a2, int b5_k2b, int b5_k3a, int b5_k3b, int b5_k3c, int b5_k3d, int b5_k3e, int b5_k4a, int b5_k5a, int b5_k5b, int b5_k5c, int b5_k5d, int b5_k5e, int b5_k5f, int b5_k5g, int b5_k5h, int b5_k5i, int b4_k1, String b4_k2a, String b4_k2b, int b4_k3, int b4_k4, int b4_k5, int b4_k6, int b4_k7, int b4_k8, int b4_k9, int b4_k10, int b4_k11, int b4_k12, int b4_k13, int b4_k14, int b4_k15, int b4_k16, int b4_k17, int b4_k18, int b4_k19a, String b4_k19b, int b4_k20, int b4_k21){
        try{
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put("id_ruta",id_ruta);
            contentValues.put("b1_k1a",b1_k1a);
            contentValues.put("b1_k1b",b1_k1b);
            contentValues.put("b1_k2a",b1_k2a);
            contentValues.put("b1_k2b",b1_k2b);
            contentValues.put("b1_k3a",b1_k3a);
            contentValues.put("b1_k3b",b1_k3b);
            contentValues.put("b1_k4a",b1_k4a);
            contentValues.put("b1_k4b",b1_k4b);
            contentValues.put("b1_k5",b1_k5);
            contentValues.put("b1_k6",b1_k6);
            contentValues.put("b1_k8",b1_k8);
            contentValues.put("b1_k9",b1_k9);
            contentValues.put("b1_k10",b1_k10);
            contentValues.put("b3_k1a",b3_k1a);
            contentValues.put("b3_k1b",b3_k1b);
            contentValues.put("b3_k2",b3_k2);
            contentValues.put("b3_k3",b3_k3);
            contentValues.put("b3_k4a",b3_k4a);
            contentValues.put("b3_k4b",b3_k4b);
            contentValues.put("b3_k5a",b3_k5a);
            contentValues.put("b3_k5b",b3_k5b);
            contentValues.put("b3_k6",b3_k6);
            contentValues.put("b3_k7",b3_k7);
            contentValues.put("b3_k8",b3_k8);
            contentValues.put("b3_k9a",b3_k9a);
            contentValues.put("b3_k9b",b3_k9b);
            contentValues.put("b3_k10",b3_k10);
            contentValues.put("b3_k11a",b3_k11a);
            contentValues.put("b3_k11b",b3_k11b);
            contentValues.put("b3_k12",b3_k12);
            contentValues.put("b5_k1a",b5_k1a);
            contentValues.put("b5_k1b",b5_k1b);
            contentValues.put("b5_k1c",b5_k1c);
            contentValues.put("b5_k1d",b5_k1d);
            contentValues.put("b5_k1e",b5_k1e);
            contentValues.put("b5_k1f",b5_k1f);
            contentValues.put("b5_k1g",b5_k1g);
            contentValues.put("b5_k1h",b5_k1h);
            contentValues.put("b5_k1i",b5_k1i);
            contentValues.put("b5_k1j",b5_k1j);
            contentValues.put("b5_k1k",b5_k1k);
            contentValues.put("b5_k1l",b5_k1l);
            contentValues.put("b5_k1m",b5_k1m);
            contentValues.put("b5_k1n",b5_k1n);
            contentValues.put("b5_k1o",b5_k1o);
            contentValues.put("b5_k2a1",b5_k2a1);
            contentValues.put("b5_k2a2",b5_k2a2);
            contentValues.put("b5_k2b",b5_k2b);
            contentValues.put("b5_k3a",b5_k3a);
            contentValues.put("b5_k3b",b5_k3b);
            contentValues.put("b5_k3c",b5_k3c);
            contentValues.put("b5_k3d",b5_k3d);
            contentValues.put("b5_k3e",b5_k3e);
            contentValues.put("b5_k4a",b5_k4a);
            contentValues.put("b5_k5a",b5_k5a);
            contentValues.put("b5_k5b",b5_k5b);
            contentValues.put("b5_k5c",b5_k5c);
            contentValues.put("b5_k5d",b5_k5d);
            contentValues.put("b5_k5e",b5_k5e);
            contentValues.put("b5_k5f",b5_k5f);
            contentValues.put("b5_k5g",b5_k5g);
            contentValues.put("b5_k5h",b5_k5h);
            contentValues.put("b5_k5i",b5_k5i);
            contentValues.put("b4_k1",b4_k1);
            contentValues.put("b4_k2a",b4_k2a);
            contentValues.put("b4_k2b",b4_k2b);
            contentValues.put("b4_k3",b4_k3);
            contentValues.put("b4_k4",b4_k4);
            contentValues.put("b4_k5",b4_k5);
            contentValues.put("b4_k6",b4_k6);
            contentValues.put("b4_k7",b4_k7);
            contentValues.put("b4_k8",b4_k8);
            contentValues.put("b4_k9",b4_k9);
            contentValues.put("b4_k10",b4_k10);
            contentValues.put("b4_k11",b4_k11);
            contentValues.put("b4_k12",b4_k12);
            contentValues.put("b4_k13",b4_k13);
            contentValues.put("b4_k14",b4_k14);
            contentValues.put("b4_k15",b4_k15);
            contentValues.put("b4_k16",b4_k16);
            contentValues.put("b4_k17",b4_k17);
            contentValues.put("b4_k18",b4_k18);
            contentValues.put("b4_k19a",b4_k19a);
            contentValues.put("b4_k19b",b4_k19b);
            contentValues.put("b4_k20",b4_k20);
            contentValues.put("b4_k21",b4_k21);
            return db.update(TABLE_NAME,contentValues,"b4_k2b =?",new String[] {String.valueOf(b4_k2b)});
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return 0;
    }

    public long deletePlayer(String id_ruta){
        try{
            SQLiteDatabase db = this.getWritableDatabase();
            db.delete(TABLE_NAME,"id_ruta =?",new String[]{String.valueOf(id_ruta)});
            return db.delete(TABLE_NAME2,"id_ruta =?",new String[]{String.valueOf(id_ruta)});
        }catch (Exception ex){
            Log.e("TAGG",ex.toString());
        }
        return 0;
    }

    public long addART(String id_ruta,int b4_k1, String b4_k2a, String b4_k2b, int b4_k3, int b4_k4, int b4_k5, int b4_k6, int b4_k7, int b4_k8, int b4_k9, int b4_k10, int b4_k11, int b4_k12, int b4_k13, int b4_k14, int b4_k15, int b4_k16, int b4_k17, int b4_k18, int b4_k19a, String b4_k19b, int b4_k20, int b4_k21){
        try{
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put("id_ruta",id_ruta);
            contentValues.put("b4_k1",b4_k1);
            contentValues.put("b4_k2a",b4_k2a);
            contentValues.put("b4_k2b",b4_k2b);
            contentValues.put("b4_k3",b4_k3);
            contentValues.put("b4_k4",b4_k4);
            contentValues.put("b4_k5",b4_k5);
            contentValues.put("b4_k6",b4_k6);
            contentValues.put("b4_k7",b4_k7);
            contentValues.put("b4_k8",b4_k8);
            contentValues.put("b4_k9",b4_k9);
            contentValues.put("b4_k10",b4_k10);
            contentValues.put("b4_k11",b4_k11);
            contentValues.put("b4_k12",b4_k12);
            contentValues.put("b4_k13",b4_k13);
            contentValues.put("b4_k14",b4_k14);
            contentValues.put("b4_k15",b4_k15);
            contentValues.put("b4_k16",b4_k16);
            contentValues.put("b4_k17",b4_k17);
            contentValues.put("b4_k18",b4_k18);
            contentValues.put("b4_k19a",b4_k19a);
            contentValues.put("b4_k19b",b4_k19b);
            contentValues.put("b4_k20",b4_k20);
            contentValues.put("b4_k21",b4_k21);
            return db.insertOrThrow(TABLE_NAME2,null,contentValues);
        }catch (Exception ex){
            Log.e("TAGG",ex.toString());
        }
        return 0;
    }

    public long addPlayers(String id_ruta, String b1_k1a, int b1_k1b, String b1_k2a, int b1_k2b, String b1_k3a, int b1_k3b, String b1_k4a, int b1_k4b, String b1_k5, String b1_k6, String b1_k8, int b1_k9, int b1_k10, int b3_k1a, int b3_k1b, String b3_k2, int b3_k3, int b3_k4a, int b3_k4b, int b3_k5a, int b3_k5b, int b3_k6, int b3_k7, int b3_k8, int b3_k9a, int b3_k9b, int b3_k10, int b3_k11a, int b3_k11b, int b3_k12, int b5_k1a, int b5_k1b, int b5_k1c, int b5_k1d, int b5_k1e, int b5_k1f, int b5_k1g, int b5_k1h, int b5_k1i, int b5_k1j, int b5_k1k, int b5_k1l, int b5_k1m, int b5_k1n, int b5_k1o, int b5_k2a1, int b5_k2a2, int b5_k2b, int b5_k3a, int b5_k3b, int b5_k3c, int b5_k3d, int b5_k3e, int b5_k4a, int b5_k5a, int b5_k5b, int b5_k5c, int b5_k5d, int b5_k5e, int b5_k5f, int b5_k5g, int b5_k5h, int b5_k5i){
        try{
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put("id_ruta",id_ruta);
            contentValues.put("b1_k1a",b1_k1a);
            contentValues.put("b1_k1b",b1_k1b);
            contentValues.put("b1_k2a",b1_k2a);
            contentValues.put("b1_k2b",b1_k2b);
            contentValues.put("b1_k3a",b1_k3a);
            contentValues.put("b1_k3b",b1_k3b);
            contentValues.put("b1_k4a",b1_k4a);
            contentValues.put("b1_k4b",b1_k4b);
            contentValues.put("b1_k5",b1_k5);
            contentValues.put("b1_k6",b1_k6);
            contentValues.put("b1_k8",b1_k8);
            contentValues.put("b1_k9",b1_k9);
            contentValues.put("b1_k10",b1_k10);
            contentValues.put("b3_k1a",b3_k1a);
            contentValues.put("b3_k1b",b3_k1b);
            contentValues.put("b3_k2",b3_k2);
            contentValues.put("b3_k3",b3_k3);
            contentValues.put("b3_k4a",b3_k4a);
            contentValues.put("b3_k4b",b3_k4b);
            contentValues.put("b3_k5a",b3_k5a);
            contentValues.put("b3_k5b",b3_k5b);
            contentValues.put("b3_k6",b3_k6);
            contentValues.put("b3_k7",b3_k7);
            contentValues.put("b3_k8",b3_k8);
            contentValues.put("b3_k9a",b3_k9a);
            contentValues.put("b3_k9b",b3_k9b);
            contentValues.put("b3_k10",b3_k10);
            contentValues.put("b3_k11a",b3_k11a);
            contentValues.put("b3_k11b",b3_k11b);
            contentValues.put("b3_k12",b3_k12);
            contentValues.put("b5_k1a",b5_k1a);
            contentValues.put("b5_k1b",b5_k1b);
            contentValues.put("b5_k1c",b5_k1c);
            contentValues.put("b5_k1d",b5_k1d);
            contentValues.put("b5_k1e",b5_k1e);
            contentValues.put("b5_k1f",b5_k1f);
            contentValues.put("b5_k1g",b5_k1g);
            contentValues.put("b5_k1h",b5_k1h);
            contentValues.put("b5_k1i",b5_k1i);
            contentValues.put("b5_k1j",b5_k1j);
            contentValues.put("b5_k1k",b5_k1k);
            contentValues.put("b5_k1l",b5_k1l);
            contentValues.put("b5_k1m",b5_k1m);
            contentValues.put("b5_k1n",b5_k1n);
            contentValues.put("b5_k1o",b5_k1o);
            contentValues.put("b5_k2a1",b5_k2a1);
            contentValues.put("b5_k2a2",b5_k2a2);
            contentValues.put("b5_k2b",b5_k2b);
            contentValues.put("b5_k3a",b5_k3a);
            contentValues.put("b5_k3b",b5_k3b);
            contentValues.put("b5_k3c",b5_k3c);
            contentValues.put("b5_k3d",b5_k3d);
            contentValues.put("b5_k3e",b5_k3e);
            contentValues.put("b5_k4a",b5_k4a);
            contentValues.put("b5_k5a",b5_k5a);
            contentValues.put("b5_k5b",b5_k5b);
            contentValues.put("b5_k5c",b5_k5c);
            contentValues.put("b5_k5d",b5_k5d);
            contentValues.put("b5_k5e",b5_k5e);
            contentValues.put("b5_k5f",b5_k5f);
            contentValues.put("b5_k5g",b5_k5g);
            contentValues.put("b5_k5h",b5_k5h);
            contentValues.put("b5_k5i",b5_k5i);
            return db.insertOrThrow(TABLE_NAME,null,contentValues);
        }catch (Exception ex){
            Log.e("TAGG",ex.toString());
        }
        return 0;
    }

    public int getCount(boolean isPenduduk){
        SQLiteDatabase db  = this.getWritableDatabase();
        Cursor cursor = null;
        try{
            if(isPenduduk){
                cursor = db.rawQuery("SELECT * FROM Penduduk",null);
            }else {
                cursor = db.rawQuery("SELECT * FROM ART",null);
            }
        }catch (Exception ex){
            Log.e("TAGG",ex.toString());
        }
        return cursor.getCount();
    }

    public List<Penduduk> allPlayers() {
        List<Penduduk> penduduks = new ArrayList<>();
        String selectQuery = "SELECT  * FROM Penduduk";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Cursor cursor2 = db.rawQuery("SELECT * FROM ART WHERE id_ruta="+cursor.getString(cursor.getColumnIndex("id_ruta")), null);
                List<DataART> listART = new ArrayList<DataART>();
                if(cursor2.moveToFirst()) {
                    do {
                        DataART newART = new DataART(
                                cursor2.getInt(1),
                                cursor2.getString(2),
                                cursor2.getString(3),
                                cursor2.getInt(4),
                                cursor2.getInt(5),
                                cursor2.getInt(6),
                                cursor2.getInt(7),
                                cursor2.getInt(8),
                                cursor2.getInt(9),
                                cursor2.getInt(10),
                                cursor2.getInt(11),
                                cursor2.getInt(12),
                                cursor2.getInt(13),
                                cursor2.getInt(14),
                                cursor2.getInt(15),
                                cursor2.getInt(16),
                                cursor2.getInt(17),
                                cursor2.getInt(18),
                                cursor2.getInt(19),
                                cursor2.getInt(20),
                                cursor2.getString(21),
                                cursor2.getInt(22),
                                cursor2.getInt(23)
                        );
                        listART.add(newART);
                    } while (cursor2.moveToNext());
                }
                Penduduk penduduk = new Penduduk(
                        cursor.getString(cursor.getColumnIndex("id_ruta")),
                        cursor.getString(cursor.getColumnIndex("b1_k1a")),
                        cursor.getInt(cursor.getColumnIndex("b1_k1b")),
                        cursor.getString(cursor.getColumnIndex("b1_k2a")),
                        cursor.getInt(cursor.getColumnIndex("b1_k2b")),
                        cursor.getString(cursor.getColumnIndex("b1_k3a")),
                        cursor.getInt(cursor.getColumnIndex("b1_k3b")),
                        cursor.getString(cursor.getColumnIndex("b1_k4a")),
                        cursor.getInt(cursor.getColumnIndex("b1_k4b")),
                        cursor.getString(cursor.getColumnIndex("b1_k5")),
                        cursor.getString(cursor.getColumnIndex("b1_k6")),
                        cursor.getString(cursor.getColumnIndex("b1_k8")),
                        cursor.getInt(cursor.getColumnIndex("b1_k9")),
                        cursor.getInt(cursor.getColumnIndex("b1_k10")),
                        cursor.getInt(cursor.getColumnIndex("b3_k1a")),
                        cursor.getInt(cursor.getColumnIndex("b3_k1b")),
                        cursor.getString(cursor.getColumnIndex("b3_k2")),
                        cursor.getInt(cursor.getColumnIndex("b3_k3")),
                        cursor.getInt(cursor.getColumnIndex("b3_k4a")),
                        cursor.getInt(cursor.getColumnIndex("b3_k4b")),
                        cursor.getInt(cursor.getColumnIndex("b3_k5a")),
                        cursor.getInt(cursor.getColumnIndex("b3_k5b")),
                        cursor.getInt(cursor.getColumnIndex("b3_k6")),
                        cursor.getInt(cursor.getColumnIndex("b3_k7")),
                        cursor.getInt(cursor.getColumnIndex("b3_k8")),
                        cursor.getInt(cursor.getColumnIndex("b3_k9a")),
                        cursor.getInt(cursor.getColumnIndex("b3_k9b")),
                        cursor.getInt(cursor.getColumnIndex("b3_k10")),
                        cursor.getInt(cursor.getColumnIndex("b3_k11a")),
                        cursor.getInt(cursor.getColumnIndex("b3_k11b")),
                        cursor.getInt(cursor.getColumnIndex("b3_k12")),
                        cursor.getInt(cursor.getColumnIndex("b5_k1a")),
                        cursor.getInt(cursor.getColumnIndex("b5_k1b")),
                        cursor.getInt(cursor.getColumnIndex("b5_k1c")),
                        cursor.getInt(cursor.getColumnIndex("b5_k1d")),
                        cursor.getInt(cursor.getColumnIndex("b5_k1e")),
                        cursor.getInt(cursor.getColumnIndex("b5_k1f")),
                        cursor.getInt(cursor.getColumnIndex("b5_k1g")),
                        cursor.getInt(cursor.getColumnIndex("b5_k1h")),
                        cursor.getInt(cursor.getColumnIndex("b5_k1i")),
                        cursor.getInt(cursor.getColumnIndex("b5_k1j")),
                        cursor.getInt(cursor.getColumnIndex("b5_k1k")),
                        cursor.getInt(cursor.getColumnIndex("b5_k1l")),
                        cursor.getInt(cursor.getColumnIndex("b5_k1m")),
                        cursor.getInt(cursor.getColumnIndex("b5_k1n")),
                        cursor.getInt(cursor.getColumnIndex("b5_k1o")),
                        cursor.getInt(cursor.getColumnIndex("b5_k2a1")),
                        cursor.getInt(cursor.getColumnIndex("b5_k2a2")),
                        cursor.getInt(cursor.getColumnIndex("b5_k2b")),
                        cursor.getInt(cursor.getColumnIndex("b5_k3a")),
                        cursor.getInt(cursor.getColumnIndex("b5_k3b")),
                        cursor.getInt(cursor.getColumnIndex("b5_k3c")),
                        cursor.getInt(cursor.getColumnIndex("b5_k3d")),
                        cursor.getInt(cursor.getColumnIndex("b5_k3e")),
                        cursor.getInt(cursor.getColumnIndex("b5_k4a")),
                        cursor.getInt(cursor.getColumnIndex("b5_k5a")),
                        cursor.getInt(cursor.getColumnIndex("b5_k5b")),
                        cursor.getInt(cursor.getColumnIndex("b5_k5c")),
                        cursor.getInt(cursor.getColumnIndex("b5_k5d")),
                        cursor.getInt(cursor.getColumnIndex("b5_k5e")),
                        cursor.getInt(cursor.getColumnIndex("b5_k5f")),
                        cursor.getInt(cursor.getColumnIndex("b5_k5g")),
                        cursor.getInt(cursor.getColumnIndex("b5_k5h")),
                        cursor.getInt(cursor.getColumnIndex("b5_k5i")),
                        listART
                );
                penduduks.add(penduduk);
            } while (cursor.moveToNext());
        }

        db.close();
        return penduduks;
    }
}
