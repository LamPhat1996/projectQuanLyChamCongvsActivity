package tdc.edu.vn.projectquanlychamcongvsactivity.Function;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import tdc.edu.vn.projectquanlychamcongvsactivity.CustomView.Custom_Listview_ChiTiet_ChamCong;
import tdc.edu.vn.projectquanlychamcongvsactivity.Database.DBChamCong;
import tdc.edu.vn.projectquanlychamcongvsactivity.Database.DBChiTietChamCong;
import tdc.edu.vn.projectquanlychamcongvsactivity.Database.DBSanPham;
import tdc.edu.vn.projectquanlychamcongvsactivity.R;
import tdc.edu.vn.projectquanlychamcongvsactivity.model.ChamCong;
import tdc.edu.vn.projectquanlychamcongvsactivity.model.ChiTietChamCong;
import tdc.edu.vn.projectquanlychamcongvsactivity.model.SanPham;


public class Function_ChiTietChamCong extends AppCompatActivity {


    ListView lvds_chitiet_chamcong;
    Custom_Listview_ChiTiet_ChamCong customlv_adapterCTCC;
    int index = -1;
    Button btnThem, btnXoa, btnSua, btnClear;
    EditText edtMaSanPham, edtSoThanhPham, edtSoPhePham;
    //lay thong tin cho spirner ma cham cong
    Spinner spinnerMaChamCong;

    ArrayList<String> data_spr_chamcong = new ArrayList<>();
    ArrayList<ChiTietChamCong> chiTietChamCongs = new ArrayList<>();
    ArrayList<ChamCong> chamCongs = new ArrayList<>();


    //lay spiner cho ma san pham
    Spinner spinnerMaSanPhaM;
    ArrayList<String> data_spr_SanPham = new ArrayList<>();
    ArrayList<SanPham> sanPhams = new ArrayList<>();





    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fagment_gallery_chitiet_chamcong);
        //btn back
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        //
        setControl();
        setEvent();
    }
    public boolean kiemtratruongdulieu(){
        if(!edtSoThanhPham.getText().toString().isEmpty() && !edtSoPhePham.getText().toString().isEmpty() ){
            Toast.makeText(getApplicationContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
            return true;
        }
        else {
            Toast.makeText(getApplicationContext(), "Thêm không thành công \n Kiểm tra trường dữ liệu nhập", Toast.LENGTH_SHORT).show();
            return false;
        }

    }
    private void setEvent() {
        hienthiDL();
        ShowSpiner();


        //chức năng Thêm
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(kiemtratruongdulieu() == true)
                {
                    DBChiTietChamCong dbChiTietChamCong1 = new DBChiTietChamCong(getApplicationContext());
                    ChiTietChamCong chiTietChamCong = layDL();
                    dbChiTietChamCong1.ThemDL_ChiTiet_ChamCong(chiTietChamCong);
                    Toast.makeText(Function_ChiTietChamCong.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                    hienthiDL();
                }


            }
        });
        //chức năng xóa
        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Function_ChiTietChamCong.this);
                builder.setMessage("Bạn có muốn xóa \n Nếu xóa ảnh hưởng đến Cong Nhan,Cham Cong,SanPham")
                        .setTitle("Thong báo nặng!!");
                builder.setCancelable(true);
                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        chiTietChamCongs.remove(index);
                        DBChiTietChamCong dbChiTietChamCong = new DBChiTietChamCong(getApplicationContext());
                        ChiTietChamCong chiTietChamCong = layDL();
                        dbChiTietChamCong.Xoa(chiTietChamCong);
                        Toast.makeText(Function_ChiTietChamCong.this, "Xóa thành công", Toast.LENGTH_SHORT).show();
                        hienthiDL();

                    }
                });
                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });

                AlertDialog alertDialog = builder.create();

                alertDialog.show();
//                chiTietChamCongs.remove(index);
//                DBChiTietChamCong dbChiTietChamCong = new DBChiTietChamCong(getApplicationContext());
//                ChiTietChamCong chiTietChamCong = layDL();
//                dbChiTietChamCong.Xoa(chiTietChamCong);
//                Toast.makeText(Function_ChiTietChamCong.this, "Xóa thành công", Toast.LENGTH_SHORT).show();
//                hienthiDL();
            }
        });
        //chuc nang sua
        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBChiTietChamCong dbChiTietChamCong = new DBChiTietChamCong(getApplicationContext());
                ChiTietChamCong chiTietChamCong = layDL();
                dbChiTietChamCong.Sua(chiTietChamCong);
                Toast.makeText(Function_ChiTietChamCong.this, "Sửa thành công", Toast.LENGTH_SHORT).show();
                hienthiDL();
            }
        });
        //
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spinnerMaChamCong.setSelection(0);
                spinnerMaSanPhaM.setSelection(0);
                edtSoThanhPham.setText("");
                edtSoPhePham.setText("");
                Toast.makeText(Function_ChiTietChamCong.this, "Clear", Toast.LENGTH_SHORT).show();


            }
        });
        //hien thi du lieu nguoc ve
        lvds_chitiet_chamcong.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                index = i;
                ChiTietChamCong chiTietChamCong = chiTietChamCongs.get(i);


                int posion1 = 0;
                for (String ten1 : data_spr_chamcong) {
                    if (ten1.equals(chiTietChamCong.getMaCC())) {
                        spinnerMaChamCong.setSelection(posion1);
                        posion1 = 0;
                        break;
                    }

                    posion1++;
                }
                int posion2 = 0;
                for (String ten2 : data_spr_SanPham) {
                    if (ten2.equals(chiTietChamCong.getMaSP())) {
                        spinnerMaSanPhaM.setSelection(posion2);
                        posion2 = 0;
                        break;
                    }

                    posion2++;
                }
                edtSoThanhPham.setText(chiTietChamCong.getSoTP());
                edtSoPhePham.setText(chiTietChamCong.getSoPP());


            }
        });

    }

    private void setControl() {
        spinnerMaChamCong = findViewById(R.id.spnMaChamCong);
        spinnerMaSanPhaM = findViewById(R.id.spnMaSP);
        edtSoThanhPham = findViewById(R.id.tvSoThanhPham);
        edtSoPhePham = findViewById(R.id.tvSoPhePham);

        btnThem = findViewById(R.id.btnThem_chitietChamCong);
        btnXoa = findViewById(R.id.btnXoa_chitietchamcong);
        btnSua = findViewById(R.id.btnSua_chitietchamcong);
        btnClear = findViewById(R.id.btnClear_chiTiet_ChamCong);
        lvds_chitiet_chamcong = findViewById(R.id.lvds_chitietchamcong);

    }

    private void hienthiDL() {
        DBChiTietChamCong dbChiTietChamCong = new DBChiTietChamCong(getApplicationContext());

        chiTietChamCongs = dbChiTietChamCong.getDuLieu();

        customlv_adapterCTCC = new Custom_Listview_ChiTiet_ChamCong(this.getApplicationContext(), R.layout.item_listview_chitiet_chamcong, chiTietChamCongs);
        lvds_chitiet_chamcong.setAdapter(customlv_adapterCTCC);


    }

    //ham lay du lieu
    private ChiTietChamCong layDL() {

        ChiTietChamCong chiTietChamCong = new ChiTietChamCong();

        chiTietChamCong.setMaCC(spinnerMaChamCong.getSelectedItem().toString());
        chiTietChamCong.setMaSP(spinnerMaSanPhaM.getSelectedItem().toString());
        chiTietChamCong.setSoTP(edtSoThanhPham.getText().toString());
        chiTietChamCong.setSoPP(edtSoPhePham.getText().toString());

        return chiTietChamCong;

    }

    private void ShowSpiner() {
        DBChamCong dbChamCong = new DBChamCong(getApplicationContext());
        chamCongs = dbChamCong.getDuLieu();
        for (ChamCong chamCong : chamCongs) {
            data_spr_chamcong.add(chamCong.getMaCN());
        }
        ArrayAdapter arrayAdapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_spinner_item, data_spr_chamcong);
        spinnerMaChamCong.setAdapter(arrayAdapter);

        DBSanPham dbSanPham = new DBSanPham(getApplicationContext());
        sanPhams = dbSanPham.getDulieu();
        for (SanPham sanPham : sanPhams) {
            data_spr_SanPham.add(sanPham.getMaSP());
        }
        ArrayAdapter arrayAdapter1 = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_spinner_item, data_spr_SanPham);
        spinnerMaSanPhaM.setAdapter(arrayAdapter1);
    }


    //back
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
    //ham tim kiem
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actionsearch, menu);

        MenuItem myActionMenuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) myActionMenuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                if (TextUtils.isEmpty(s)) {
                    customlv_adapterCTCC.filter("");
                    lvds_chitiet_chamcong.clearTextFilter();
                } else {
                    customlv_adapterCTCC.filter(s);
                }
                return true;
            }
        });

        return true;
    }

}
