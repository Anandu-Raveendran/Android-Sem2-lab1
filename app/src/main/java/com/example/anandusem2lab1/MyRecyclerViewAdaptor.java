package com.example.anandusem2lab1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.example.anandusem2lab1.Database.User;
import com.example.anandusem2lab1.databinding.RecyclerItemLayoutBinding;

import java.text.DateFormat;
import java.util.List;

public class MyRecyclerViewAdaptor extends RecyclerView.Adapter<MyRecyclerViewAdaptor.MyViewHolder> {

    private Context context;
    private List<User> userList;

    public void setUserList(List<User> userList) {
        this.userList = userList;
        notifyDataSetChanged();
    }

    public MyRecyclerViewAdaptor(Context context) {
        this.context = context;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        RecyclerItemLayoutBinding binding;

        public MyViewHolder(RecyclerItemLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerItemLayoutBinding binding = RecyclerItemLayoutBinding.inflate(LayoutInflater.from(context), parent, false);

        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(MyRecyclerViewAdaptor.MyViewHolder holder, int position) {
        holder.binding.name.setText(userList.get(position).getName());
        holder.binding.age.setText(String.valueOf(userList.get(position).getAge()));
        holder.binding.tuition.setText(String.valueOf(userList.get(position).getTuition()));
        holder.binding.startDate.setText(DateFormat.getDateInstance().format(userList.get(position).getStart_date()));
    }


    @Override
    public int getItemCount() {
        return userList.size();
    }
}
