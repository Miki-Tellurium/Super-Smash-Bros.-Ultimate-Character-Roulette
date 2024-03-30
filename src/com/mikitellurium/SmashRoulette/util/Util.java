package com.mikitellurium.SmashRoulette.util;

import java.util.function.Consumer;

public class Util {

    public static <T> T make(T obj, Consumer<T> consumer) {
        consumer.accept(obj);
        return obj;
    }

}
