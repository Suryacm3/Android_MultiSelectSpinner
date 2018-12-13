package io.github.suryacm3.optionmenu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;


public class MultiSelectSpinnerAdapter extends BaseAdapter {

    String[] items;
    Context  context;
    boolean[] selected;
    MultipleSelectCheckBoxChangeListener listener;


    MultiSelectSpinnerAdapter(Context context, String[] items, MultipleSelectCheckBoxChangeListener listener) {
        this.items = items;
        selected = new boolean[items.length];
        this.context = context;
        this.listener = listener;
    }

    @Override
    public int getCount() {
        return items.length;
    }

    @Override
    public Object getItem(int i) {
        return items[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        return getCustomView(i);
    }

    @Override
    public View getDropDownView(int i, View convertView, ViewGroup parent) {
        return getCustomView(i);
    }

    private View getCustomView(final int i) {
        View view1 = LayoutInflater.from(context).inflate(R.layout.single_dropdown_item, null);
        TextView spinnerText = view1.findViewById(R.id.dropdown_text);
        CheckBox spinnerSelection = view1.findViewById(R.id.dropdown_checkbox);

        spinnerSelection.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                selected[i] = b;
                    listener.onMultiSelectOptionSelected(i, items[i], b);
            }
        });

        spinnerSelection.setChecked(selected[i]);

        spinnerText.setText(items[i]);

        return view1;
    }

    interface MultipleSelectCheckBoxChangeListener{
        void onMultiSelectOptionSelected(int i, String selectedText, boolean isAdded);
    }
}
