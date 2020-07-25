package tdc.edu.vn.projectquanlychamcongvsactivity.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;


import tdc.edu.vn.projectquanlychamcongvsactivity.DBhepler.Dbhelper;
import tdc.edu.vn.projectquanlychamcongvsactivity.model.SanPham;


public class DBSanPham {
    //    String sql = "CREATE TABLE SANPHAM(MASP TEXT PRIMARY KEY, TENSP TEXT, DONGGIA TEXT)"
    Dbhelper dbhelper_SanPham;
    Cursor cursor;

    public DBSanPham(Context context) {
        dbhelper_SanPham = new Dbhelper(context);
    }

    public void ThemDL(SanPham sanPham) {
        //String MaSP,TenSP,DonGia;
        SQLiteDatabase db = dbhelper_SanPham.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("MaSP",sanPham.getMaSP());
        values.put("TenSP",sanPham.getTenSP());
        values.put("DonGia",sanPham.getDonGia());
        db.insert("SANPHAM",null,values);
    }


    public void Xoa(SanPham sanPham) {
        SQLiteDatabase db = dbhelper_SanPham.getWritableDatabase();
        String sql = "Delete from SANPHAM where MASP = '" + sanPham.getMaSP() + "'";
        db.execSQL(sql);
    }

    public void Sua(SanPham sanPham) {
         //String MaSP,TenSP,DonGia;
        SQLiteDatabase db = dbhelper_SanPham.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("MaSP", sanPham.getMaSP());
        values.put("TenSP", sanPham.getTenSP());
        values.put("DonGia", sanPham.getDonGia());
        db.update("SANPHAM", values, "MASP ='" + sanPham.getMaSP() + "'", null);
    }

    public ArrayList<SanPham> getDulieu() {
        ArrayList<SanPham> data = new ArrayList<>();
        SQLiteDatabase db = dbhelper_SanPham.getReadableDatabase();

        cursor = db.rawQuery("select * from SANPHAM", null);

        if (cursor != null) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                SanPham sanPham = new SanPham();
                sanPham.setMaSP(cursor.getString(0));
                sanPham.setTenSP(cursor.getString(1));
                sanPham.setDonGia(cursor.getString(2));
                data.add(sanPham);
                cursor.moveToNext();
            }
        }
        return data;
    }
    //
    //


//    public ArrayList<SanPham> getDuLieu() {
//        ArrayList<SanPham> data = new ArrayList<>();
//        String sql = "select * from SANPHAM";
//        SQLiteDatabase db = dbhelperSanPham.getReadableDatabase();
//        Cursor cursor = db.rawQuery(sql, null);
//        cursor.moveToFirst();
//        do {
//            SanPham sanPham = new SanPham();
//            sanPham.setMaSanPham(cursor.getString(0));
//            sanPham.setTenSanPham(cursor.getString(1));
//            sanPham.setDonGia(cursor.getInt(2));
//            data.add(sanPham);
//        }
//        while (cursor.moveToNext());
//        return data;
//    }


}
