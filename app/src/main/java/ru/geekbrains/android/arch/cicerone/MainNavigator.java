package ru.geekbrains.android.arch.cicerone;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

import ru.terrakok.cicerone.android.SupportAppNavigator;

/**
 * Навигатор реализует команды - создаёт и переключает экраны средствами Android
 * При необходимости можно написать полностью собственный навигатор реализовав интерфейс Navigator
 * В данном случае переключение экранов (запуск активити и работу с fragment manager)
 * берут на себя базовые навигаторы, включенные в библиотеку Cicerone
 * SupportFragmentNavigator отвечает за работу с фрагментами
 * SupportAppNavigator работает с активити
 * В данном случае реализация MainNavigator отвечает только за создание фрагментов
 */
class MainNavigator extends SupportAppNavigator {

    // activity используется для запуска других активити
    // fragmentManager для работы с фрагментами
    // containerId -- контейнер, куда будут помещаться фрагменты
    MainNavigator(FragmentActivity activity, FragmentManager fragmentManager, int containerId) {
        super(activity, fragmentManager, containerId);
    }

    @Override
    protected Intent createActivityIntent(Context context, String screenKey, Object data) {
        // при выполнении команды навигации сначала будет вызван этот метод
        // если он вернёт интент, активити будет запущено и выполнение команды завершится
        // в данном же случае управление будет передано навигатору фрагментов и методу createFragment
        return null;
    }

    /**
     * в этом методы определены способы создания фрагментов для соответствующих экранов
     * @param screenKey -- экран, для которого будет создан фрагмент
     * @param data --данные, которые были переданы роутеру в метод перехода на экран
     * @return -- фрагмент, соответствующий экрану или null, если соответствия не найдено
     */
    @Override
    protected Fragment createFragment(String screenKey, Object data) {
        // в нашем примере все возможные экраны реализуются фрагментами
        switch (screenKey) {
            case Screens.MAIN: return new Fragment1();
            case Screens.TOP: return new Fragment2();
            // данные передаются обычным образом,
            // минусом является передача по Object c приведением типа
            case Screens.BOTTOM: return Fragment3.newInstance(data);
        }

        return null;
    }


}
