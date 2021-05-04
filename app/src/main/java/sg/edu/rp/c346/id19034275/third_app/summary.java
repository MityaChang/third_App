package sg.edu.rp.c346.id19034275.third_app;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class summary extends AppCompatActivity {

    ListView lstTable;
    ArrayList<row> myRecords = new ArrayList<>();
    NewTable.RecordAdapter myRecordAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        lstTable = findViewById(R.id.lstTable);

        loadData();

        myRecordAdapter = new NewTable.RecordAdapter(this, myRecords);
        lstTable.setAdapter(myRecordAdapter);

    }

    public void loadData() {
        myRecords.clear();

        DataBase db = new DataBase(this);
        Cursor cursor = db.getAllData();

        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String date = cursor.getString(1);
            String name = cursor.getString(2);
            int score = cursor.getInt(3);

            row listTable = new row(id, date, name, score);

            myRecords.add(listTable);
        }

        if (myRecordAdapter != null) {
            myRecordAdapter.notifyDataSetChanged();
        }

    }
}