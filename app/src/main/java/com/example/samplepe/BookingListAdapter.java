package com.example.samplepe;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class BookingListAdapter extends RecyclerView.Adapter<BookingListAdapter.BookingViewHolder> {
    private Context context;
    private List<BookingEntity> productList;

    public BookingListAdapter(Context context, List<BookingEntity> productList) {
        this.context = context;
        this.productList = productList;
    }

    @NonNull
    @Override
    public BookingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.booking_item_layout, parent, false);
        return new BookingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookingViewHolder holder, int position) {
        BookingEntity p = productList.get(position);
        holder.idTv.setText("" + p.getId());
        holder.customerNameTv.setText(p.getCustomerName());
        holder.totalValueTv2.setText("" + p.getTotalValue());
        holder.dateTv.setText(p.getDate());
        holder.booking_item_ctn.setOnClickListener(v -> {
            Intent intent = new Intent(context, BookingDetailActivity.class);
            intent.putExtra("id", p.getId());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public static class BookingViewHolder extends RecyclerView.ViewHolder {
        private final TextView idTv, customerNameTv, dateTv, totalValueTv2;
        
        private final LinearLayout booking_item_ctn;
        public BookingViewHolder(@NonNull View itemView) {
            super(itemView);
            
            booking_item_ctn = itemView.findViewById(R.id.booking_item_ctn);
            idTv = itemView.findViewById(R.id.idTv);
            customerNameTv = itemView.findViewById(R.id.customerNameTv);
            dateTv = itemView.findViewById(R.id.dateTv);
            totalValueTv2 = itemView.findViewById(R.id.totalValueTv2);
        }
    }
}
