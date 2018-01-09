package com.br.virtualstore.service;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.br.virtualstore.async.AsyncUsuario;
import com.br.virtualstore.util.Constants;
import com.br.virtualstore.validation.LoginValidation;

/**
 * Created by Alex on 23/11/2017.
 */

public class LoginService {

   // private LoginRepository loginRepository;

    public LoginService(Activity activity) {
 //       loginRepository = new LoginRepository(activity);
    }

    public void validarCamposLogin(LoginValidation validation) {
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

            new AsyncUsuario(validation).execute(Constants.URL_WS_LOGIN);


        }

    }

    public void deslogar() {

    }

}
