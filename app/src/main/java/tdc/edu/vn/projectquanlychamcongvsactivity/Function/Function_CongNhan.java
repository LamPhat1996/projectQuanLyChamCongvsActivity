package tdc.edu.vn.projectquanlychamcongvsactivity.Function;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import tdc.edu.vn.projectquanlychamcongvsactivity.CustomView.Custom_listview_Congnhan;
import tdc.edu.vn.projectquanlychamcongvsactivity.Database.DBCongNhan;
import tdc.edu.vn.projectquanlychamcongvsactivity.R;
import tdc.edu.vn.projectquanlychamcongvsactivity.model.CongNhan;


public class Function_CongNhan extends AppCompatActivity {
    ListView lvdscongnhan;

    ArrayList<CongNhan> datacongNhans = new ArrayList<>();
    int index = -1;
    Button btnThem, btnXoa, btnSua, btnClear;
    EditText edtMaCN, edtHoCN, edtTenCN, edtPhanXuong;
    Custom_listview_Congnhan adapter_congnhan;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_gallery_congnhan);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //get actionbae ham tim kiem
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        //

        setControl();
        setEvent();


    }

    //back về trang home
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
public boolean kiemtratruongdulieu(){
        if(!edtMaCN.getText().toString().isEmpty() && !edtHoCN.getText().toString().isEmpty() &&
                !edtTenCN.getText().toString().isEmpty() && !edtPhanXuong.getText().toString().isEmpty()){
            Toast.makeText(getApplicationContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
            return true;
        }
        else {
            Toast.makeText(getApplicationContext(), "Thêm không thành công \n Kiểm tra dữ liệu nhập", Toast.LENGTH_SHORT).show();
            return false;
        }

}
    private void setEvent() {
        hienThiDL();
        //chức năng thêm
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                CongNhan congNhan = getlaydl();
                DBCongNhan dbCongNhan = new DBCongNhan(getApplicationContext());
                if(kiemtratruongdulieu() == true)
                {
                    dbCongNhan.ThemDL(congNhan);
//                adapter_congnhan.notifyDataSetChanged();
                    //Toast.makeText(Function_CongNhan.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                    lvdscongnhan.deferNotifyDataSetChanged();
                    hienThiDL();

                }

            }
        });
        //chức năng xóa
        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(Function_CongNhan.this);
                builder.setMessage("Bạn có muốn xóa \n Nếu xóa sẽ ảnh hưởng đến các bảng,ChiTiec,SanPham")
                        .setTitle("Thông báo nặng !!");
                builder.setCancelable(true);
                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        datacongNhans.remove(index);
                        DBCongNhan dbCongNhan = new DBCongNhan(getApplicationContext());
                        CongNhan congNhan = getlaydl();
                        dbCongNhan.Xoa(congNhan);
                        Toast.makeText(Function_CongNhan.this, "Xóa thành công", Toast.LENGTH_SHORT).show();
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
            }
        });
        //chuc nang sua
        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBCongNhan dbCongNhan = new DBCongNhan(getApplicationContext());
                CongNhan congNhan = getlaydl();
                dbCongNhan.Sua(congNhan);
                Toast.makeText(Function_CongNhan.this, "Sửa thành công", Toast.LENGTH_SHORT).show();
                hienThiDL();
            }
        });
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtMaCN.setText("");
                edtHoCN.setText("");
                edtTenCN.setText("");
                edtPhanXuong.setText("");


            }
        });

        //hien thi du lieu nguoc ve
        lvdscongnhan.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                index = i;
                CongNhan congNhan = datacongNhans.get(i);
                edtMaCN.setText(congNhan.getMaCN());
                edtHoCN.setText(congNhan.getHoCN());
                edtTenCN.setText(congNhan.getTenCN());
                edtPhanXuong.setText(congNhan.getPhanXuong());
            }
        });
    }

    private void setControl() {
        btnThem = findViewById(R.id.btnThem_congnhan);
        btnXoa = findViewById(R.id.btnXoa_congnhan);
        btnSua = findViewById(R.id.btnSua_congnhan);
        btnClear = findViewById(R.id.btnClear_congnhan);
        lvdscongnhan = findViewById(R.id.lvdsCongNhan);
        edtMaCN = findViewById(R.id.tvMaCN);
        edtHoCN = findViewById(R.id.tvHoCN);
        edtTenCN = findViewById(R.id.tvTenCN);
        edtPhanXuong = findViewById(R.id.tvPhanXuong);
    }

    private void hienThiDL() {
        DBCongNhan dbCongNhan = new DBCongNhan(getApplicationContext());
        datacongNhans = dbCongNhan.getDuLieu();
        adapter_congnhan = new Custom_listview_Congnhan(this, R.layout.item_listview_congnhan, datacongNhans);
        lvdscongnhan.setAdapter(adapter_congnhan);
    }

    private CongNhan getlaydl() {
        CongNhan congNhan = new CongNhan();
        congNhan.setMaCN(edtMaCN.getText().toString());
        congNhan.setHoCN(edtHoCN.getText().toString());
        congNhan.setTenCN(edtTenCN.getText().toString());
        congNhan.setPhanXuong(edtPhanXuong.getText().toString());
        return congNhan;
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
                    adapter_congnhan.filter("");
                    lvdscongnhan.clearTextFilter();
                } else {
                    adapter_congnhan.filter(s);
                }
                return true;
            }
        });

        return true;
    }
}
