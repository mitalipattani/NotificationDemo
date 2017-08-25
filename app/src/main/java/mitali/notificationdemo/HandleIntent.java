package mitali.notificationdemo;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;

/**
 * Created by CICCC-CIRAC on 2017-08-25.
 */

public class HandleIntent extends IntentService {
    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public HandleIntent() {
        super("HandleIntent");
    }

    @Override
    protected void onHandleIntent( Intent intent) {
        String action = intent.getAction();
        HandleAction.executeTask(this, action);
    }
}
