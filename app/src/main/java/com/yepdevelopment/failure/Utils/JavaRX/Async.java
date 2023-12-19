package com.yepdevelopment.failure.Utils.JavaRX;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class Async {
    /**
     * This class should not be instantiated.
     */
    private Async() {

    }

    public static void run(Completable completable) {
        if (completable == null) return;
        Completable.fromRunnable(completable::subscribe).subscribeOn(Schedulers.io()).subscribe();
    }
}
