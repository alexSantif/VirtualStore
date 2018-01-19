package com.br.virtualstore.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.br.virtualstore.R;
import com.br.virtualstore.entity.User;
import com.br.virtualstore.util.Constants;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.StringEntity;

/**
 * Created by Alex on 02/01/2018.
 */

public class FragmentPerfil extends Fragment {

    private Button btnCadastrar;
    private TextInputLayout lytTxtNome;
    private TextView txtNome;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_perfil, container, false);

        btnCadastrar = view.findViewById(R.id.btnCadastrar);
        txtNome = view.findViewById(R.id.txtNome);
        lytTxtNome = view.findViewById(R.id.lytTxtNome);

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!validarNome()) {
                    return;
                }

                final Gson gson = new Gson();
                String json = gson.toJson(criarPessoa());

                try {
                    StringEntity stringEntity = new StringEntity(json);
                    new AsyncHttpClient().post(null, Constants.URL_WS_BASE + "user/teste", stringEntity, "application_json", new JsonHttpResponseHandler(){
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                            Log.e("response", response.toString());
                            gson.fromJson(response.toString(), User.class);
                        }
                    });
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }


            }
        });

        return view;
    }

    private boolean validarNome() {
        if (txtNome.getText().toString().trim().isEmpty()) {
            lytTxtNome.setError("Campo nome obrigatório");
            return false;
        } else {
            lytTxtNome.setErrorEnabled(false);
        }
        return true;
    }

    private User criarPessoa() {
        User pessoa = new User();
        pessoa.setCodProfissao(1);
        pessoa.setEmail("teste@teste.com");
        pessoa.setMiniBio("Teste MiniBio");
        pessoa.setSexo('M');
        pessoa.setNome("Teste");

        return pessoa;
    }
}
