package tdc.edu.vn.projectquanlychamcongvsactivity.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Vector;

import tdc.edu.vn.projectquanlychamcongvsactivity.R;
import tdc.edu.vn.projectquanlychamcongvsactivity.model.CardViewModel;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    //tao mang 1 chieu cho cardviewmodel
    private Vector<CardViewModel> list;

    //code -> generate
    public MyAdapter(Vector<CardViewModel> list) {
        this.list = list;
    }

    //ke' thua tu lop cha,
    static class MyViewHolder extends RecyclerView.ViewHolder {
        // trong layout card view co bnhiu phan tu thi ben day co bay nhiu phan tu
        ImageView imageView;
        TextView textView;

        //itemView nay` 9 la View da~ chua' layout do'
        public MyViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);


        }
    }

    //tạo mới phần tử // viewType là id tương ứng nếu được truyền vào
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        CardView cardView;
        //định nghĩa 1 layout inflater
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        //cardview chứa layout card view.                  truyền trực tiếp
        cardView = (CardView) inflater.inflate(R.layout.card_view_layout,viewGroup,false);
//        cardView = (CardView) inflater.inflate(viewType, viewGroup, false);

        //nếu được gọi nó sẻ quay lại public MyViewHolder
        return new MyViewHolder(cardView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder viewHolder, int position) {
        //tại vị trí position tui lấy ra 1 cái aCard ( dữ liệu )
        CardViewModel aCard = list.get(position);
        //xét dữ liệu vào viewHolder

        viewHolder.imageView.setImageResource(aCard.getImageResourceId());

    }

    //trả về số phần tử của recycler bấy nhiêu phần tử thì nó return bấy nhiêu
    @Override
    public int getItemCount() {
        return list.size();
    }

    //chuyển ViewTy đó tương ứng với layout nào
//    @Override
//    public int getItemViewType(int position) {
//        if (position % 2 == 0) {
//            return R.layout.card_view_layout;
//        } else {
//            return R.layout.card_view_layouy_inver;
//        }
//    }
}
