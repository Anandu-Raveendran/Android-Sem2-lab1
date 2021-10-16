package com.example.anandusem2lab1;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.Toast;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import com.example.anandusem2lab1.Database.AppDatabase;
import com.example.anandusem2lab1.Database.User;
import com.example.anandusem2lab1.databinding.FragmentEditBinding;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

public class EditFragment extends Fragment implements DatePickerDialog.OnDateSetListener {

    private FragmentEditBinding binding;
    private Date star_date;
    private AppDatabase db;

    private User selectedUser = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = AppDatabase.getDbInstance(getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentEditBinding.inflate(inflater);
        setHasOptionsMenu(true);

        binding.startEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment datePickerFragment = new DatePickerFragment(EditFragment.this);
                datePickerFragment.show(getActivity().getSupportFragmentManager(), "datePicker");
            }
        });

        EditFragmentArgs args = EditFragmentArgs.fromBundle(getArguments());
        selectedUser = args.getUser();

        if (selectedUser != null) {
            binding.delete.setVisibility(View.VISIBLE);

            binding.nameEditText.setText(selectedUser.getName());
            binding.tuitionEditText.setText(selectedUser.getTuition().toString());
            binding.ageEditText.setText(String.valueOf(selectedUser.getAge()));
            star_date = selectedUser.getStart_date();
            binding.startEditText.setText(DateFormat.getDateInstance().format(star_date.getTime()));
        } else {
            binding.delete.setVisibility(View.GONE);
        }

        binding.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.userDao().delete(selectedUser);
                Navigation.findNavController(v).popBackStack();
            }
        });
        return binding.getRoot();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.editview_menu_items, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.save_item: {

                if (binding.nameEditText.getText().toString().isEmpty() ||
                        binding.ageEditText.getText().toString().isEmpty() ||
                        binding.tuitionEditText.getText().toString().isEmpty() ||
                        binding.startEditText.getText().toString().isEmpty()) {
                    Toast.makeText(getContext(), "Error: Missing data field!", Toast.LENGTH_SHORT).show();
                } else {

                    if (selectedUser == null) {
                        User user = new User(binding.nameEditText.getText().toString(),
                                Integer.valueOf(binding.ageEditText.getText().toString()),
                                Double.valueOf(binding.tuitionEditText.getText().toString()), star_date);

                        db.userDao().insertUser(user);

                    } else {
                        selectedUser.setName(binding.nameEditText.getText().toString());
                        selectedUser.setAge(Integer.valueOf(binding.ageEditText.getText().toString()));
                        selectedUser.setTuition(Double.valueOf(binding.tuitionEditText.getText().toString()));
                        selectedUser.setStart_date(star_date);

                        db.userDao().update(selectedUser);
                    }
                    return NavHostFragment.findNavController(this).popBackStack();
                }
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

        Calendar c = Calendar.getInstance();
        c.set(year, month, dayOfMonth);
        String currentDateString = DateFormat.getDateInstance().format(c.getTime());
        binding.startEditText.setText(currentDateString);
        star_date = c.getTime();
    }
}