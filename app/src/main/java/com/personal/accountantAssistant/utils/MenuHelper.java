package com.personal.accountantAssistant.utils;

import android.view.Menu;

import io.reactivex.functions.Action;

public class MenuHelper {

    private static Menu mainMenu;

    private static final int add_option = 0;
    private static final int import_export_option = 1;
    private static final int delete_all_option = 2;
    private static final int restore_default_option = 3;

    private static boolean buysViewSelected = Boolean.FALSE;
    private static boolean billsViewSelected = Boolean.FALSE;

    public static Menu getMainMenu() {
        return mainMenu;
    }

    public static void setMainMenu(Menu mainMenu) {
        MenuHelper.mainMenu = mainMenu;
    }

    public static void initializeHomeOptions() {
        buysViewSelected = Boolean.FALSE;
        billsViewSelected = Boolean.FALSE;
        enableMenuItemOptions(Boolean.FALSE);
    }

    public static void initializeSummaryOptions() {
        buysViewSelected = Boolean.FALSE;
        billsViewSelected = Boolean.FALSE;
        enableMenuItemOptions(Boolean.FALSE);
    }

    public static void initializeBuysOptions() {
        buysViewSelected = Boolean.TRUE;
        billsViewSelected = Boolean.FALSE;
        enableMenuItemOptions(Boolean.TRUE);
    }

    public static void initializeBillsOptions() {
        buysViewSelected = Boolean.FALSE;
        billsViewSelected = Boolean.TRUE;
        enableMenuItemOptions(Boolean.TRUE);
    }

    public static void enableMenuItemOptions(final boolean enable) {
        setItemEnabled(add_option, enable);
        setItemEnabled(import_export_option, enable);
        setItemEnabled(delete_all_option, enable);
        setItemEnabled(restore_default_option, enable);
    }

    private static void setItemEnabled(final int itemIndex,
                                       final boolean enabled) {
        if (!ParserUtils.isNullObject(mainMenu)) {
            mainMenu.getItem(itemIndex)
                    .setVisible(enabled);
        }
    }

    public static void conditionalMenuItemClickListener(final Action buysAction,
                                                        final Action billsAction) {
        if (isBuysViewSelected()) {
            ActionUtils.runAction(buysAction);
        } else if (isBillsViewSelected()) {
            ActionUtils.runAction(billsAction);
        }
    }

    public static boolean isBuysViewSelected() {
        return buysViewSelected;
    }

    public static boolean isBillsViewSelected() {
        return billsViewSelected;
    }
}
