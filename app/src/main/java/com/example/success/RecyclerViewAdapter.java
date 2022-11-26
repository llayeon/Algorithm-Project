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

    private List<Model> mModelList;

    public RecyclerViewAdapter(List<Model> modelList) {
        mModelList = modelList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.table_list, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final Model model = mModelList.get(position);

        holder.imageView.setImageResource(R.drawable.table);

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

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private View view;
        private ImageView imageView;

        private MyViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            imageView = (ImageView) itemView.findViewById(R.id.imgview);
        }
    }
}
