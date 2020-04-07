package com.example.assignment12;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.assignment12.R.string;

import java.util.ArrayList;
import java.util.List;

class RecyclerAdapteri extends RecyclerView.Adapter<RecyclerAdapteri.ViewHolder> {

    private List<User> mData = new ArrayList<>();
    private LayoutInflater layoutInflater;

    RecyclerAdapteri(Context context){
        this.layoutInflater = LayoutInflater.from(context);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView id;
        TextView name;
        TextView userName;
        TextView email;
        TextView street;
        TextView city;
        ViewHolder(@NonNull View itemView){
            super(itemView);
            id = itemView.findViewById(R.id.id);
            name = itemView.findViewById(R.id.name);
            userName = itemView.findViewById(R.id.userName);
            email = itemView.findViewById(R.id.email);
            street = itemView.findViewById(R.id.street);
            city = itemView.findViewById(R.id.city);
        }
    }

    public void setItems(List<User> lista){

        this.mData = lista;

        DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() {
            @Override
            public int getOldListSize() {
                return mData.size();
            }

            @Override
            public int getNewListSize() {
                return lista.size();
            }

            @Override
            public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                return mData.get(oldItemPosition).equals(lista.get(newItemPosition));
            }

            @Override
            public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                return false;
            }
        });
        mData = lista;
        result.dispatchUpdatesTo(this);
        //notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View v = layoutInflater.inflate(R.layout.user_tiedot, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String id = String.valueOf(mData.get(position).getId());
        String name = mData.get(position).getName();
        String userName = mData.get(position).getUserName();
        String email = mData.get(position).getEmail();
        String street = mData.get(position).getStreet();
        String city = mData.get(position).getCity();

        holder.id.setText(id);
        holder.name.setText(name);
        holder.userName.setText(userName);
        holder.email.setText(email);
        holder.street.setText(street);
        holder.city.setText(city);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
}
