package ru.geekbrains.android.arch.cicerone;

import ru.terrakok.cicerone.Router;

/**
 * Презентер стартового экрана-фрагмента
 */
class Fragment1PresenterImpl implements Fragment1Presenter {
    // так как роутер не содержит андроид зависимостей, его можно использовать в презентере
    private final Router router;

    Fragment1PresenterImpl(Router router) {
        this.router = router;
    }

    @Override
    public void onTopButtonClick() {
        // выполняем навигацию на экран по нажатию кнопки
        // в данном случае презентер не знает ничего о том, как реализован экран TOP
        // он может быть фрагментом, активити, может быть кастомным View
        // также вся работа по созданию экрана и переходу на него скрыта в нижнем слое навигации
        router.navigateTo(Screens.TOP);
    }

    @Override
    public void onBottomButtonClick() {
        // выполняем навигацию на другой экран по нажатию другой кнопки
        // здесь мы оперируем экранами со стороны бизнес-логики
        // -- мы знаем, что есть экран с определенным названием и что на него нужно перейти
        router.navigateTo(Screens.BOTTOM);
    }
}
