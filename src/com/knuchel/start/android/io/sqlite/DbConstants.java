package com.knuchel.start.android.io.sqlite;

public final class DbConstants {
    public static final int VERSION_BDD = 1;
    public static final String NOM_BDD = "startdroid.db"; // Only lowercase letter

    public static final String ARTICLE_TABLE = "table_article";
    public static final String ARTICLE_COL_ID = "id";
    public static final int ARTICLE_NUM_COL_ID = 0;
    public static final String ARTICLE_COL_BARCODE = "barcode";
    public static final int ARTICLE_NUM_COL_BARCODE = 1;
    public static final String ARTICLE_COL_BARCODEFORMAT = "barcodeformat";
    public static final int ARTICLE_NUM_COL_BARCODEFORMAT = 2;
    public static final String ARTICLE_COL_NAME = "name";
    public static final int ARTICLE_NUM_COL_NAME = 3;
    public static final String ARTICLE_COL_DATE = "date";
    public static final int ARTICLE_NUM_COL_DATE = 4;
    public static final String ARTICLE_COL_PRICE = "price";
    public static final int ARTICLE_NUM_COL_PRICE = 5;

    public static final String URI_TABLE = "table_article";
    public static final String URI_COL_ID = "id";
    public static final int URI_NUM_COL_ID = 0;
    public static final String URI_COL_DATE = "date";
    public static final int URI_NUM_COL_DATE = 1;
    public static final String URI_COL_URI = "uri";
    public static final int URI_NUM_COL_URI = 2;
    public static final String URI_COL_TYPE = "type";
    public static final int URI_NUM_COL_TYPE = 3;
    public static final String URI_COL_CONTENT = "content";
    public static final int URI_NUM_COL_CONTENT = 4;

    public static final String EMPLOYE_TABLE = "table_employe";
    public static final String EMPLOYE_COL_ID = "id";
    public static final int EMPLOYE_NUM_COL_ID = 0;
    public static final String EMPLOYE_COL_NAME = "name";
    public static final int EMPLOYE_NUM_COL_NAME = 1;
    public static final String EMPLOYE_COL_JOB = "job";
    public static final int EMPLOYE_NUM_COL_JOB = 2;
    public static final String EMPLOYE_COL_COMPANY = "company";
    public static final int EMPLOYE_NUM_COL_COMPANY = 3;
    
    public static final String BARCODE_TABLE = "table_barcode";
    public static final String BARCODE_COL_ID = "id";
    public static final int BARCODE_NUM_COL_ID = 0;
    public static final String BARCODE_COL_FORMAT = "format";
    public static final int BARCODE_NUM_COL_FORMAT = 1;
    public static final String BARCODE_COL_VALUE = "value";
    public static final int BARCODE_NUM_COL_VALUE = 2;
}
