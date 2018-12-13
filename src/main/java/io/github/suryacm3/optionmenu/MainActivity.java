package io.github.suryacm3.optionmenu;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class MainActivity extends AppCompatActivity implements MultiSelectSpinnerAdapter.MultipleSelectCheckBoxChangeListener {

    private MultiSelectSpinnerAdapter multiSelectSpinnerAdapter;
    private Spinner                   spinner;
    private TextView                  selectedItemsView;
    private Set<String>               selectedItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] items = getResources().getStringArray(R.array.value);
        selectedItems = new TreeSet<>();

        spinner = findViewById(R.id.spinner1);
        selectedItemsView = findViewById(R.id.textView);

        multiSelectSpinnerAdapter = new MultiSelectSpinnerAdapter(this, items, this);

        spinner.setAdapter(multiSelectSpinnerAdapter);

    }

    @Override
    public void onMultiSelectOptionSelected(int i, String selectedText, boolean isAdded) {
        if (isAdded) {
            selectedItems.add(selectedText);
        } else {
            if (selectedItems.contains(selectedText))
                selectedItems.remove(selectedText);
        }
        selectedItemsView.setText(join(selectedItems, "\n"));
    }

    public String join(Set<String> set, String sep) {
        String result = null;
        if (set != null) {
            StringBuilder sb = new StringBuilder();
            Iterator<String> it = set.iterator();
            if (it.hasNext()) {
                sb.append(it.next());
            }
            while (it.hasNext()) {
                sb.append(sep).append(it.next());
            }
            result = sb.toString();
        }
        return result;
    }
}
