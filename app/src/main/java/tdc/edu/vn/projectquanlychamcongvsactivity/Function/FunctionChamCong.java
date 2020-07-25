package tdc.edu.vn.projectquanlychamcongvsactivity.Function;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import tdc.edu.vn.projectquanlychamcongvsactivity.CustomView.Custom_Listview_ChamCong;
import tdc.edu.vn.projectquanlychamcongvsactivity.Database.DBChamCong;
import tdc.edu.vn.projectquanlychamcongvsactivity.Database.DBCongNhan;
import tdc.edu.vn.projectquanlychamcongvsactivity.R;
import tdc.edu.vn.projectquanlychamcongvsactivity.model.ChamCong;
import tdc.edu.vn.projectquanlychamcongvsactivity.model.CongNhan;


public class FunctionChamCong extends AppCompatActivity {
    ListView lvdsChamCong1;
    Custom_Listview_ChamCong customlv_adaptercc;
    int index = -1;
    Button btnThem, btnXoa, btnSua, btnClear;

    EditText edtMaChamCong;


    //phan lay thong tin cua spriner va su dung motj vai thong tin cho viec luu database
    Spinner spinnercc;
    ArrayList<String> data_spr = new ArrayList<>();
    ArrayList<ChamCong> chamCongs = new ArrayList<>();
    ArrayList<CongNhan> congNhans = new ArrayList<>();
    private TextView txtdate;
    private Button btnDate;
    private DatePickerDialog.OnDateSetListener onDateSetListener;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_slideshow_chamcong);
        //

        //

        //btn back
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        setControl();
        setEvent();
    }

    //back
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
    public boolean kiemtratruongdulieu(){
        if(!edtMaChamCong.getText().toString().isEmpty()){
            Toast.makeText(getApplicationContext(), "Them thanh cong", Toast.LENGTH_SHORT).show();
            return true;
        }
        else {
            Toast.makeText(getApplicationContext(), "Them khong thanh cong \n Kiem tra du lieu nhap", Toast.LENGTH_SHORT).show();
            return false;
        }

    }

    private void setEvent() {
        hienthiDL();

        ShowSpiner();
        ArrayAdapter arrayAdapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_spinner_item, data_spr);

        //Lay du lieu ma cong nhan tu bang cong nhan sang lam mot spriner cho bang cham cong
        spinnercc.setAdapter(arrayAdapter);
        btnDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog pickerDialog = new DatePickerDialog(view.getContext()
                        , android.R.style.Theme_Holo_Light_Dialog_MinWidth
                        , onDateSetListener, year, month, day);
                pickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                pickerDialog.show();

            }
        });
        onDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                String date = day + "/" + month + "/" + year;
                txtdate.setText(date);
            }
        };

        //chức năng Thêm
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(kiemtratruongdulieu() == true)
                {
                    DBChamCong dbChamCong = new DBChamCong(getApplicationContext());
                    ChamCong chamCong = layDL();
                    dbChamCong.ThemDL(chamCong);
                    Toast.makeText(FunctionChamCong.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                    hienthiDL();
                }


            }
        });
        //chức năng xóa
        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(FunctionChamCong.this);
                builder.setMessage("Bạn có muốn xóa \n Nếu xóa sẽ ảnh hưởng đến các bảng,SanPham,CongNhan,ChiTietChamCong")
                        .setTitle("Thông báo nặng !!");
                builder.setCancelable(true);
                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        chamCongs.remove(index);
                        DBChamCong dbChamCong = new DBChamCong(getApplicationContext());
                        ChamCong chamCong = layDL();
                        dbChamCong.Xoa(chamCong);
                        Toast.makeText(FunctionChamCong.this, "Xóa thành công", Toast.LENGTH_SHORT).show();
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
//                chamCongs.remove(index);
//                DBChamCong dbChamCong = new DBChamCong(getApplicationContext());
//                ChamCong chamCong = layDL();
//                dbChamCong.Xoa(chamCong);
//                Toast.makeText(FunctionChamCong.this, "Xóa thành công", Toast.LENGTH_SHORT).show();
//                hienthiDL();
            }
        });
        //
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edtMaChamCong.setText("");
                txtdate.setText("");
            }
        });
        //
        //chuc nang sua
        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBChamCong dbChamCong = new DBChamCong(getApplicationContext());
                ChamCong chamCong = layDL();
                dbChamCong.Sua(chamCong);
                Toast.makeText(FunctionChamCong.this, "Sửa thành công", Toast.LENGTH_SHORT).show();
                hienthiDL();
            }
        });
        //hien thi du lieu nguoc ve
        lvdsChamCong1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                index = i;
                ChamCong chamCong = chamCongs.get(i);
                edtMaChamCong.setText(chamCong.getMaCC());
                txtdate.setText(chamCong.getNgayCC());

                int posion = 0;
                for (String ten : data_spr) {
                    if (ten.equals(chamCong.getMaCN())) {
                        spinnercc.setSelection(posion);
                        posion = 0;
                        break;
                    }

                    posion++;
                }
            }
        });

    }

    private void setControl() {
        edtMaChamCong = findViewById(R.id.edt_Macc);
        txtdate = findViewById(R.id.tv_date);
        spinnercc = findViewById(R.id.spnMaCongNhan);


        btnDate = findViewById(R.id.btndate);


        //

        btnThem = findViewById(R.id.btnThem_chamcong);
        btnXoa = findViewById(R.id.btnXoa_chamcong);
        btnSua = findViewById(R.id.btnSua_chamcong);
        btnClear = findViewById(R.id.btnClear_chamcong);

        lvdsChamCong1 = findViewById(R.id.lvdsChamCong);


    }

    private void hienthiDL() {
        DBChamCong dbChamCong = new DBChamCong(getApplicationContext());

        chamCongs = dbChamCong.getDuLieu();

        customlv_adaptercc = new Custom_Listview_ChamCong(this.getApplicationContext(), R.layout.item_listview_chamcong, chamCongs);
        lvdsChamCong1.setAdapter(customlv_adaptercc);
    }

    //ham lay du lieu
    private ChamCong layDL() {

        ChamCong chamCong = new ChamCong();

        chamCong.setMaCC(edtMaChamCong.getText().toString());
        chamCong.setNgayCC(txtdate.getText().toString());
        chamCong.setMaCN(spinnercc.getSelectedItem().toString());

        return chamCong;

    }

    //
    private void ShowSpiner() {
        DBCongNhan dbCongNhan = new DBCongNhan(getApplicationContext());
        congNhans = dbCongNhan.getDuLieu();
        for (CongNhan congNhan : congNhans) {
            data_spr.add(congNhan.getMaCN());
        }
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
                    customlv_adaptercc.filter("");
                    lvdsChamCong1.clearTextFilter();
                } else {
                    customlv_adaptercc.filter(s);
                }
                return true;
            }
        });

        return true;
    }
}

//public class FunctionChamCong extends Fragment {
//
//    ListView lvdsChamCong;
//    Custom_Listview_ChamCong customlv_adaptercc;
//    int index = -1;
//    Button btnThem, btnXoa, btnSua, btnClear;
//
//    EditText edtMaChamCong;
//
//
//    //phan lay thong tin cua spriner va su dung motj vai thong tin cho viec luu database
//    Spinner spinnercc;
//    ArrayList<String> data_spr = new ArrayList<>();
//    ArrayList<ChamCong> chamCongs = new ArrayList<>();
//    ArrayList<CongNhan> congNhans = new ArrayList<>();
//    private TextView txtdate;
//    private Button btnDate;
//    private DatePickerDialog.OnDateSetListener onDateSetListener;
//
//    //
//    public View onCreateView(@NonNull LayoutInflater inflater,
//                             ViewGroup container, Bundle savedInstanceState) {
//        View root = inflater.inflate(R.layout.fragment_slideshow_chamcong, container, false);
//        setControl(root);
//        setEvent(root);
//
//        return root;
//    }
//
//    private void setEvent(View view) {
//        ShowSpiner();
//        ArrayAdapter arrayAdapter = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, data_spr);
//
//        //Lay du lieu ma cong nhan tu bang cong nhan sang lam mot spriner cho bang cham cong
//        spinnercc.setAdapter(arrayAdapter);
//        btnDate.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Calendar calendar = Calendar.getInstance();
//                int year = calendar.get(Calendar.YEAR);
//                int month = calendar.get(Calendar.MONTH);
//                int day = calendar.get(Calendar.DAY_OF_MONTH);
//                DatePickerDialog pickerDialog = new DatePickerDialog(view.getContext()
//                        , android.R.style.Theme_Holo_Light_Dialog_MinWidth
//                        , onDateSetListener, year, month, day);
//                pickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//                pickerDialog.show();
//
//            }
//        });
//        onDateSetListener = new DatePickerDialog.OnDateSetListener() {
//            @Override
//            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
//                month = month + 1;
//                String date = day + "/" + month + "/" + year;
//                txtdate.setText(date);
//            }
//        };
//
//        //chức năng Thêm
//        btnThem.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                DBChamCong dbChamCong = new DBChamCong(getContext());
//                ChamCong chamCong = layDL();
////                dbChamCong.ThemDL(ChamCong);
//                hienthiDL();
//            }
//        });
//    }
//    private void setControl(View v) {
//        txtdate = v.findViewById(R.id.tv_date);
//        btnDate = v.findViewById(R.id.btndate);
//        spinnercc = v.findViewById(R.id.spnMaCongNhan);
//        //
//
//        btnThem = v.findViewById(R.id.btnThem_chamcong);
//        btnXoa = v.findViewById(R.id.btnXoa_chamcong);
//        btnSua = v.findViewById(R.id.btnSua_chamcong);
//        btnClear = v.findViewById(R.id.btnClear_chamcong);
//        edtMaChamCong = v.findViewById(R.id.edt_Macc);
//
//
//    }
//
//    private void hienthiDL() {
//        DBChamCong dbChamCong = new DBChamCong(this.getContext());
//
//        chamCongs = dbChamCong.getDuLieu();
//
//        customlv_adaptercc = new Custom_Listview_ChamCong(this.getContext(), R.layout.item_listview_chamcong, chamCongs);
//        lvdsChamCong.setAdapter(customlv_adaptercc);
//    }
//
//    //ham lay du lieu
//    private ChamCong layDL() {
//
//        ChamCong chamCong = new ChamCong();
//        chamCong.setMaCC(edtMaChamCong.getText().toString());
//
//
//        chamCong.setNgayCC(txtdate.getText().toString());
//        chamCong.setMaCN(spinnercc.getSelectedItem().toString());
//        return chamCong;
//    }
//
//    //
//    private void ShowSpiner() {
//        DBCongNhan dbCongNhan = new DBCongNhan(getContext());
//        congNhans = dbCongNhan.getDuLieu();
//        for (CongNhan congNhan :
//                congNhans) {
//            data_spr.add(congNhan.getMaCN());
//        }
//    }
//
//}
