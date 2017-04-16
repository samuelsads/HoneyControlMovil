package com.example.sads.honeycontrol.adapters;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sads.honeycontrol.R;
import com.example.sads.honeycontrol.models.Client;
import com.example.sads.honeycontrol.models.Products;
import com.example.sads.honeycontrol.service.ApiAdapter;
import com.example.sads.honeycontrol.service.response.ResponseDeleteClient;
import com.example.sads.honeycontrol.service.response.ResponseDeleteProduct;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by sads on 14/04/17.
 */
public class adapterProduct  extends RecyclerView.Adapter<adapterProduct.ViewHolder> {
    private List<Products> product;
    private int layout;
    private OnItemClickListener itemClickListener;
    private Context context;
    private Activity activity;
    private String id="1";
    private String pass="aduLvDJ7Lk74c";

    public adapterProduct(List<Products> product, int layout, Activity activity, OnItemClickListener listener) {
        this.product = product;
        this.layout = layout;
        this.itemClickListener = listener;
        this.activity = activity;

    }

    @Override
    public adapterProduct.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v  = LayoutInflater.from(parent.getContext()).inflate(layout,parent,false);
        context = parent.getContext();
        ViewHolder vh  = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(adapterProduct.ViewHolder holder, int position) {
        holder.bind(product.get(position),itemClickListener);
    }

    @Override
    public int getItemCount() {
        return product.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener, MenuItem.OnMenuItemClickListener {
        public TextView textViewPrice;
        public TextView textViewSize;

        public ViewHolder(View itemView) {
            super(itemView);
            textViewPrice = (TextView) itemView.findViewById(R.id.textViewPrice);
            textViewSize = (TextView) itemView.findViewById(R.id.textViewSize);
            itemView.setOnCreateContextMenuListener(this);

        }

        public void bind(final Products product, final OnItemClickListener listener) {
            // this.TextViewName.setText(name);
            textViewPrice.setText(product.getPrice());
            textViewSize.setText(product.getSize());
            //imageView.setImageResource(movies.getPoster());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClik(product, getAdapterPosition());
                }
            });
        }

        @Override
        public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
            Products myproducts  = product.get(this.getAdapterPosition());
            contextMenu.setHeaderTitle(myproducts.getSize());
            MenuInflater inflater  = activity.getMenuInflater();
            inflater.inflate(R.menu.menu_options,contextMenu);
            for (int i =0; i<contextMenu.size();i++){
                contextMenu.getItem(i).setOnMenuItemClickListener(this);
            }
        }

        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            switch (menuItem.getItemId()){
                case R.id.delete:
                   deleteMyProduct(product.get(getAdapterPosition()).getId());
                    product.remove(getAdapterPosition());
                    notifyItemRemoved(getAdapterPosition());
                    Toast.makeText(activity,"eliminar el id1 ",Toast.LENGTH_LONG).show();
                    return  true;
                default:
                    return  false;

            }
        }
    }

    private void deleteMyProduct(int idProduct){
        Call<ResponseDeleteProduct> call = ApiAdapter.getApiService().deleteProduct(id,pass,idProduct);
        call.enqueue( new ResponsableCallBack());
    }

    class ResponsableCallBack implements Callback<ResponseDeleteProduct>{

        @Override
        public void onResponse(Call<ResponseDeleteProduct> call, Response<ResponseDeleteProduct> response) {
            if(response.isSuccessful()){
                ResponseDeleteProduct deletes = response.body();
                if(deletes.isSuccess()){

                }
            }
        }

        @Override
        public void onFailure(Call<ResponseDeleteProduct> call, Throwable t) {

        }
    }
    public interface OnItemClickListener{
        void onItemClik(Products product, int position);
    }
}
