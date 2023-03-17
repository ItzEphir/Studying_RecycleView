package com.ephirium.recyclerapplication;

public interface ItemAnimationListener {
    void onMove(int from, int to);

    void onSwipe(int direction, int pos);
}
