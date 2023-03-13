package com.ephirium.recyclerapplication;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;

import com.ephirium.recyclerapplication.databinding.FragmentRecyclerBinding;

import java.util.ArrayList;
import java.util.List;

public class RecyclerFragment extends Fragment {

    private ItemsAdapter itemsAdapter;

    private FragmentRecyclerBinding binding;

    private List<Item> items;

    public static RecyclerFragment newInstance(){
        return new RecyclerFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentRecyclerBinding.inflate(getLayoutInflater());

        items = new ArrayList<>();

        itemsAdapter = new ItemsAdapter(items, this::OnClick);

        binding.addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txt = String.valueOf(binding.inputText.getText());
                itemsAdapter.addItem(new Item(txt));
                binding.inputText.setText("");
            }
        });

        binding.recycler.setAdapter(itemsAdapter);

        return binding.getRoot();
    }

    private void OnClick(Item item){
        startFragment(ItemFragment.newInstance(item.getText()));
    }

    private void startFragment(Fragment fragment){
        getParentFragmentManager().beginTransaction()
                .replace(R.id.container, fragment, null)
                .addToBackStack(null)
                .commit();
    }
}