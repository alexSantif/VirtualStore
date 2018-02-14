package com.br.virtualstore.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.br.virtualstore.R;
import com.br.virtualstore.adapter.ProfissaoAdapter;
import com.br.virtualstore.entity.Profissao;
import com.br.virtualstore.entity.User;
import com.br.virtualstore.util.Constants;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.StringEntity;

/**
 * Created by Alex on 02/01/2018.
 */

public class FragmentPerfil extends Fragment {

    private Button btnCadastrar;
    private TextInputLayout lytTxtNome;
    private TextView txtMiniBio;
    private TextView txtEmail;
    private TextView txtNome;
    private RadioGroup rbgSexo;
    private RadioButton rbtMasc, rbtFem;

    private Spinner spnProfissao;

    private List<Profissao> profissoes;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_perfil, container, false);

        final Gson gson = new Gson();

        final RelativeLayout lytLoading = view.findViewById(R.id.lytLoading);
        lytLoading.setVisibility(View.VISIBLE);

        btnCadastrar = view.findViewById(R.id.btnCadastrar);
        txtNome = view.findViewById(R.id.txtNome);
        lytTxtNome = view.findViewById(R.id.lytTxtNome);
        spnProfissao = view.findViewById(R.id.spnProfissao);
        txtEmail = view.findViewById(R.id.txtEmail);
        txtMiniBio = view.findViewById(R.id.txtMinibio);

        rbgSexo = view.findViewById(R.id.groupSexo);
        rbtFem = view.findViewById(R.id.rbtFem);
        rbtMasc = view.findViewById(R.id.rbtMasc);

        spnProfissao = view.findViewById(R.id.spnProfissao);

        new AsyncHttpClient().get(Constants.URL_WS_BASE + "user/get_profissoes", new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {

                if (response != null) {
                    Type type = new TypeToken<List<Profissao>>() {
                    }.getType();
                    profissoes = gson.fromJson(response.toString(), type);

                    ProfissaoAdapter arrayAdapter = new ProfissaoAdapter(getActivity(), R.layout.linha_profissao, profissoes);
                    spnProfissao.setAdapter(arrayAdapter);
                } else {
                    Toast.makeText(getActivity(), "Houve um erro no carregamento da lista!", Toast.LENGTH_SHORT).show();
                }

                lytLoading.setVisibility(View.GONE);

            }
        });

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!validarNome()) {
                    return;
                }

                String json = gson.toJson(criarPessoa());

                try {
                    StringEntity stringEntity = new StringEntity(json);
                    new AsyncHttpClient().post(null, Constants.URL_WS_BASE + "user/add", stringEntity, "application_json", new JsonHttpResponseHandler() {
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
        pessoa.setEmail(txtEmail.getText().toString());
        pessoa.setMiniBio(txtMiniBio.getText().toString());
        pessoa.setSexo(rbtMasc.isChecked() ? 'M' : 'F');
        pessoa.setNome(txtNome.getText().toString());
        pessoa.setProfissao((Profissao) spnProfissao.getSelectedItem());

        return pessoa;
    }
}
