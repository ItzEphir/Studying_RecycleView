package com.ephirium.recyclerapplication;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ephirium.recyclerapplication.databinding.FragmentItemBinding;

public class ItemFragment extends Fragment {

    private FragmentItemBinding binding;

    public String text;

    public static ItemFragment newInstance(String text){
        ItemFragment itm = new ItemFragment();
        itm.text = text;
        return itm;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentItemBinding.inflate(getLayoutInflater());

        binding.text.setText(text);

        return binding.getRoot();
    }
}