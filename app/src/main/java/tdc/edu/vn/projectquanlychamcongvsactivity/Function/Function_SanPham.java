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

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import tdc.edu.vn.projectquanlychamcongvsactivity.CustomView.Custom_ListView_sanpham;
import tdc.edu.vn.projectquanlychamcongvsactivity.Database.DBSanPham;
import tdc.edu.vn.projectquanlychamcongvsactivity.R;
import tdc.edu.vn.projectquanlychamcongvsactivity.model.ChiTietChamCong;
import tdc.edu.vn.projectquanlychamcongvsactivity.model.SanPham;


public class Function_SanPham extends AppCompatActivity {
    ListView lvdsSanPham;
    Custom_ListView_sanpham customlv_sanpham;
    int index = -1;
    Button btnThem, btnXoa, btnSua, btnclear;
    EditText edtMasp, edtTenSP, edtDonGia;

    Spinner spinnermsp;
    ArrayList<String> data_spn_SanPham = new ArrayList<>();
    ArrayList<SanPham> sanPhams = new ArrayList<>();
    ArrayList<ChiTietChamCong> chiTietChamCongs = new ArrayList<>();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_sanpham);

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
        if(!edtMasp.getText().toString().isEmpty() && !edtTenSP.getText().toString().isEmpty() &&
                !edtDonGia.getText().toString().isEmpty()){
            Toast.makeText(getApplicationContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
            return true;
        }
        else {
            Toast.makeText(getApplicationContext(), "Thêm không thành công \n Kiểm tra dữ liệu nhập ", Toast.LENGTH_SHORT).show();
            return false;
        }

    }

    private void setEvent() {
        hienThiDL();
//        ShowSpiner();
        ArrayAdapter arrayAdapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_spinner_item, data_spn_SanPham);
        //lay du lieu cho spirner
//        spinnermsp.setAdapter(arrayAdapter);
        //chuc nang theem
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(kiemtratruongdulieu() ==  true)
                {
                    DBSanPham dbSanPham = new DBSanPham(getApplicationContext());
                    SanPham sanPham = layDL();
                    dbSanPham.ThemDL(sanPham);
                    Toast.makeText(Function_SanPham.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                    hienThiDL();
                }

            }
        });

        btnclear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edtMasp.setText("");
                edtTenSP.setText("");
                edtDonGia.setText("");
                Toast.makeText(Function_SanPham.this, "Clear", Toast.LENGTH_SHORT).show();
            }
        });

        //hien thi du lieu nguoc ve
        lvdsSanPham.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                index = i;
                SanPham sanPham = sanPhams.get(i);
                edtMasp.setText(sanPham.getMaSP());
                edtTenSP.setText(sanPham.getTenSP());
                edtDonGia.setText(sanPham.getDonGia());
            }
        });
        //
        //chức năng xóa
        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(Function_SanPham.this);
                builder.setMessage("Bạn có muốn xóa \n Nếu xóa sẽ ảnh hưởng đến các bảng,CongNhan,ChiTietChamCong,ChamCong")
                        .setTitle("Thông báo nặng !!");
                builder.setCancelable(true);
                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        sanPhams.remove(index);
                        DBSanPham dbSanPham = new DBSanPham(getApplicationContext());
                        SanPham sanPham = layDL();
                        dbSanPham.Xoa(sanPham);
                        Toast.makeText(Function_SanPham.this, "Xóa thành công", Toast.LENGTH_SHORT).show();
                        hienThiDL();

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
//                sanPhams.remove(index);
//                DBSanPham dbSanPham = new DBSanPham(getApplicationContext());
//                SanPham sanPham = layDL();
//                dbSanPham.Xoa(sanPham);
//                Toast.makeText(Function_SanPham.this, "Xóa thành công", Toast.LENGTH_SHORT).show();
//                hienThiDL();
            }
        });
        //chuc nang sua
        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBSanPham dbSanPham = new DBSanPham(getApplicationContext());
                SanPham sanPham = layDL();
                dbSanPham.Sua(sanPham);
                Toast.makeText(Function_SanPham.this, "Sửa thành công", Toast.LENGTH_SHORT).show();
                hienThiDL();
            }
        });
        //
    }

    private void setControl() {
        btnThem = findViewById(R.id.btnThem_sp);
        btnXoa = findViewById(R.id.btnxoa_sp);
        btnSua = findViewById(R.id.btnsua_sp);
        btnclear = findViewById(R.id.btnClear_sp);

       edtMasp = findViewById(R.id.tv_MaSanPham);
        edtTenSP = findViewById(R.id.tv_TenSanPham);
        edtDonGia = findViewById(R.id.tv_DonGia);

        lvdsSanPham = findViewById(R.id.lvds_SanPham);
    }

    //back
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
    private void hienThiDL() {
        DBSanPham dbSanPham = new DBSanPham(getApplicationContext());
        sanPhams = dbSanPham.getDulieu();
        customlv_sanpham = new Custom_ListView_sanpham(this.getApplicationContext(), R.layout.item_listview_sanpham, sanPhams);
        lvdsSanPham.setAdapter(customlv_sanpham);
    }
    private SanPham layDL() {

        SanPham sanPham = new SanPham();

        sanPham.setMaSP(edtMasp.getText().toString());
        sanPham.setTenSP(edtTenSP.getText().toString());
        sanPham.setDonGia(edtDonGia.getText().toString());
        return sanPham;
    }
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
                    customlv_sanpham.filter("");
                    lvdsSanPham.clearTextFilter();
                } else {
                    customlv_sanpham.filter(s);
                }
                return true;
            }
        });

        return true;
    }



}
