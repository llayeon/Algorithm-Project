package com.example.success;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;



public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    public List<Model> mModelList;

    //추가
    int position;

    public RecyclerViewAdapter(List<Model> modelList) {
        mModelList = modelList;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.table_list, parent, false);
        ItemClickListener itemClickListener = new ItemClickListener() {
            @Override
            public void onItemClick(int position) {

            }
        };
        return new MyViewHolder(view,itemClickListener);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final Model model = mModelList.get(position);

        holder.imageView.setImageResource(R.drawable.table);

        //추가 , 특정뷰에 태그를 달아준다
        holder.imageView.setTag(position);

        holder.imageView.setImageResource(model.isSelected() ? R.drawable.check: R.drawable.table);
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                model.setSelected(!model.isSelected());
                //holder.imageView.setImageResource(R.drawable.check);
                //view.setBackgroundColor에서 바꿈 확인할것 -> 잘 작동
                holder.imageView.setImageResource(model.isSelected() ? R.drawable.check: R.drawable.table);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mModelList == null ? 0 : mModelList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

         View view;
         ImageView imageView;
        ItemClickListener itemClickListener;

        public MyViewHolder(View itemView,ItemClickListener itemClickListener) {
            super(itemView);
            view = itemView;
            imageView = itemView.findViewById(R.id.imgview);

            this.itemClickListener = itemClickListener;
            imageView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v){
            itemClickListener.onItemClick(getAbsoluteAdapterPosition());
        }
    }

    public interface ItemClickListener{
        void onItemClick(int position);
    }


}


