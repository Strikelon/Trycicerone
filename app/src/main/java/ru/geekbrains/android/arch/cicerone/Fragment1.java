package ru.geekbrains.android.arch.cicerone;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * Стартовый фрагмент
 * Содержит две кнопки для перехода на другие фрагменты
 */
public class Fragment1 extends Fragment {

    public Fragment1() {
        // Required empty public constructor
    }

    // презентер отвечает за обработку нажатий на кнопки и вызов навигации через роутер
    private Fragment1Presenter presenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fragment1, container, false);

        // передаём роутер во фрагмент из синглтона
        // в настоящем проекте можно использовать какой-либо вариант DI
        presenter = new Fragment1PresenterImpl(NavigationApplication.INSTANCE.getRouter());

        view.findViewById(R.id.top).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onTopButtonClick();
            }
        });

        view.findViewById(R.id.bottom).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onBottomButtonClick();
            }
        });

        return view;
    }

}
