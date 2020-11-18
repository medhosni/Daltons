/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.content.Context
 *  android.content.Intent
 *  android.os.Bundle
 *  android.support.v7.app.AppCompatActivity
 *  android.util.Log
 *  android.view.View
 *  com.facebook.AccessToken
 *  com.facebook.CallbackManager
 *  com.facebook.CallbackManager$Factory
 *  com.facebook.FacebookCallback
 *  com.facebook.FacebookException
 *  com.facebook.FacebookSdk
 *  com.facebook.GraphRequest
 *  com.facebook.GraphRequest$GraphJSONObjectCallback
 *  com.facebook.GraphRequestAsyncTask
 *  com.facebook.GraphResponse
 *  com.facebook.appevents.AppEventsLogger
 *  com.facebook.login.LoginResult
 *  com.facebook.login.widget.LoginButton
 *  com.linkedin.platform.LISessionManager
 *  com.linkedin.platform.errors.LIAuthError
 *  com.linkedin.platform.utils.Scope
 *  com.linkedin.platform.utils.Scope$LIPermission
 *  java.lang.Class
 *  java.lang.Object
 *  java.lang.String
 *  org.json.JSONException
 *  org.json.JSONObject
 */
package com.gen.souhaikr.daltons;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphRequestAsyncTask;
import com.facebook.GraphResponse;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.gen.souhaikr.daltons.HomeActivity;
import com.linkedin.platform.LISessionManager;
import com.linkedin.platform.errors.LIAuthError;
import com.linkedin.platform.listeners.AuthListener;
import com.linkedin.platform.utils.Scope;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity
extends AppCompatActivity {
    private CallbackManager callbackManager;
    String email;
    String first_name;
    String last_name;
    LoginButton loginButton;
    private AccessToken mAccessToken;

    private static Scope buildScope() {
        Scope.LIPermission[] arrlIPermission = new Scope.LIPermission[]{Scope.R_BASICPROFILE, Scope.W_SHARE};
        return Scope.build((Scope.LIPermission[])arrlIPermission);
    }

    private void getUserProfile(final AccessToken accessToken) {
        GraphRequest graphRequest = GraphRequest.newMeRequest((AccessToken)accessToken, (GraphRequest.GraphJSONObjectCallback)new GraphRequest.GraphJSONObjectCallback(){

            public void onCompleted(JSONObject jSONObject, GraphResponse graphResponse) {
                try {
                    jSONObject.getJSONObject("picture").getJSONObject("data").getString("url");
                    MainActivity.this.first_name = jSONObject.getString("first_name");
                    MainActivity.this.last_name = jSONObject.getString("last_name");
                    MainActivity.this.email = jSONObject.getString("email");
                    jSONObject.getString("id");
                    Log.i((String)"aaaaaaaaaaa", (String)String.valueOf((Object)jSONObject));
                    Intent intent = new Intent(MainActivity.this.getApplicationContext(), HomeActivity.class);
                    intent.putExtra("first_name", MainActivity.this.first_name);
                    intent.putExtra("user_id", accessToken.getUserId());
                    MainActivity.this.startActivity(intent);
                    return;
                }
                catch (JSONException jSONException) {
                    jSONException.printStackTrace();
                    return;
                }
            }
        });
        Bundle bundle = new Bundle();
        bundle.putString("fields", "id,name,email,picture.width(200),first_name,last_name");
        graphRequest.setParameters(bundle);
        graphRequest.executeAsync();
    }

    protected void onActivityResult(int n, int n2, Intent intent) {
        super.onActivityResult(n, n2, intent);
        this.callbackManager.onActivityResult(n, n2, intent);
        LISessionManager.getInstance((Context)this.getApplicationContext()).onActivityResult((Activity)this, n, n2, intent);
    }

    protected void onCreate(Bundle bundle) {
        this.setTheme(2131558405);
        super.onCreate(bundle);
        FacebookSdk.sdkInitialize((Context)this.getApplicationContext());
        AppEventsLogger.activateApp((Context)this);
        this.setContentView(2131296287);
        this.callbackManager = CallbackManager.Factory.create();
        this.loginButton = (LoginButton)this.findViewById(2131165308);
        this.loginButton.setReadPermissions(new String[]{"public_profile"});
        this.loginButton.setReadPermissions(new String[]{"email"});
        this.loginButton.registerCallback(this.callbackManager, (FacebookCallback)new FacebookCallback<LoginResult>(){

            public void onCancel() {
            }

            public void onError(FacebookException facebookException) {
            }

            public void onSuccess(LoginResult loginResult) {
                MainActivity.this.mAccessToken = loginResult.getAccessToken();
                loginResult.getAccessToken().getUserId();
                MainActivity.this.getUserProfile(MainActivity.this.mAccessToken);
            }
        });
    }

    public void signInWithLinkedIn(View view) {
        LISessionManager.getInstance((Context)this.getApplicationContext()).init((Activity)this, MainActivity.buildScope(), new AuthListener(){

            @Override
            public void onAuthError(LIAuthError lIAuthError) {
            }

            @Override
            public void onAuthSuccess() {
            }
        }, true);
    }

}

