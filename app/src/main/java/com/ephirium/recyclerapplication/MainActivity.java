package com.ephirium.recyclerapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.ephirium.recyclerapplication.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());

        StartFragment(RecyclerFragment.newInstance());

        setContentView(binding.getRoot());
    }

    private void StartFragment(Fragment fragment){
        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment, null).commit();
    }
}