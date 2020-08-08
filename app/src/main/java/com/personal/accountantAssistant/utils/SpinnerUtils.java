package com.personal.accountantAssistant.utils;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.personal.accountantAssistant.R;

import java.util.concurrent.atomic.AtomicInteger;

public class SpinnerUtils {

    public static int fill(final Context context,
                           final int defaultValue,
                           final Spinner selectorObject) {
        final AtomicInteger selectedValue = new AtomicInteger(defaultValue);
        final ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(context,
                R.array.quantity_values,
                android.R.layout.simple_spinner_item);
        selectorObject.setAdapter(arrayAdapter);
        selectorObject.setSelection(selectedValue.get());
        selectorObject.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                final Object selectedItem = parent.getItemAtPosition(position);
                if (!ParserUtils.isNullObject(selectedItem)) {
                    selectedValue.set(Integer.parseInt(selectedItem.toString()));
                } else {
                    selectedValue.set(defaultValue);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        return selectedValue.get();
    }
}
