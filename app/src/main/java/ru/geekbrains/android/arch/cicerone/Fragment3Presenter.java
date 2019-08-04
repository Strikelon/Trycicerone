package ru.geekbrains.android.arch.cicerone;

public interface Fragment3Presenter {
    interface View {
        void showText(String text);
    }
    void onStart();

    void onForwardClick();
}
