package com.br.virtualstore.firebase;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import static android.content.ContentValues.TAG;

/**
 * Created by Alex on 05/03/2018.
 */

public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService {

  /*  @Override
    public void onTokenRefresh() {
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG,"Refreshed token: "+refreshedToken);

       // sendRegistrationToServer(refreshedToken);
    }*/

}
