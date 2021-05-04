package sg.edu.rp.c346.id19034275.third_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class NewTable extends AppCompatActivity {

    public static String TAG = "MYDEV";

    EditText etName,etDate;
    Button btContinue;
    static ArrayList<row> myRecords = new ArrayList<>();

    private Context context;
    private DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener()
    {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth)
        {
            Calendar cal = Calendar.getInstance();
            cal.set(Calendar.YEAR,year);
            cal.set(Calendar.MONTH,month);
            cal.set(Calendar.DAY_OF_MONTH,dayOfMonth);
            SimpleDateFormat f = new SimpleDateFormat("yyyy-MMM-dd,EEE");
            String str = f.format(cal.getTime());
        }
    };

    private View.OnClickListener DateListener = new View.OnClickListener()
    {
        @Override
        public void onClick(View v)
        {
            DialogFragment datePickerDialog = new DialogFragment();
            datePickerDialog.show(getSupportFragmentManager(),"date picker");

            Calendar cal = Calendar.getInstance();
            int year = cal.get(Calendar.YEAR);
            int mon = cal.get(Calendar.MONTH);
            int day = cal.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog d = new DatePickerDialog(context,dateSetListener,year,mon,day);
            d.show();


        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_table);
        setupUi();
        Log.i(TAG, "onCreate: " + myRecords.size());
    }

    public void setupUi()
    {
        context = NewTable.this;
        etName = (EditText)findViewById(R.id.etName);
        etDate = (EditText)findViewById(R.id.etDate);
        String namestored = etName.getText().toString();
        String datestored = etDate.getText().toString();
        btContinue = (Button)findViewById(R.id.btContinue);

        btContinue.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent i = new Intent(NewTable.this,Settings.class);
                startActivity(i);
//                loadData();
            }
        });

        etDate.setOnClickListener(DateListener);
    }
    public void addRecord(String date,String name,int score)
    {
        DataBase db = new DataBase(NewTable.this);
        db.insertData(date,name,score);
    }
    // Change to static due to error on ViewHolder class
    static class RecordAdapter extends BaseAdapter
    {
        Context mContext;

        ArrayList<row> listTables;
        LayoutInflater inflater;

        static class ViewHolder
        {
            TextView lblDate,lblName,lblScore;
        }

        public RecordAdapter(Context mContext, ArrayList<row> listTables)
        {
            this.mContext = mContext;
            this.listTables = listTables;
            this.inflater = (LayoutInflater.from(mContext));
        }

        @Override
        public int getCount()
        {
            return listTables.size();
        }

        @Override
        public Object getItem(int position)
        {
            return listTables.get(position);
        }

        @Override
        public long getItemId(int position)
        {
            return listTables.get(position).id;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent)
        {
            final row model = (row) getItem(position);
            final ViewHolder viewHolder;
            final View rowView;

            if(convertView == null)
            {
                viewHolder = new ViewHolder();
                convertView = inflater.inflate(R.layout.activity_row,parent,false);
                viewHolder.lblDate = convertView.findViewById(R.id.lblDate);
                viewHolder.lblName = convertView.findViewById(R.id.lblName);
                viewHolder.lblScore = convertView.findViewById(R.id.lblScore);

                rowView = convertView;
                convertView.setTag(viewHolder);
            }
            else
            {
                viewHolder = (ViewHolder) convertView.getTag();
                rowView = convertView;
            }

            if (model != null)
            {
                viewHolder.lblDate.setText(model.date);
                viewHolder.lblName.setText(model.name);
                viewHolder.lblScore.setText( String.valueOf(model.score));
            }

            return rowView;
        }




    }
}