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
import tdc.edu.vn.projectquanlychamcongvsactivity.model.SanPham;


public class Custom_ListView_sanpham extends ArrayAdapter {
    Context context;
    int resource;
    ArrayList<SanPham> sanPhams;
    ArrayList<SanPham> data_sanpham;

    public Custom_ListView_sanpham( Context context, int resource,ArrayList<SanPham>sanPhams) {
        super(context,resource,sanPhams);
        this.context = context;
        this.resource = resource;
        this.sanPhams = sanPhams;
        this.data_sanpham = new ArrayList<SanPham>();
        this.data_sanpham.addAll(sanPhams);
    }
    @Override
    public int getCount() {
        return sanPhams.size();
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(resource,null);
        ImageView img_SanPham = view.findViewById(R.id.img_sanpham);
        TextView tv_MaSP = view.findViewById(R.id.tv_MaSP);
        TextView tv_TenSP = view.findViewById(R.id.tv_TenSP);
        TextView tv_DonGia = view.findViewById(R.id.tv_DongGia);
        //
        SanPham sanPham = sanPhams.get(position);
        //
        img_SanPham.setImageResource(R.drawable.san_pham);
        tv_MaSP.setText(sanPham.getMaSP());
        tv_TenSP.setText(sanPham.getTenSP());
        tv_DonGia.setText(sanPham.getDonGia());
        return view;
    }
    public void filter (String charText){
        charText = charText.toLowerCase(Locale.getDefault());
        sanPhams.clear();
        if(charText.length() == 0)
        {
            sanPhams.addAll(data_sanpham);

        }
        else {
            for (SanPham model : data_sanpham)
            {
                if (model.getMaSP().toLowerCase(Locale.getDefault())
                        .contains(charText))
                {
                    sanPhams.add(model);}

            }
        }
        notifyDataSetChanged();
    }
}
