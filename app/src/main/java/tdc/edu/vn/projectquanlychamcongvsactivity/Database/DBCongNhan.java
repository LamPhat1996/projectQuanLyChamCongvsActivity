package tdc.edu.vn.projectquanlychamcongvsactivity.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import tdc.edu.vn.projectquanlychamcongvsactivity.DBhepler.Dbhelper;
import tdc.edu.vn.projectquanlychamcongvsactivity.model.CongNhan;


public class DBCongNhan {
    //    String sql = "CREATE TABLE CONGNHAN (MACN TEXT PRIMARY KEY, HOCN TEXT, TENCN TEXT, PHANXUONG TEXT)";
    Dbhelper dbhelperCongNhan;
    public DBCongNhan(Context context) {
        dbhelperCongNhan = new Dbhelper(context);
    }

    public void ThemDL(CongNhan congNhan) {
        //    String MaCN,HoCN,TenCN,PhanXuong;
        SQLiteDatabase db = dbhelperCongNhan.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("MaCN", congNhan.getMaCN());
        values.put("HoCN", congNhan.getHoCN());
        values.put("TenCN", congNhan.getTenCN());
        values.put("PhanXuong", congNhan.getPhanXuong());
        db.insert("CONGNHAN", null, values);
    }

    public void Xoa(CongNhan congNhan) {
        SQLiteDatabase db = dbhelperCongNhan.getWritableDatabase();
        String sql = "Delete from CONGNHAN where MaCN = '" + congNhan.getMaCN() + "'";
        db.execSQL(sql);
    }

//    public void Xoa(CongNhan congNhan) {
//        String maCN = " + congNhan.getMaCN() + ";
//        SQLiteDatabase db = dbhelperCongNhan.getWritableDatabase();
//        db.delete("CONGNHAN", "maCN=?", new String[]{maCN});
//    }

    public void Sua(CongNhan congNhan) {
        SQLiteDatabase db = dbhelperCongNhan.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("MaCN", congNhan.getMaCN());
        values.put("HoCN", congNhan.getHoCN());
        values.put("TenCN", congNhan.getTenCN());
        values.put("PhanXuong", congNhan.getPhanXuong());
        db.update("CONGNHAN", values, "MACN ='" + congNhan.getMaCN() + "'", null);
    }

    public ArrayList<CongNhan> getDuLieu() {
        ArrayList<CongNhan> data = new ArrayList<>();

        SQLiteDatabase db = dbhelperCongNhan.getReadableDatabase();
        String sql = "select * from CONGNHAN";
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            CongNhan congnhan = new CongNhan();
            congnhan.setMaCN(cursor.getString(0));
            congnhan.setHoCN(cursor.getString(1));
            congnhan.setTenCN(cursor.getString(2));
            congnhan.setPhanXuong(cursor.getString(3));
            data.add(congnhan);
            cursor.moveToNext();
        }
        return data;

    }
//    public ArrayList<CongNhan> getDuLieu() {
//        ArrayList<CongNhan> data = new ArrayList<>();
//        String sql = "select * from CONGNHAN";
//        SQLiteDatabase db = dbhelper.getReadableDatabase();
//        Cursor cursor = db.rawQuery(sql, null);
//        cursor.moveToFirst();
//        do {
//            CongNhan congnhan = new CongNhan();
//            congnhan.setMaCN(cursor.getString(0));
//            congnhan.setHoCN(cursor.getString(1));
//            congnhan.setTenCN(cursor.getString(2));
//            congnhan.setPhanXuong(cursor.getString(3));
//            data.add(congnhan);
//        }
//        while (cursor.moveToNext());
//        return data;
//    }

}
