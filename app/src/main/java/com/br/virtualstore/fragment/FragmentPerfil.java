package com.br.virtualstore.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.br.virtualstore.R;

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
}
