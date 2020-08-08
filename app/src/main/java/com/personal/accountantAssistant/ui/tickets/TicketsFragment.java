package com.personal.accountantAssistant.ui.tickets;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.personal.accountantAssistant.R;
import com.personal.accountantAssistant.utils.MenuHelper;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public class TicketsFragment extends Fragment {

    private Context context;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        context = getContext();
        MenuHelper.initializeHomeOptions();
        return inflater.inflate(R.layout.fragment_tickets, container, Boolean.FALSE);
    }
}