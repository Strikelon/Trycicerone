package ru.geekbrains.android.arch.cicerone;

/**
 * Список всех экранов в приложении, использующих навигацию Cicerone
 * Экраны кодируются простыми строками, что позволяет абстрагироваться от фреймворка
 * Экран может быть реализован фрагментов, активити или даже View в зависимости от используемого навигатора
 */
public class Screens {

    // главный экран, который будет показан при старте приложения
    public static final String MAIN = "main";
    // экран, открываемый по кнопке Top
    public static final String TOP = "top";
    // экран, открываемый по кнопке Bottom
    public static final String BOTTOM = "bottom";

}
