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
import tdc.edu.vn.projectquanlychamcongvsactivity.model.ChamCong;


public class Custom_Listview_ChamCong extends ArrayAdapter {
    Context context;
    int resource;
    ArrayList<ChamCong> chamCongs;
    ArrayList<ChamCong> data_danhsach;
    public Custom_Listview_ChamCong(@NonNull Context context, int resource, @NonNull ArrayList<ChamCong> chamCongs) {

        super(context, resource, chamCongs);
        this.context = context;
        this.resource = resource;
        this.chamCongs = chamCongs;
        this.data_danhsach = new ArrayList<ChamCong>();
        this.data_danhsach.addAll(chamCongs);
    }

    @Override
    public int getCount() {
        return chamCongs.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(resource, null);
        ImageView imgchamcong = view.findViewById(R.id.img_chamcong);
        TextView tvMachamcong = view.findViewById(R.id.tv_machamcong);
        TextView tvNgayChamCong = view.findViewById(R.id.tv_ngaychamcong);
        TextView tvMaCN = view.findViewById(R.id.tv_macongnhan);
        //
        ChamCong chamCong = chamCongs.get(position);
        //
        imgchamcong.setImageResource(R.drawable.cham_cong);
        tvMachamcong.setText(chamCong.getMaCC());
        tvNgayChamCong.setText(chamCong.getNgayCC());
        tvMaCN.setText(chamCong.getMaCN());
        return view;
    }
    public void filter (String charText){
        charText = charText.toLowerCase(Locale.getDefault());
        chamCongs.clear();
        if(charText.length() == 0)
        {
            chamCongs.addAll(data_danhsach);

        }
        else {
            for (ChamCong model : data_danhsach)
            {
                if (model.getMaCC().toLowerCase(Locale.getDefault())
                        .contains(charText))
                {
                    chamCongs.add(model);}

            }
        }
        notifyDataSetChanged();
    }
}
