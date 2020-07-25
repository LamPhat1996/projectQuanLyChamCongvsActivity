package tdc.edu.vn.projectquanlychamcongvsactivity.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import tdc.edu.vn.projectquanlychamcongvsactivity.DBhepler.Dbhelper;
import tdc.edu.vn.projectquanlychamcongvsactivity.model.ChiTietChamCong;


public class DBChiTietChamCong {
    Dbhelper dbhelper_ChiTiet_ChamCong;
    Cursor cursor;

    public DBChiTietChamCong(Context context) {
        dbhelper_ChiTiet_ChamCong = new Dbhelper(context);
    }
    public void ThemDL_ChiTiet_ChamCong(ChiTietChamCong chiTietChamCong)
    {
        //String MaCC,MaSP,SoTP,SoPP;
        SQLiteDatabase db = dbhelper_ChiTiet_ChamCong.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("MaCC",chiTietChamCong.getMaCC());
        values.put("MaSP",chiTietChamCong.getMaSP());
        values.put("SoTP",chiTietChamCong.getSoTP());
        values.put("SoPP",chiTietChamCong.getSoPP());
        db.insert("CHITIETCHAMCONG",null,values);
    }
    public void Xoa(ChiTietChamCong chiTietChamCong) {
        SQLiteDatabase db = dbhelper_ChiTiet_ChamCong.getWritableDatabase();
        String sql = "Delete from CHITIETCHAMCONG where MACC = '" + chiTietChamCong.getMaCC() + "'";
        db.execSQL(sql);
    }

    public void Sua(ChiTietChamCong chiTietChamCong) {
        //String MaCC,MaSP,SoTP,SoPP;
        SQLiteDatabase db = dbhelper_ChiTiet_ChamCong.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("MaCC", chiTietChamCong.getMaCC());
        values.put("MaSP", chiTietChamCong.getMaSP());
        values.put("SoTP", chiTietChamCong.getSoTP());
        values.put("SoPP", chiTietChamCong.getSoPP());
        db.update("CHITIETCHAMCONG", values, "MACC = '" + chiTietChamCong.getMaCC() + "' AND MASP = '" + chiTietChamCong.getMaSP() + "'" , null);
    }
public ArrayList<ChiTietChamCong> getDuLieu(){
        ArrayList<ChiTietChamCong> data = new ArrayList<>();

        SQLiteDatabase db = dbhelper_ChiTiet_ChamCong.getReadableDatabase();
        cursor = db.rawQuery("select * from CHITIETCHAMCONG", null);
        if(cursor != null)
        {
            cursor.moveToFirst();
            while (!cursor.isAfterLast())
            {
                ChiTietChamCong chiTietChamCong = new ChiTietChamCong();
                chiTietChamCong.setMaCC(cursor.getString(0));
                chiTietChamCong.setMaSP(cursor.getString(1));
                chiTietChamCong.setSoTP(cursor.getString(2));
                chiTietChamCong.setSoPP(cursor.getString(3));
                data.add(chiTietChamCong);
                cursor.moveToNext();
            }
        }
        return data;
}

//
//String sql ="CREATE TABLE CHITIETCHAMCONG(MACC TEXT, MASP TEXT,SOTP INT,SOPP TEXT," +
//        "CONSTRAINT FK_MACC_CHITIETCHAMCONG FOREIGN KEY(MACC) REFERENCES CHAMCONG(MACC), " +
//        "CONSTRAINT FK_MASP_CHITIETCHAMCONG FOREIGN KEY (MASP) REFERENCES SANPHAM(MASP) )";
//    Dbhelper dbhelperCongNhan;
////    public DBChiTietChamCong(Context context) {
////        dbhelperCongNhan.setSql(sql);
////        dbhelperCongNhan = new Dbhelper_CongNhan(context);
////
////    }

}
