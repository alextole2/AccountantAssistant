package com.personal.accountantAssistant.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.personal.accountantAssistant.R;
import com.personal.accountantAssistant.ui.payments.PaymentsListAdapter;
import com.personal.accountantAssistant.ui.payments.enums.PaymentsType;

import java.util.Objects;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class PaymentsFragmentsUtils {

    private Context context;
    private Activity activity;
    private PaymentsType paymentsType;

    private ImageView headerCardImage;
    private TextView headerCardSubTitle;
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    private Switch headerCardSwitch;

    private RecyclerView recyclerView;

    public PaymentsFragmentsUtils(final Context context,
                                  final Activity activity,
                                  final PaymentsType paymentsType) {
        this.context = context;
        this.activity = activity;
        this.paymentsType = paymentsType;
    }

    public Context getContext() {
        return context;
    }

    public void initializeVisualComponentsFrom(final View viewRoot) {
        final View headerCardTitlesBar = viewRoot.findViewById(R.id.header_card_titles_bar);

        //Title
        final TextView headerCardTitle = headerCardTitlesBar.findViewById(R.id.titles_bar_title);
        headerCardTitle.setVisibility(View.GONE);

        //Image
        headerCardImage = headerCardTitlesBar.findViewById(R.id.title_image);

        //Subtitle
        headerCardSubTitle = headerCardTitlesBar.findViewById(R.id.titles_bar_subtitle);
        headerCardSubTitle.setText(String.valueOf(Constants.DEFAULT_VALUE));

        //Switch
        headerCardSwitch = headerCardTitlesBar.findViewById(R.id.title_switch);
        headerCardSwitch.setOnClickListener(view -> {
            Objects.requireNonNull((PaymentsListAdapter) recyclerView.getAdapter())
                    .setAllPaymentsRecordsActiveFrom(headerCardSwitch.isChecked());
            updateRecyclerView();
        });

        if (PaymentsType.isBuy(paymentsType)) {
            recyclerView = viewRoot.findViewById(R.id.buy_list);
        }

        if (PaymentsType.isBill(paymentsType)) {
            recyclerView = viewRoot.findViewById(R.id.bills_list);
        }

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    public void updateRecyclerView() {

        if (PaymentsType.isBuy(paymentsType)) {
            MenuHelper.initializeBuysOptions();
        }

        if (PaymentsType.isBill(paymentsType)) {
            MenuHelper.initializeBillsOptions();
        }

        final PaymentsListAdapter paymentsListAdapter = new PaymentsListAdapter(context, paymentsType);
        final boolean anyActivePayments = DataBaseUtils.anyActivePaymentsRecordsBy(activity, paymentsType);

        headerCardImage.setImageResource(anyActivePayments ?
                R.drawable.ic_red_money :
                R.drawable.ic_menu_green_money);

        headerCardSubTitle.setTextSize(TypedValue.COMPLEX_UNIT_SP, 24);
        headerCardSubTitle.setTextColor(anyActivePayments ?
                context.getColor(R.color.colorAccent) :
                context.getColor(R.color.colorPrimary));

        headerCardSubTitle.setText(String.valueOf(paymentsListAdapter.getTotalPrice()));
        headerCardSwitch.setChecked(DataBaseUtils.allActivePaymentsRecordsBy(activity, paymentsType));

        recyclerView.setAdapter(paymentsListAdapter);
    }
}