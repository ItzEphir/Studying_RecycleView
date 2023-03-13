package com.ephirium.recyclerapplication;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ephirium.recyclerapplication.databinding.RecyclerItemBinding;

import java.util.ArrayList;
import java.util.List;

public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ViewHolder> {

    private List<Item> items;
    private final ItemsCallback itemsCallback;

    public ItemsAdapter(List<Item> items, ItemsCallback itemsCallback){
        this.items = items;
        this.itemsCallback = itemsCallback;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerItemBinding binding = RecyclerItemBinding
                .inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(items.get(position), this, itemsCallback);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void addItem(Item item){
        items.add(item);
        notifyDataSetChanged();
    }

    public void delItem(int pos){
        items.remove(pos);
        Item.last_id = 0;
        List<Item> newItems = new ArrayList<>();
        for(Item item : items){
            newItems.add(new Item(item.getText()));
        }
        items = newItems;
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        private final RecyclerItemBinding binding;

        public ViewHolder(RecyclerItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Item item, ItemsAdapter adapter, ItemsCallback itemsCallback){
            binding.text.setText(item.getText());

            binding.deleteButton.setOnClickListener(v -> {
                int id = item.getId();
                adapter.delItem(id);
            });

            binding.getRoot().setOnClickListener(view -> itemsCallback.OnClick(item));
        }
    }
}
