package com.br.virtualstore.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.br.virtualstore.R;
import com.br.virtualstore.async.AsyncImageHelper;
import com.br.virtualstore.entity.Profissao;
import com.br.virtualstore.util.Constants;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Alex on 21/01/2018.
 */

public class ProfissaoAdapter extends ArrayAdapter<Profissao> {

    private List<Profissao> profissoes;
    private Context context;

    public ProfissaoAdapter(@NonNull Context context, int resource, List<Profissao> profissoes) {
        super(context, resource);
        this.context = context;
        this.profissoes = profissoes;
    }

    @Override
    public int getCount() {
        return profissoes.size();
    }

    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return getViewAux(position, parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return getViewAux(position, parent);
    }

    @Nullable
    @Override
    public Profissao getItem(int position) {
        return profissoes.get(position);
    }

    @NonNull
    private View getViewAux(int position, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View linha = layoutInflater.inflate(R.layout.linha_profissao, parent, false);

        Profissao profissao = profissoes.get(position);

        TextView txtProfissao = linha.findViewById(R.id.txtProfissao);
        txtProfissao.setText(profissao.getDescricao());

        TextView txtDescricao = linha.findViewById(R.id.txtDescricao);
        txtDescricao.setText(profissao.getSubDescricao());

        ImageView imgProfissao = linha.findViewById(R.id.imgProfissao);
        Picasso.with(imgProfissao.getContext()).load(Constants.URL_WEB_BASE + profissao.getUrlImg()).into(imgProfissao);
      //  new AsyncImageHelper(imgProfissao).execute(Constants.URL_WEB_BASE + profissao.getUrlImg());

        return linha;
    }
}
