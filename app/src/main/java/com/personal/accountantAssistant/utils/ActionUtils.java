package com.personal.accountantAssistant.utils;

import io.reactivex.functions.Action;

public class ActionUtils {

    public static Action NONE_ACTION_TO_DO = () -> {
    };

    static void runAction(final Action action) {
        try {
            action.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void conditionalActions(final boolean condition,
                                          final Action trueAction,
                                          final Action falseAction) {
        if (condition) {
            runAction(trueAction);
        } else {
            runAction(falseAction);
        }
    }
}
