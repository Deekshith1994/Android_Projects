package com.example.kdeek.inclass10;

/**
 * Created by kdeek on 11/7/2016.
 */
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by saisr on 11/7/2016.
 */
public class ExpenseListAdapter extends ArrayAdapter<Expense> {
    ExpenseApp eApp = new ExpenseApp();
    Context context = null;
    List<Expense> expenseList;
    int resource;
    Context contextW = null;


    public ExpenseListAdapter(Context context, int resource, List<Expense> objects) {
        super(context, resource, objects);
        this.contextW = context;
        this.expenseList=objects;
        this.context = context;
        this.resource=resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(resource, parent, false);
        }
        Expense expense = expenseList.get(position);
        TextView expN = (TextView) convertView.findViewById(R.id.expName);
        expN.setText(expense.expenseName+"");

        TextView expP = (TextView) convertView.findViewById(R.id.price);
        expP.setText ("$ "+expense.amount);

        return convertView;


    }
}