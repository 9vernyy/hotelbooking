package kz.alisher.hotel;

import android.app.Application;

import com.parse.Parse;

/**
 * Created by Alisher Kozhabay on 5/15/2016.
 */
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Parse.initialize(this, "IsOVjgzGzeFl7VMzGodvSMO5RFp63hvdcymkZMEX", "WYWrWkuMrG9fO09euwBjxIHQywIKzrv8SeaaiYs8");
    }
}
