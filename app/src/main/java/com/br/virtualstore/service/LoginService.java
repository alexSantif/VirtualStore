package com.br.virtualstore.service;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.br.virtualstore.MainActivity;
import com.br.virtualstore.async.AsyncUsuario;
import com.br.virtualstore.async.AsyncUsuarioHttpClient;
import com.br.virtualstore.util.Constants;
import com.br.virtualstore.util.Utils;
import com.br.virtualstore.validation.LoginValidation;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

import cz.msebera.android.httpclient.Header;

/**
 * Created by Alex on 23/11/2017.
 */

public class LoginService {

   // private LoginRepository loginRepository;

    public LoginService(Activity activity) {
 //       loginRepository = new LoginRepository(activity);
    }

    public void validarCamposLogin(final LoginValidation validation) {
        final Activity activity = validation.getActivity();
        boolean resultado = true;
        if (validation.getLogin() == null || "".equals(validation.getLogin())) {
            validation.getEdtLogin().setError("Campo obrigatório!");
            resultado = false;
        } else if (validation.getLogin().length() <= 2) {
            validation.getEdtLogin().setError("Campo deve ter no mínimo 3 caracteres!");
        }

        if (validation.getSenha() == null || "".equals(validation.getSenha())) {
            validation.getEdtSenha().setError("Campo obrigatório!");
            resultado = false;
        }

        if (resultado) {

            RequestParams params = new RequestParams();
            params.put("usuario", validation.getLogin());
            params.put("senha", validation.getSenha());

            AsyncUsuarioHttpClient.post("user/login", params, new TextHttpResponseHandler() {
                @Override
                public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                    Utils.showMsgSimpleToast(activity, "Houve um erro no login do usuário: " + throwable.getMessage());
                }

                @Override
                public void onSuccess(int statusCode, Header[] headers, String resultado) {
                    if (Boolean.valueOf(resultado)) {
                        SharedPreferences.Editor editor = activity.getSharedPreferences("pref", Context.MODE_PRIVATE).edit();
                        editor.putString("login", validation.getLogin());
                        editor.putString("senha", validation.getSenha());
                        editor.commit();

                        Intent intent = new Intent(activity, MainActivity.class);
                        activity.startActivity(intent);
                        activity.finish();
                    } else {
                        Utils.showMsgSimpleToast(activity, "Login/Senha inválidos");
                    }
                }
            });

         //   new AsyncUsuario(validation).execute(Constants.URL_WS_LOGIN);


        }

    }

    public void deslogar() {

    }

}
