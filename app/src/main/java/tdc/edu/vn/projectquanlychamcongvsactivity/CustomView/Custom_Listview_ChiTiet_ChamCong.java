package tdc.edu.vn.projectquanlychamcongvsactivity.CustomView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import tdc.edu.vn.projectquanlychamcongvsactivity.R;
import tdc.edu.vn.projectquanlychamcongvsactivity.model.ChiTietChamCong;

public class Custom_Listview_ChiTiet_ChamCong extends ArrayAdapter {
    Context context;
    int resource;
    ArrayList<ChiTietChamCong>chiTietChamCongs;
    ArrayList<ChiTietChamCong> data_danhsach;

    public Custom_Listview_ChiTiet_ChamCong(Context context, int resource, ArrayList<ChiTietChamCong>chiTietChamCongs) {

        super(context, resource,chiTietChamCongs);
        this.context = context;
        this.resource = resource;
        this.chiTietChamCongs = chiTietChamCongs;
        this.data_danhsach = new ArrayList<ChiTietChamCong>();
        this.data_danhsach.addAll(chiTietChamCongs);
    }

    @Override
    public int getCount() {
        return chiTietChamCongs.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(resource,null);
        ImageView imgchitietchamcong = view.findViewById(R.id.img_chitiet_chamcong);
        TextView tvMaChamCong_in_ChiTietChamCong = view.findViewById(R.id.tv_machamcong_in_chitietchamcong);
        TextView tvMaSanPham_in_ChiTietChamCong = view.findViewById(R.id.tv_masanpham_in_chitietchamcong);
        TextView tvSoThanhPham_in_ChiTietChamCong = view.findViewById(R.id.tv_sothanhpham_in_chitietchamcong);
        TextView tvSoPhePham_in_ChiTietChamCong = view.findViewById(R.id.tv_sophepham_in_chitietchamcong);
        //
        ChiTietChamCong chiTietChamCong = chiTietChamCongs.get(position);
        //
        imgchitietchamcong.setImageResource(R.drawable.chi_tiet_cham_cong);
        tvMaChamCong_in_ChiTietChamCong.setText(chiTietChamCong.getMaCC());
        tvMaSanPham_in_ChiTietChamCong.setText(chiTietChamCong.getMaSP());
        tvSoThanhPham_in_ChiTietChamCong.setText(chiTietChamCong.getSoTP());
        tvSoPhePham_in_ChiTietChamCong.setText(chiTietChamCong.getSoPP());

        return view;
    }
    public void filter (String charText){
        charText = charText.toLowerCase(Locale.getDefault());
        chiTietChamCongs.clear();
        if(charText.length() == 0)
        {
            chiTietChamCongs.addAll(data_danhsach);

        }
        else {
            for (ChiTietChamCong model : data_danhsach)
            {
                if (model.getMaCC().toLowerCase(Locale.getDefault())
                        .contains(charText))
                {
                    chiTietChamCongs.add(model);}

            }
        }
        notifyDataSetChanged();
    }
}
