package com.personal.accountantAssistant.ui.buys;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.personal.accountantAssistant.R;
import com.personal.accountantAssistant.db.DatabaseManager;
import com.personal.accountantAssistant.ui.payments.PaymentsListAdapter;
import com.personal.accountantAssistant.ui.payments.enums.PaymentsType;
import com.personal.accountantAssistant.utils.Constants;
import com.personal.accountantAssistant.utils.MenuHelper;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class BuysFragment extends Fragment {

    private Context context;
    private TextView headerCardSubTitle;
    private RecyclerView recyclerView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        context = getContext();

        View viewRoot = inflater.inflate(R.layout.fragment_buys, container, Boolean.FALSE);

        final View headerCardTitlesBar = viewRoot.findViewById(R.id.header_card_titles_bar);
        final TextView headerCardTitle = headerCardTitlesBar.findViewById(R.id.titles_bar_title);
        headerCardTitle.setText(R.string.total_to_pay);
        headerCardSubTitle = headerCardTitlesBar.findViewById(R.id.titles_bar_subtitle);
        headerCardSubTitle.setText(String.valueOf(Constants.DEFAULT_VALUE));

        recyclerView = viewRoot.findViewById(R.id.buy_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));

        updateRecyclerView();

        return viewRoot;
    }

    @Override
    public void onStart() {
        super.onStart();
        updateRecyclerView();
    }

    @Override
    public void onResume() {
        super.onResume();
        updateRecyclerView();
    }

    private void updateRecyclerView() {
        MenuHelper.initializeBuysOptions();
        final PaymentsListAdapter buysListAdapter = new PaymentsListAdapter(context, PaymentsType.BUY);
        headerCardSubTitle.setText(String.valueOf(buysListAdapter.getTotalPrice()));
        recyclerView.setAdapter(buysListAdapter);
    }
}