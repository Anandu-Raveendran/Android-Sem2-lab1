package com.example.anandusem2lab1;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.example.anandusem2lab1.Database.User;
import com.example.anandusem2lab1.databinding.RecyclerItemLayoutBinding;

import java.text.DateFormat;
import java.util.List;

public class MyRecyclerViewAdaptor extends RecyclerView.Adapter<MyRecyclerViewAdaptor.MyViewHolder> {

    private Context context;
    private List<User> userList;
    private ListDelegate delegate;

    public void setUserList(List<User> userList) {
        this.userList = userList;
        notifyDataSetChanged();
    }

    public MyRecyclerViewAdaptor(Context context, ListDelegate delegate) {
        this.context = context;
        this.delegate = delegate;
    }

    public interface ListDelegate {
        void listItemClicked(View v, int posisiton);
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
        RecyclerItemLayoutBinding binding = RecyclerItemLayoutBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);

        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(MyRecyclerViewAdaptor.MyViewHolder holder, int position) {
        Log.i("anandu", "Binding ViewHolder for position " + position + " " + holder.binding.toString());
        holder.binding.name.setText(userList.get(position).getName());
        holder.binding.age.setText(String.valueOf(userList.get(position).getAge()));
        holder.binding.tuition.setText(String.valueOf(userList.get(position).getTuition()));
        holder.binding.startDate.setText(DateFormat.getDateInstance().format(userList.get(position).getStart_date()));
        holder.binding.Itemlayout.setTag(position);
        holder.binding.Itemlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delegate.listItemClicked(v, (Integer) v.getTag());
            }
        });

    }


    @Override
    public int getItemCount() {
        Log.i("anandu", "Adaptor count " + userList.size());
        return userList.size();
    }


}

