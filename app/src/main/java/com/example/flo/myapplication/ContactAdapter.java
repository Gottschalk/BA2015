package com.example.flo.myapplication;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;


public class ContactAdapter extends ArrayAdapter<Contact> {
    Context context;
    int layoutResourceId;
    ArrayList<Contact> contact_data = null;

    public ContactAdapter(Context context, int layoutResourceId, ArrayList<Contact> contact_data) {
        super(context, layoutResourceId, contact_data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.contact_data = contact_data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ContactHolder holder = null;

        if (row == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);

            holder = new ContactHolder();
            holder.contactName = (TextView) row.findViewById(R.id.contactName);
            holder.contactNumber = (TextView) row.findViewById(R.id.contactNumber);
            row.setTag(holder);
        } else {
            holder = (ContactHolder) row.getTag();
        }

        Contact contact = contact_data.get(position);
        // set text in listview
        holder.contactName.setText(contact.getName());
        holder.contactNumber.setText(contact.getNumber());

        return row;
    }

    static class ContactHolder {
        TextView contactName;
        TextView contactNumber;
    }
}
