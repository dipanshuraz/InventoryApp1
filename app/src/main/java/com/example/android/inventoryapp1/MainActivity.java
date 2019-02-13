package com.example.android.inventoryapp1;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.android.inventoryapp1.data.Contract;
import com.example.android.inventoryapp1.data.DbHelper;
import com.example.android.inventoryapp1.data.Contract;

public class MainActivity extends AppCompatActivity {

    private DbHelper shipDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertData();
            }
        });

        shipDbHelper = new DbHelper(this);
        readDisplayData();
    }

    private void readDisplayData() {


        SQLiteDatabase db = shipDbHelper.getReadableDatabase();


        String[] projection = {
                BaseColumns._ID, Contract.Entry.COLUMN_SHIP_NAME, Contract.Entry.COLUMN_SHIP_PRICE,
                Contract.Entry.COLUMN_SHIP_QUANTITY, Contract.Entry.COLUMN_SHIP_SUPPLIER,
                Contract.Entry.COLUMN_SHIP_PHONE
        };
        Cursor cursor = db.query(
                Contract.Entry.TABLE_NAME,
                projection,
                null, null, null, null, null);

        TextView displayInfo = findViewById(R.id.display);

        try {

            displayInfo.setText("This Spacestation contains " + cursor.getCount() + " starships.\n\n");
            displayInfo.append(Contract.Entry._ID + " - " +
                    Contract.Entry.COLUMN_SHIP_NAME + " - " +
                    Contract.Entry.COLUMN_SHIP_PRICE + " - " +
                    Contract.Entry.COLUMN_SHIP_QUANTITY + " - " +
                    Contract.Entry.COLUMN_SHIP_SUPPLIER + " - " +
                    Contract.Entry.COLUMN_SHIP_PHONE + "\n");


            int idColumnIndex = cursor.getColumnIndex(Contract.Entry._ID);
            int nameColumnIndex = cursor.getColumnIndex(Contract.Entry.COLUMN_SHIP_NAME);
            int priceColumnIndex = cursor.getColumnIndex(Contract.Entry.COLUMN_SHIP_PRICE);
            int quantityColumnIndex = cursor.getColumnIndex(Contract.Entry.COLUMN_SHIP_QUANTITY);
            int supplierColumnIndex = cursor.getColumnIndex(Contract.Entry.COLUMN_SHIP_SUPPLIER);
            int phoneColumnIndex = cursor.getColumnIndex(Contract.Entry.COLUMN_SHIP_PHONE);


            while (cursor.moveToNext()) {




                int currentID = cursor.getInt(idColumnIndex);
                int shipName = cursor.getInt(nameColumnIndex);
                int price = cursor.getInt(priceColumnIndex);
                int quantity = cursor.getInt(quantityColumnIndex);
                String supplier = cursor.getString(supplierColumnIndex);
                String phone = cursor.getString(phoneColumnIndex);


                displayInfo.append("\n" + currentID + " - " +
                        shipName + " - " +
                        price + " - " +
                        quantity + " - " +
                        supplier + " - " +
                        phone);
            }

        } finally {
            cursor.close();
        }
    }

    private void insertData() {

        SQLiteDatabase db = shipDbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Contract.Entry.COLUMN_SHIP_NAME, Contract.Entry.STARSHIP_ROMULAN_WARBIRD);
        values.put(Contract.Entry.COLUMN_SHIP_PRICE, 65431);
        values.put(Contract.Entry.COLUMN_SHIP_QUANTITY, 2);
        values.put(Contract.Entry.COLUMN_SHIP_SUPPLIER, "Deepanshu");
        values.put(Contract.Entry.COLUMN_SHIP_PHONE, "8299379285");

        long newRowId = db.insert(Contract.Entry.TABLE_NAME, Contract.Entry.NULL, values);
        Log.v("MainActivity", "New Row ID" + newRowId);
        
        readDisplayData();
    }
}
