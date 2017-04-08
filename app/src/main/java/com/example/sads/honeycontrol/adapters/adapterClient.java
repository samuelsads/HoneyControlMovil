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

import com.example.sads.honeycontrol.R;
import com.example.sads.honeycontrol.models.Client;

import java.util.List;

/**
 * Created by sads on 31/03/17.
 */
public class adapterClient extends RecyclerView.Adapter<adapterClient.ViewHolder> {

    private List<Client> client;
    private int layout;
    private OnItemClickListener itemClickListener;
    private Context context;
    private Activity activity;

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
                    client.remove(getAdapterPosition());
                    notifyItemRemoved(getAdapterPosition());
                    return  true;
                default:
                    return  false;

            }
        }
    }
        public interface OnItemClickListener{
            void onItemClik(Client client, int position);
        }
}
