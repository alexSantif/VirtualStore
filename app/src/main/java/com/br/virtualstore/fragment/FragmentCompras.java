package com.br.virtualstore.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.br.virtualstore.R;
import com.br.virtualstore.adapter.ProdutoRecyclerAdapter;
import com.br.virtualstore.entity.Produto;
import com.br.virtualstore.util.Constants;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;

import java.lang.reflect.Type;
import java.util.List;

import cz.msebera.android.httpclient.Header;
import it.gmariotti.cardslib.library.internal.Card;
import it.gmariotti.cardslib.library.internal.CardHeader;
import it.gmariotti.cardslib.library.internal.base.BaseCard;
import it.gmariotti.cardslib.library.view.CardViewNative;

/**
 * Created by Alex on 02/01/2018.
 */

public class FragmentCompras extends Fragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View viewRoot = inflater.inflate(R.layout.fragment_compras, container, false);

        final RelativeLayout lytLoading = viewRoot.findViewById(R.id.lytLoading);
        lytLoading.setVisibility(View.VISIBLE);

        Card card = new Card(getContext());

        CardHeader header = new CardHeader(getContext());
        header.setTitle("Mochila Mickey");

        header.setPopupMenu(R.menu.menu_main, new CardHeader.OnClickCardHeaderPopupMenuListener(){
            @Override
            public void onMenuItemClick(BaseCard card, MenuItem item) {
                Toast.makeText(getActivity(), "Click on "+item.getTitle(), Toast.LENGTH_SHORT).show();
            }
        });

        header.setPopupMenuPrepareListener(new CardHeader.OnPrepareCardHeaderPopupMenuListener() {
            @Override
            public boolean onPreparePopupMenu(BaseCard card, PopupMenu popupMenu) {
                popupMenu.getMenu().add("Refazer a compra");
                return true;
            }
        });

        card.addCardHeader(header);

        CardViewNative cardView = (CardViewNative) viewRoot.findViewById(R.id.carddemo);
        cardView.setCard(card);

        new AsyncHttpClient().get(Constants.URL_WS_BASE + "produto/list", new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {

                lytLoading.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Toast.makeText(getActivity(), "Falha: " + responseString, Toast.LENGTH_SHORT).show();
            }
        });

        return viewRoot;
    }
}
