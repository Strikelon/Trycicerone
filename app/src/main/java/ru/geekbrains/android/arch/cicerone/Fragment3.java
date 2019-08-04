package ru.geekbrains.android.arch.cicerone;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import ru.terrakok.cicerone.Router;


/**
 * Третий фрагмент. Принимает в качестве аргумента счётчик
 * Имеет кнопку "вперед" которая также навигирует на этот же фрагмент
 */
public class Fragment3 extends Fragment implements Fragment3Presenter.View {

    public static final String ARG_COUNTER = "arg_counter";

    private Fragment3Presenter presenter;
    private TextView textView;

    public Fragment3() {
        // Required empty public constructor
    }

    /**
     * Типичный фабричный метод для создания фрагмента
     * отличие здесь в передаче параметра в виде Object
     * такое решение обусловлено желанием авторов Cicerone обеспечить независимость от Bundle
     */
    public static Fragment newInstance(Object counter) {
        Fragment3 fragment3 = new Fragment3();
        Bundle bundle = new Bundle();

        // использование Object вынуждает применять приведение типов
        if (counter instanceof Integer) {
            bundle.putInt(ARG_COUNTER, (Integer) counter);
        }
        fragment3.setArguments(bundle);
        return fragment3;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // используется стандартный механизм передачи аргументов
        // получаем счётчик из аргументов, чтобы отобразить его на экране
        // при запуске следующего фрагмента счётчик увеличиваем
        // - таким образом видно, что фрагмент пересоздается
        int counter = 0;
        Bundle arguments = getArguments();
        if (arguments != null) {
            counter = arguments.getInt(ARG_COUNTER);
        }

        // получаем роутер из синглтона - простое рабочее решение
        // при этом мы передаем роутер в презентер через конструктор,
        // а не вызываем статический метод внутри
        // это позволит в будущем перейти на DI или подменить роутер при тестировании
        Router router = NavigationApplication.INSTANCE.getRouter();
        // передаём роутер в презентер таким же образом, как в активити
        presenter = new Fragment3PresenterImpl(this, router, counter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_fragment3, container, false);

        textView = root.findViewById(R.id.text_view);

        root.findViewById(R.id.forward).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onForwardClick();
            }
        });

        return root;
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.onStart();
    }

    @Override
    public void showText(String text) {
        textView.setText(text);
    }
}
