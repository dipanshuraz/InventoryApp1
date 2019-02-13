package com.example.android.inventoryapp1.data;

import android.provider.BaseColumns;

public final class Contract {

    public static abstract class Entry implements BaseColumns {

        public static final String TABLE_NAME = "starships";

        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_SHIP_NAME = "starship_Name";
        public static final String COLUMN_SHIP_PRICE = "price";
        public static final String COLUMN_SHIP_QUANTITY = "quantity";
        public static final String COLUMN_SHIP_SUPPLIER = "supplier";
        public static final String COLUMN_SHIP_PHONE = "supplier_Phone";

        public static final int STARSHIP_ROMULAN_WARBIRD = 0;

        public static final String TEXT_TYPE = " TEXT";
        public static final String INTERGER_TYPE = " INTEGER";
        public static final String PKA = " PRIMARY KEY AUTOINCREMENT";
        public static final String DEFAULT = " DEFAULT";
        public static final String NULL = " NULL";
        public static final String NOT_NULL = " NOT NULL";
        public static final String COMMA_SEP = ",";

        public static final String SQL_CREATE_ENTRIES = "CREATE TABLE " + Entry.TABLE_NAME + " (" +
                Entry._ID + INTERGER_TYPE + PKA + COMMA_SEP + Entry.COLUMN_SHIP_NAME + INTERGER_TYPE +
                NOT_NULL + COMMA_SEP + COLUMN_SHIP_PRICE + INTERGER_TYPE + NOT_NULL + COMMA_SEP +
                COLUMN_SHIP_QUANTITY + INTERGER_TYPE + NOT_NULL + DEFAULT + " 1 " + COMMA_SEP +
                COLUMN_SHIP_SUPPLIER + TEXT_TYPE + COMMA_SEP + COLUMN_SHIP_PHONE + TEXT_TYPE +
                " )" + ";";
        public static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + Entry.TABLE_NAME;
    }
}
