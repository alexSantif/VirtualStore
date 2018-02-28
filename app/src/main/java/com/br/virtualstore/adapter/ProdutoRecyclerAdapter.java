package com.br.virtualstore.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.br.virtualstore.R;
import com.br.virtualstore.entity.Produto;
import com.br.virtualstore.util.Constants;
import com.squareup.picasso.Picasso;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

/**
 * Created by Alex on 15/02/2018.
 */

public class ProdutoRecyclerAdapter extends RecyclerView.Adapter<ProdutoRecyclerAdapter.ViewHolder> {

    private final List<Produto> produtos;

    public ProdutoRecyclerAdapter(final List<Produto> produtos) {
        this.produtos = produtos;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imgProduto;
        TextView txtTitulo;
        TextView txtDescricao;
        TextView txtPreco;
        Button btnDetalhes;

        public ViewHolder(final View itemView) {
            super(itemView);
            imgProduto = (ImageView) itemView.findViewById(R.id.imgProduto);
            txtTitulo = (TextView) itemView.findViewById(R.id.txtTitulo);
            txtDescricao = (TextView) itemView.findViewById(R.id.txtDescricao);
            txtPreco = (TextView) itemView.findViewById(R.id.txtPreco);
            btnDetalhes = (Button) itemView.findViewById(R.id.btnDetalhes);
            btnDetalhes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(itemView.getContext(), "Card Clicado!", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    @Override
    public ProdutoRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.linha_produto, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ProdutoRecyclerAdapter.ViewHolder produtoViewHolder, int position) {
        Produto produto = produtos.get(position);
        produtoViewHolder.txtTitulo.setText(produto.getTitulo());
        produtoViewHolder.txtDescricao.setText(produto.getDescricao());
        NumberFormat format = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));

        produtoViewHolder.txtPreco.setText(format.format(produto.getValor()));
        Picasso.with(produtoViewHolder.imgProduto.getContext())
                .load(Constants.URL_WEB_BASE + produto.getUrlImg())
                .into(produtoViewHolder.imgProduto);
    }

    @Override
    public int getItemCount() {
        return produtos.size();
    }
}
