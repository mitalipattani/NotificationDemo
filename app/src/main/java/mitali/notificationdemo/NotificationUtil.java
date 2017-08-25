package mitali.notificationdemo;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;
import android.app.Notification;
/**
 * Created by CICCC-CIRAC on 2017-08-25.
 */

public class NotificationUtil {

    //Notification ID used to access our notification
    //after we displayed it. This cane be handy when
    //we need to cancel the notification or update
    //number is arbitary and can be set to anything.
    private static final int REMINDER_NOTIFICATION_ID = 1138;
    private static final int REMINDER_PENDING_INTENT_ID = 3417;
    //helper methods called contentIntent with a single parameter
    //context. It should return a pending intent.This method
    // will crearte a pending intent which trigger when
    // notification is pressed. This pending intent should
    //open up the mainactivity

    private static PendingIntent contentIntent (Context context)
    {
        //create an intent that opens MainActivity
        Intent startActivityIntent = new
                Intent(context, mitali.notificationdemo.Notification.class);
        //Create a pending intent using
        //getactivity that will:
        //take the context passed as paraameter
        //takes a ID for pending intent
        //takes intent created to open MAinActivity
        //when the notification is trigrred
        //-has the flag FLAG_UPDATE_CURRENT, so that
        //if intent is created again keep the intent
        //but update the data
        return PendingIntent.getActivity(
                context,
                REMINDER_PENDING_INTENT_ID,
                startActivityIntent,
                PendingIntent.FLAG_UPDATE_CURRENT
        );
    }
    //  Create a helper method called largeIcon which takes in a Context as a parameter and
    // returns a Bitmap. This method is necessary to decode a bitmap needed for the notification.
    private static Bitmap largeIcon(Context context) {
        //  Get a Resources object from the context.
        Resources res = context.getResources();
        //  Create and return a bitmap using BitmapFactory.decodeResource, passing in the
        // resources object and R.drawable.ic_local_drink_black_24px
        Bitmap largeIcon = BitmapFactory.decodeResource(res, R.mipmap.ic_drink);
        return largeIcon;
    }
    //lets start building the notification
    public static void reminderUser(Context context)
    {
        //NotificationCompact.Builder to create it
        // has color of R.colorPrimary
        // has small icon
        // has title (a string resource)
        // has content (summary again fom string resource)
        // has style e.g. bigText(), template
        // has defaults i.e. vibrate
        // uses the contentIntent
        // automatically cancel the notification when
        // user click it
        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(context)
                .setColor(ContextCompat.getColor(context, R.color.colorPrimary))
                .setSmallIcon(R.mipmap.ic_drink)
                .setLargeIcon(largeIcon(context))
                .setContentTitle(context.getString(R.string.title))
                .setContentText(context.getString(R.string.content))
                .setStyle(new NotificationCompat.BigTextStyle().bigText(
                    context.getString(R.string.content)))
                .setDefaults(Notification.DEFAULT_VIBRATE)
                .setContentIntent(contentIntent(context))
                .setAutoCancel(true);
        //If build version is greater than JELLY BEAN
        //than set priority
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN)
        {
            notificationBuilder.setPriority(Notification.PRIORITY_HIGH);
        }
        //get NotificationManager ,using
        //context.getSystemService(Context.NOTIFICATION_SERVICE)
        NotificationManager notificationManager =
                (NotificationManager)context
                .getSystemService(Context.NOTIFICATION_SERVICE);

        //Trigger the notification by calling notify
        //on the NotificationManager
        //pass the ID value and build()
        notificationManager.notify(REMINDER_NOTIFICATION_ID,
                notificationBuilder.build());
    }
}
