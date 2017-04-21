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
import com.example.sads.honeycontrol.activities.DashActivity;
import com.example.sads.honeycontrol.models.Client;
import com.example.sads.honeycontrol.service.ApiAdapter;
import com.example.sads.honeycontrol.service.response.ResponseClient;
import com.example.sads.honeycontrol.service.response.ResponseDeleteClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by sads on 31/03/17.
 */
public class adapterClient extends RecyclerView.Adapter<adapterClient.ViewHolder> {

    private List<Client> client;
    private int layout;
    private OnItemClickListener itemClickListener;
    private Context context;
    private Activity activity;
    private String id="1";
    private String pass="aduLvDJ7Lk74c";


    public adapterClient(List<Client> client, int layout, Activity activity, OnItemClickListener listener) {
        this.client = client;
        this.layout = layout;
        this.itemClickListener = listener;
        this.activity = activity;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v  = LayoutInflater.from(parent.getContext()).inflate(layout,parent,false);
        context = parent.getContext();
        ViewHolder vh  = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(client.get(position),itemClickListener);
    }


    @Override
    public int getItemCount() {
        return client.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener, MenuItem.OnMenuItemClickListener {
        public TextView textViewName;

        public ViewHolder(View itemView) {
            super(itemView);
            textViewName = (TextView) itemView.findViewById(R.id.textViewTitle);
            itemView.setOnCreateContextMenuListener(this);

        }

        public void bind(final Client client, final OnItemClickListener listener) {
            // this.TextViewName.setText(name);
            textViewName.setText(client.getName());
            //imageView.setImageResource(movies.getPoster());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClik(client, getAdapterPosition());
                }
            });
        }

        @Override
        public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
            Client myclient  = client.get(this.getAdapterPosition());
            contextMenu.setHeaderTitle(myclient.getName());
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
                    deleteMyClient(client.get(getAdapterPosition()).getId());
                    client.remove(getAdapterPosition());
                    notifyItemRemoved(getAdapterPosition());
                    Toast.makeText(activity,"El cliente fue eliminado con exito",Toast.LENGTH_LONG).show();
                    return  true;
                default:
                    return  false;

            }
        }
    }
        public interface OnItemClickListener{
            void onItemClik(Client client, int position);
        }

    private void deleteMyClient(int idClient){
        Call<ResponseDeleteClient> call = ApiAdapter.getApiService().deleteClient(id,pass,idClient);
        call.enqueue( new ResponsableCallBack());
    }
    class ResponsableCallBack implements Callback<ResponseDeleteClient>{

        @Override
        public void onResponse(Call<ResponseDeleteClient> call, Response<ResponseDeleteClient> response) {
            if(response.isSuccessful()){
                ResponseDeleteClient responsable= response.body();
                if(responsable.isSuccess()){
                    responsable.isSuccess();

                }
            }
        }


        @Override
        public void onFailure(Call<ResponseDeleteClient> call, Throwable t) {

        }
    }
}
