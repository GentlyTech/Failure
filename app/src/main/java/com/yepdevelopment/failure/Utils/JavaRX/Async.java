package com.yepdevelopment.failure.Utils.JavaRX;

import android.annotation.SuppressLint;

import java.util.function.Consumer;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.disposables.Disposable;
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

    @SuppressLint("CheckResult")
    public static <T> void run(Single<T> single, Consumer<T> onSuccess) {
        if (single == null) return;
        single.subscribeOn(Schedulers.io()).subscribe(result -> { // FIXME may or may not cause a memory leak
            if (onSuccess == null) return;
            onSuccess.accept(result);
        });
    }
}
