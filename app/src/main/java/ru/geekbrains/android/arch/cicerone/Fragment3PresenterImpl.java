package ru.geekbrains.android.arch.cicerone;

import ru.terrakok.cicerone.Router;

class Fragment3PresenterImpl implements Fragment3Presenter {

    private final View view;
    private final Router router;

    // значение счётчика для текущего экрана неизменно
    private final int counter;

    /**
     * помимо view получаем в конструкторе Router для возможности навигации
     * и счётчик, переданный во фрагмент через аргументы
     */
    Fragment3PresenterImpl(View view, Router router, int counter) {
        this.view = view;
        this.router = router;
        this.counter = counter;
    }


    @Override
    public void onStart() {
        // отображаем на старте счётчик для наглядности
        view.showText("Fragment 3. Counter: " + counter);
    }

    @Override
    public void onForwardClick() {
        // выполняем навигацию на экран BOTTOM (который реализован этим же фрагментом номер 3)
        // чтобы избежать наполнение стэка одинаковыми фрагментами (с разным значением счётчика)
        // используем метод replaceScreen который будет заменять верхний экран на новый
        // передаем данные (увеличенное значение счётчика) для нового экрана
        router.replaceScreen(Screens.BOTTOM, counter + 1);
    }
}
