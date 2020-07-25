package tdc.edu.vn.projectquanlychamcongvsactivity.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import java.util.ArrayList;

import tdc.edu.vn.projectquanlychamcongvsactivity.DBhepler.Dbhelper;
import tdc.edu.vn.projectquanlychamcongvsactivity.model.ChamCong;


public class DBChamCong {
    //     String sql = "CREATE TABLE CHAMCONG(MACC TEXT PRIMARY KEY, NGAYCC TEXT, MACN TEXT, " +
//            "CONSTRAINT FK_MACN_CHAMCONG FOREIGN KEY (MACN) REFERENCES CONGNHAN(MACN) )";
//    //    String sql = "CREATE TABLE CHAMCONG(MACC TEXT PRIMARY KEY, NGAYCC TEXT, MACN TEXT) ";
    Dbhelper dbhelper;
    Cursor cursor;

    public DBChamCong(Context context) {
        dbhelper = new Dbhelper(context);
    }

//    public DBChamCong(Context context) {
//
//        dbhelperCongNhan = new Dbhelper_CongNhan(context);
//        dbhelperCongNhan.setSql(sql);
//    }

    public void ThemDL(ChamCong chamCong) {

//        String MaCC,MaCN;
//        Date NgayCC;
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        ContentValues values = new ContentValues();


        values.put("MaCC", chamCong.getMaCC());
        values.put("NgayCC", chamCong.getNgayCC());
        values.put("MaCN", chamCong.getMaCN());

        db.insert("CHAMCONG", null, values);
    }

    public void Xoa(ChamCong chamCong) {
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        String sql = "Delete from CHAMCONG where MACC = '" + chamCong.getMaCC() + "'";
        db.execSQL(sql);
    }

    public void Sua(ChamCong chamCong) {
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("MaCC", chamCong.getMaCC());
        //(Date) new SimpleDateFormat("dd/MM/yyyy").parse(chamCong.getNgayCC())
        values.put("NgayCC", chamCong.getNgayCC());
        values.put("MaCN", chamCong.getMaCN());
        db.update("CHAMCONG", values, "MACC ='" + chamCong.getMaCC() + "'", null);
    }

    public ArrayList<ChamCong> getDuLieu() {
        ArrayList<ChamCong> data = new ArrayList<>();

        SQLiteDatabase db = dbhelper.getReadableDatabase();
        //String sql = "select * from CHAMCONG";
        //= db.rawQuery(sql, null);


        cursor = db.rawQuery("select * from CHAMCONG", null);


        if (cursor != null) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                ChamCong chamCong = new ChamCong();
                chamCong.setMaCC(cursor.getString(0));
                chamCong.setNgayCC(cursor.getString(1));
                chamCong.setMaCN(cursor.getString(2));


                data.add(chamCong);
                cursor.moveToNext();
            }
        }

        return data;


    }

}
