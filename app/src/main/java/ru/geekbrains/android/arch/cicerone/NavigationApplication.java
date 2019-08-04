package ru.geekbrains.android.arch.cicerone;

import android.app.Application;

import ru.terrakok.cicerone.Cicerone;
import ru.terrakok.cicerone.NavigatorHolder;
import ru.terrakok.cicerone.Router;

/**
 * Класс приложения в данном примере выполняет функцию хранилища-синглтона
 */
public class NavigationApplication extends Application {

    // один из способом получить доступ к классу приложения
    public static NavigationApplication INSTANCE;
    // Cicerone -- хранилище для объектов Router и NavigatorHolder
    // в них хранится очередь команд навигации,
    // которые создаются вызовом методов роутера
    private Cicerone<Router> cicerone;

    @Override
    public void onCreate() {
        super.onCreate();
        INSTANCE = this;

        initCicerone();
    }

    private void initCicerone() {
        cicerone = Cicerone.create();
    }

    // предоставляет NavigatorHolder -- возможность привязать навигатор для исполения команд из очереди
    public NavigatorHolder getNavigatorHolder() {
        return cicerone.getNavigatorHolder();
    }

    // предоставляет роутер -- абстракцию навигации не зависящую от Android
    public Router getRouter() {
        return cicerone.getRouter();
    }
}
