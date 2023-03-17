package com.ephirium.recyclerapplication;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ephirium.recyclerapplication.databinding.FragmentRecyclerBinding;

import java.util.ArrayList;
import java.util.List;

public class RecyclerFragment extends Fragment implements ItemAnimationListener {

    private List<Item> items = new ArrayList<>();

    private ItemsAdapter itemsAdapter = new ItemsAdapter(items, this::OnClick);

    private FragmentRecyclerBinding binding;

    public static RecyclerFragment newInstance(){
        return new RecyclerFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentRecyclerBinding.inflate(getLayoutInflater());

        binding.addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txt = String.valueOf(binding.inputText.getText());
                itemsAdapter.addItem(new Item(txt));
                binding.inputText.setText("");
            }
        });

        new ItemTouchHelper(new ItemTouchManager(this)).attachToRecyclerView(binding.recycler);

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

    @Override
    public void onMove(int from, int to) {
        items.add(to, items.remove(from));
        itemsAdapter.notifyItemMoved(from, to);
    }

    @Override
    public void onSwipe(int direction, int pos) {
        items.remove(pos);
        itemsAdapter.notifyItemRemoved(pos);
    }
}