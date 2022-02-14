package com.example.diaryapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

/**
 * @author jinyan
 *
 */


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Calendar;
import java.util.Date;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
class ActivityDiaryEdit extends Activity {

    private EditText mTitleText;
    private EditText mBodyText;
    private Long mRowId;
    private DiaryDbAdapter mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDbHelper = new DiaryDbAdapter(this);
        mDbHelper.open();
        setContentView(R.layout.activity_diary_edit);

        mTitleText = (EditText) findViewById(R.id.title);
        mBodyText = (EditText) findViewById(R.id.body);

        Button confirmButton = (Button) findViewById(R.id.confirm);

        mRowId = null;
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String title = extras.getString(DiaryDbAdapter.KEY_TITLE);
            String body = extras.getString(DiaryDbAdapter.KEY_BODY);
            mRowId = extras.getLong(DiaryDbAdapter.KEY_ROWID);

            if (title != null) {
                mTitleText.setText(title);
            }
            if (body != null) {
                mBodyText.setText(body);
            }
        }

        confirmButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String title = mTitleText.getText().toString();
                String body = mBodyText.getText().toString();
                if (mRowId != null) {
                    mDbHelper.updateDiary(mRowId, title, body);
                } else
                    mDbHelper.createDiary(title, body);
                Intent mIntent = new Intent();
                setResult(RESULT_OK, mIntent);
                finish();
            }

        });
    }
}
