package me.mssodhi.myapplication.controllers;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONObject;

import me.mssodhi.myapplication.R;
import me.mssodhi.myapplication.domain.User;

public class LoginController extends Activity {
    private CallbackManager callbackManager;
    private LoginButton loginButton;
    private TextView btnLogin;
    AccessToken accessToken;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.login);
        accessToken = AccessToken.getCurrentAccessToken();
        String parameter = "";
        // Retrieve the current user from loginActivity
        Intent intent = getIntent();
        if(intent.getExtras() != null){
            parameter = intent.getExtras().getString("request");
            if(parameter.equals("logout")){
                try {
                    LoginManager.getInstance().logOut();
                } catch (Throwable e) {
                    e.printStackTrace();
                }
            }
        }

        if(accessToken != null && !parameter.equals("logout")){
            GraphRequest request = GraphRequest.newMeRequest(accessToken, new GraphRequest.GraphJSONObjectCallback() {
                @Override
                public void onCompleted(JSONObject object, GraphResponse response) {
                    Log.e("response: ", response + "");
                    try {
                        user = new User();
                        user.setFacebookID(object.getString("id"));
                        user.setEmail(object.getString("email"));
                        user.setName(object.getString("name"));

                    }catch (Exception e){
                        e.printStackTrace();
                    }

                    // Package up the currentUser for landingActivity
                    Intent i = new Intent(LoginController.this, Landing.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("currentUser", user);
                    i.putExtras(bundle);
                    startActivity(i);
                }
            });

            Bundle parameters = new Bundle();
            parameters.putString("fields", "id,name,email");
            request.setParameters(parameters);
            request.executeAsync();
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        callbackManager=CallbackManager.Factory.create();
        loginButton= (LoginButton)findViewById(R.id.login_button);
        loginButton.setReadPermissions("public_profile", "email", "user_friends");
        btnLogin= (TextView) findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginButton.performClick();
                loginButton.setPressed(true);
                loginButton.invalidate();
                loginButton.registerCallback(callbackManager, mCallBack);
                loginButton.setPressed(false);
                loginButton.invalidate();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    private FacebookCallback<LoginResult> mCallBack = new FacebookCallback<LoginResult>() {

        @Override
        public void onSuccess(LoginResult loginResult) {
            accessToken = AccessToken.getCurrentAccessToken();
            GraphRequest request = GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                        @Override
                        public void onCompleted(JSONObject object, GraphResponse response) {

                            Log.e("response: ", response + "");
                            try {
                                user = new User();
                                user.setFacebookID(object.getString("id"));
                                user.setEmail(object.getString("email"));
                                user.setName(object.getString("name"));

                            }catch (Exception e){
                                e.printStackTrace();
                            }

                            Toast.makeText(LoginController.this,"welcome "+ user.getName(),Toast.LENGTH_LONG).show();

                            // Package up the currentUser for landingActivity
                            Intent i = new Intent(LoginController.this, Landing.class);
                            Bundle bundle = new Bundle();
                            bundle.putSerializable("currentUser", user);
                            i.putExtras(bundle);
                            startActivity(i);

                        }

                    });

            Bundle parameters = new Bundle();
            parameters.putString("fields", "id,name,email,gender, birthday");
            request.setParameters(parameters);
            request.executeAsync();

        }

        @Override
        public void onCancel() {
//            progressDialog.dismiss();
        }

        @Override
        public void onError(FacebookException e) {
//            progressDialog.dismiss();
        }
    };

}