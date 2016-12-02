package com.example.sanakazi.cabg.database;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by SanaKazi on 12/1/2016.
 */
public class Cabg {

    protected static final String TAG = "DataAdapter";

    private final Context mContext;
    private SQLiteDatabase mDb;
    private DataBaseHelper mDbHelper;
    String cabg_id,age,current_OM_CABG,current_OM_CABGAVR,current_PS_CABG,current_PS_CABGAVR,current_RF_CABG, current_RF_CABGAVR,
            future_OM_CABGAVR,future_PS_CABGAVR,future_RF_CABGAVR,status;
    String id,gradientValue,daysAdded;


    public Cabg(Context context) {
        this.mContext = context;
        mDbHelper = new DataBaseHelper(mContext);

    }

    public Cabg createDatabase() throws SQLException {
        try {
            mDbHelper.createDataBase();
        } catch (IOException mIOException) {
            Log.e(TAG, mIOException.toString() + "  UnableToCreateDatabase");
            throw new Error("UnableToCreateDatabase");
        }
        return this;
    }

    public Cabg open() throws SQLException {
        try {
            mDbHelper.openDataBase();
            mDbHelper.close();
            mDb = mDbHelper.getReadableDatabase();
        } catch (SQLException mSQLException) {
            Log.e(TAG, "open >>" + mSQLException.toString());
            throw mSQLException;
        }
        return this;
    }

    public void close() {
        mDbHelper.close();
    }

    public CabjBean getCurrentValues(String age_value) {
        try {
            String sql = "SELECT * FROM cabg WHERE age =" + age_value;
            Cursor mCur = mDb.rawQuery(sql, null);
            mCur.moveToFirst();
            while (mCur.isAfterLast() == false) {

                if (mCur != null) {
                    cabg_id = mCur.getString(0);
                    age = mCur.getString(1);
                    current_OM_CABG = mCur.getString(2);
                    current_OM_CABGAVR = mCur.getString(3);
                    current_PS_CABG = mCur.getString(4);
                    current_PS_CABGAVR = mCur.getString(5);
                    current_RF_CABG = mCur.getString(6);
                    current_RF_CABGAVR = mCur.getString(7);
                    status=mCur.getString(11);

                    mCur.moveToNext();
                }

            }
            mCur.moveToPosition(0);

            return new CabjBean(cabg_id, age, current_OM_CABG,current_OM_CABGAVR,current_PS_CABG,current_PS_CABGAVR,current_RF_CABG, current_RF_CABGAVR,status);

        } catch (SQLException mSQLException) {
            Log.e(TAG, "getTestData >>" + mSQLException.toString());
            throw mSQLException;
        }

    }

    public CabjBean getFutureValues(String age_value) {
        try {
            String sql = "SELECT * FROM cabg WHERE age =" + age_value;
            Cursor mCur = mDb.rawQuery(sql, null);
            mCur.moveToFirst();
            while (mCur.isAfterLast() == false) {

                if (mCur != null) {
                    cabg_id = mCur.getString(0);
                    age = mCur.getString(1);
                    future_OM_CABGAVR = mCur.getString(8);
                    future_PS_CABGAVR = mCur.getString(9);
                    future_RF_CABGAVR = mCur.getString(10);
                    status=mCur.getString(11);

                    mCur.moveToNext();
                }

            }
            mCur.moveToPosition(0);

            return new CabjBean(cabg_id, age,future_OM_CABGAVR,future_PS_CABGAVR,future_RF_CABGAVR,status);

        } catch (SQLException mSQLException) {
            Log.e(TAG, "getTestData >>" + mSQLException.toString());
            throw mSQLException;
        }

    }

    public CabjBean getAllValues(String age_value) {
        try {
            String sql = "SELECT * FROM cabg WHERE age =" + age_value;
            Cursor mCur = mDb.rawQuery(sql, null);
            mCur.moveToFirst();
            while (mCur.isAfterLast() == false) {

                if (mCur != null) {
                    cabg_id = mCur.getString(0);
                    age = mCur.getString(1);
                    current_OM_CABG = mCur.getString(2);
                    current_OM_CABGAVR = mCur.getString(3);
                    current_PS_CABG = mCur.getString(4);
                    current_PS_CABGAVR = mCur.getString(5);
                    current_RF_CABG = mCur.getString(6);
                    current_RF_CABGAVR = mCur.getString(7);
                    future_OM_CABGAVR = mCur.getString(8);
                    future_PS_CABGAVR = mCur.getString(9);
                    future_RF_CABGAVR = mCur.getString(10);
                    status=mCur.getString(11);

                    mCur.moveToNext();
                }

            }
            mCur.moveToPosition(0);

            return new CabjBean(cabg_id,age,current_OM_CABG,current_OM_CABGAVR,current_PS_CABG,current_PS_CABGAVR,current_RF_CABG, current_RF_CABGAVR,
                    future_OM_CABGAVR,future_PS_CABGAVR,future_RF_CABGAVR,status);

        } catch (SQLException mSQLException) {
            Log.e(TAG, "getTestData >>" + mSQLException.toString());
            throw mSQLException;
        }

    }

    public DaysAddedBean getDaysAdded(String gradient)
    {

        try {
            String sql = " SELECT * FROM AgeCalculation WHERE gradientValue ="+gradient;
            Cursor mCur = mDb.rawQuery(sql, null);
            mCur.moveToFirst();
            while (mCur.isAfterLast() == false) {

                if (mCur != null) {
                    cabg_id = mCur.getString(0);;
                    gradientValue = mCur.getString(1);
                    daysAdded = mCur.getString(2);

                    mCur.moveToNext();
                }

            }
            mCur.moveToPosition(0);

        return new DaysAddedBean(id,gradientValue,daysAdded);
        } catch (SQLException mSQLException) {
            Log.e(TAG, "getTestData >>" + mSQLException.toString());
            throw mSQLException;
        }
    }

}
