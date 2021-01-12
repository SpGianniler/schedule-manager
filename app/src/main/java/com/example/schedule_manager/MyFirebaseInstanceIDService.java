/**
 * η κλάση αυτή χρησιμοποίειται για να δώσει ενα μοναδικό token σε μια νέα συσκευή
 * στην οποία έχει εγκατασταθεί η εφαρμογή έτσι ώστε ναστέλνονται και σε αυτή οι ειδοποίησεις
 */
package com.example.schedule_manager;

import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;

public class MyFirebaseInstanceIDService extends FirebaseMessagingService {
    @Override
    public void onNewToken(String s) {
        super.onNewToken(s);
        Log.d("NEW_TOKEN",s);
    }

}
