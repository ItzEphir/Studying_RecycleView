package com.ephirium.recyclerapplication;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

public class ItemTouchManager extends ItemTouchHelper.Callback {
    private final ItemAnimationListener animationListener;

    public ItemTouchManager(ItemAnimationListener itemAnimationListener){
        animationListener = itemAnimationListener;
    }

    @Override
    public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN | ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
        int swipeFlags = ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT;
        return makeMovementFlags(dragFlags, swipeFlags);
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        if(animationListener != null){
            animationListener.onMove(viewHolder.getAdapterPosition(), target.getAdapterPosition());
        }

        return true;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
        if(animationListener != null){
            animationListener.onSwipe(direction, viewHolder.getAdapterPosition());
        }
    }
}
