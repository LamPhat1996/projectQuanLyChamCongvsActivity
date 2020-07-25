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
import tdc.edu.vn.projectquanlychamcongvsactivity.R;
import tdc.edu.vn.projectquanlychamcongvsactivity.model.CongNhan;

public class Custom_listview_Congnhan extends ArrayAdapter {
    Context context;
    int resource;
    ArrayList<CongNhan> congNhans;
    ArrayList<CongNhan> data_danhsach;

    public Custom_listview_Congnhan(@NonNull Context context, int resource, @NonNull ArrayList<CongNhan> congNhans) {
        super(context, resource);
        this.context = context;
        this.resource = resource;
        this.congNhans = congNhans;
        this.data_danhsach = new ArrayList<CongNhan>();
        this.data_danhsach.addAll(congNhans);
    }

    @Override
    public int getCount() {
        return congNhans.size();
    }

    @NonNull
    @Override
    public View getView(int position,View convertView,ViewGroup parent) {
        View v = LayoutInflater.from(context).inflate(resource, null);
        ImageView imgcongnhan = v.findViewById(R.id.img_congnhan);

        TextView tvMaCN = v.findViewById(R.id.tv_MaCN);
        TextView tvHoCN = v.findViewById(R.id.tv_hocn);
        TextView tvTenCN = v.findViewById(R.id.tv_tenCN);
        TextView tvPhanXuong = v.findViewById(R.id.tv_phanXuong);


        CongNhan congNhan = congNhans.get(position);

        imgcongnhan.setImageResource(R.drawable.cong_nhan);
        tvMaCN.setText(congNhan.getMaCN());
        tvHoCN.setText(congNhan.getHoCN());
        tvTenCN.setText(congNhan.getTenCN());
        tvPhanXuong.setText(congNhan.getPhanXuong());


        return v;
    }

    public void filter (String charText){
        charText = charText.toLowerCase(Locale.getDefault());
        congNhans.clear();
        if(charText.length() == 0)
        {
            congNhans.addAll(data_danhsach);

        }
        else {
            for (CongNhan model : data_danhsach)
            {
                if (model.getMaCN().toLowerCase(Locale.getDefault())
                        .contains(charText))
                {
                    congNhans.add(model);}

            }
        }
        notifyDataSetChanged();
    }

}
