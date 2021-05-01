package com.personal.accountantAssistant.ui.bills;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.personal.accountantAssistant.R;
import com.personal.accountantAssistant.ui.payments.enums.PaymentsType;
import com.personal.accountantAssistant.utils.PaymentsFragmentsUtils;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public class BillsFragment extends Fragment {

    private PaymentsFragmentsUtils paymentsFragmentsUtils;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        final View viewRoot = inflater.inflate(R.layout.fragment_bills, container, Boolean.FALSE);
        paymentsFragmentsUtils = new PaymentsFragmentsUtils(getContext(), requireActivity(), PaymentsType.BILL);
        paymentsFragmentsUtils.initializeVisualComponentsFrom(viewRoot);
        return viewRoot;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent resultData) {
        super.onActivityResult(requestCode, resultCode, resultData);
        paymentsFragmentsUtils.onDetailsActivityResult(requestCode, resultCode, resultData);
    }
}