package com.br.virtualstore.async;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.view.accessibility.AccessibilityRecord;
import android.widget.Toast;

import com.br.virtualstore.LoginActivity;
import com.br.virtualstore.MainActivity;
import com.br.virtualstore.util.Utils;
import com.br.virtualstore.validation.LoginValidation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Alex on 04/01/2018.
 */

public class AsyncUsuario extends AsyncTask<String, String, String> {

    private LoginValidation loginValidation;

    private Activity activity;

    public AsyncUsuario(LoginValidation loginValidation) {
        this.loginValidation = loginValidation;
        this.activity = loginValidation.getActivity();
    }

    @Override
    protected String doInBackground(String... url) {
        StringBuilder resultado = new StringBuilder("");
        try {
            String path = url[0];
            path += "?usuario=" + loginValidation.getLogin() + "&senha=" + loginValidation.getSenha();

            URL urlNet = new URL(path);
            HttpURLConnection con = (HttpURLConnection) urlNet.openConnection();
            con.setRequestMethod("POST");
            con.setDoInput(true);
            con.connect();

            InputStream inputStream = con.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            String linha = "";
            while ((linha = bufferedReader.readLine()) != null){
                resultado.append(linha);
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resultado.toString();
    }

    @Override
    protected void onPostExecute(String resultado) {
        if (Boolean.valueOf(resultado)) {
            SharedPreferences.Editor editor = activity.getSharedPreferences("pref", Context.MODE_PRIVATE).edit();
            editor.putString("login", loginValidation.getLogin());
            editor.putString("senha", loginValidation.getSenha());
            editor.commit();

                Intent intent = new Intent(activity, MainActivity.class);
                activity.startActivity(intent);
                activity.finish();
        } else {
            Utils.showMsgToast(activity, "Login/Senha inválidos!");
        }
    }
}
