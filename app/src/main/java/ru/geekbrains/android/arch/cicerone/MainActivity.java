package ru.geekbrains.android.arch.cicerone;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Единственная активити-контейнер
 * Содержит минимум кода
 */
public class MainActivity extends AppCompatActivity {

    private MainNavigator navigator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // создаём навигатор, который отвечает за всю работу с созданием и переключанием экранов
        navigator = new MainNavigator(this, getSupportFragmentManager(), R.id.container);

        // для упрощения примера навигация на стартовый экран выполнена прямо из активити
        // с точки зрения чистоты архитектуры её можно перенести, например, в презентер активити
        NavigationApplication.INSTANCE.getRouter().replaceScreen(Screens.MAIN);
    }

    @Override
    protected void onResumeFragments() {
        super.onResumeFragments();

        // здесь навигатор подключается к роутеру и может выполнять команды навигации
        // таким образом навигация осуществляется только в активном состоянии приложения
        // onResumeFragments -- рекомендованный метод для подключения навигатора
        // для корректной работы с фрагментами
        NavigationApplication.INSTANCE.getNavigatorHolder().setNavigator(navigator);
    }

    @Override
    protected void onPause() {
        super.onPause();

        // навигатор отключается от роутера и перестает выполнять команды
        NavigationApplication.INSTANCE.getNavigatorHolder().removeNavigator();
    }

}
