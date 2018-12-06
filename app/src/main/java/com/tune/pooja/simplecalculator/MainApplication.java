package com.tune.pooja.simplecalculator;


import com.tune.Tune;
import com.tune.application.TuneActivityLifecycleCallbacks;
import com.tune.application.TuneApplication;

public class MainApplication extends TuneApplication {
    @Override
    public void onCreate() {
        super.onCreate();

        Tune.setDebugMode(true);
        // Initialize TMC
        Tune.init(this, "199221", "3db67ee526471081c7bf30d7d02a241c");

        // Make sure you register this AFTER Tune.init
        registerActivityLifecycleCallbacks(new TuneActivityLifecycleCallbacks());
    }
}
