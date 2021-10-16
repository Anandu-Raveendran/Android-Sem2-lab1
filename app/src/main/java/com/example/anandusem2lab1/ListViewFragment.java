package com.example.anandusem2lab1;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.anandusem2lab1.Database.AppDatabase;
import com.example.anandusem2lab1.Database.User;
import com.example.anandusem2lab1.databinding.FragmentListViewBinding;

import java.util.List;


public class ListViewFragment extends Fragment implements MyRecyclerViewAdaptor.ListDelegate {

    private FragmentListViewBinding binding;
    private List<User> userList;
    private MyRecyclerViewAdaptor adaptor;
    private AppDatabase db;

    public ListViewFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        db = AppDatabase.getDbInstance(getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentListViewBinding.inflate(inflater);

        adaptor = new MyRecyclerViewAdaptor(getContext(), this);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recyclerView.setAdapter(adaptor);
        updateList();

        return binding.getRoot();
    }

    void updateList() {
        userList = db.userDao().getAllUsers();
        Log.i("anandu", "Got date size " + userList.size());
        adaptor.setUserList(userList);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.listview_menu_items, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.addItem: {
                NavHostFragment.findNavController(this).navigate(R.id.action_listViewFragment_to_editFragment);
                break;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void listItemClicked(View v, int position) {
        Log.i("anandu", "clicked position " + position);
        ListViewFragmentDirections.ActionListViewFragmentToEditFragment action = ListViewFragmentDirections.actionListViewFragmentToEditFragment();
        action.setUser(userList.get(position));
        Navigation.findNavController(v).navigate(action);
    }
}