package tdc.edu.vn.projectquanlychamcongvsactivity.DBhepler;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class Dbhelper extends SQLiteOpenHelper {
    // KHAI BAO table
//    public static final String TABLE_CONGNHAN = "CONGNHAN";
//    public static final String TABLE_CHAMCONG = "CHAMCONG";
//    public static final String TABLE_CHITIETCHAMCONG = "CHITIETCHAMCONG";
//    public static final String TABLE_SANPHAM = "SANPHAM";
//    //KHAI BAO COLLUM IN TABLE_CONGNHAN
//    public static final String COLLUM_MACN = "MACN";
//    public static final String COLLUM_HOCN = "HOCN";
//    public static final String COLLUM_TENCN = "TENCN";
//    public static final String COLLUM_PHANXUONG = "PHANXUONG";
//    //KHAI BAO COLLUM IN TABLE_CHAMCONG
//    public static final String COLLUM_MACC = "MACC";
//    public static final String COLLUM_NGAYCC = "NGAYCC";
//    //KHAI BAO COLLUM IN TABLE_CHITIETCHAMCONG
//    public static final String COLLUM_MASP = "MASP";
//    public static final String COLLUM_SOTP = "SOTP";
//    public static final String COLLUM_SOPP = "SOPP";
//    //KHAI BAO COLLUM IN TABLE_SANPHAM
//    public static final String COLLUM_TENSP = "TENSP";
//    public static final String COLLUM_DONGGIA = "DONGGIA";

    //    public DBHelper(Context context) {
//        super(context, "QLCongNhan", null, 1);
//    }
//    @Override
//    public void onCreate(SQLiteDatabase db) {
//        String sql = "create table CongNhan( ngayChamCong text, soSanPham text , congNHan text, sanPham text )";
//        db.execSQL(sql);
//    }
//    @Override
//    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//
//    }


    public static final String sql = "CREATE TABLE CONGNHAN(MACN TEXT PRIMARY KEY, HOCN TEXT, TENCN TEXT, PHANXUONG TEXT)";
    public static final String sql_ChamCong = "CREATE TABLE CHAMCONG(MACC TEXT PRIMARY KEY, NGAYCC TEXT, MACN TEXT REFERENCES CONGNHAN(MACN)) ";
    public static final String sql_ChiTiet_ChamCong = "CREATE TABLE CHITIETCHAMCONG(MACC TEXT, MASP TEXT,SOTP INT,SOPP TEXT," +
            "CONSTRAINT FK_MACC_CHITIETCHAMCONG FOREIGN KEY(MACC) REFERENCES CHAMCONG(MACC), " +
            "CONSTRAINT FK_MASP_CHITIETCHAMCONG FOREIGN KEY (MASP) REFERENCES SANPHAM(MASP) )";
    public static  final String sql_SanPham = "CREATE TABLE SANPHAM(MASP TEXT PRIMARY KEY, TENSP TEXT, DONGIA TEXT)";

    public Dbhelper(@Nullable Context context) {
        super(context, "quanLyChamCong", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {


        db.execSQL(sql);
        db.execSQL(sql_ChamCong);
        db.execSQL(sql_ChiTiet_ChamCong);
        db.execSQL(sql_SanPham);


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + "CONGNHAN");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + "CHAMCONG");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + "CHITIETCHAMCONG");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + "SANPHAM");
        onCreate(sqLiteDatabase);
    }
}
