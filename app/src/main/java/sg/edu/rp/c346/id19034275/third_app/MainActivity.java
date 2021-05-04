package sg.edu.rp.c346.id19034275.third_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    Button newtablebutton , summaryButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        newtablebutton = (Button)findViewById(R.id.newtablebutton);

        summaryButton = (Button)findViewById(R.id.btnSummarise);

        newtablebutton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent i = new Intent(MainActivity.this,NewTable.class);
                startActivity(i);
            }
        });
        Button btnSummarise = (Button)findViewById(R.id.btnSummarise);
        btnSummarise.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, summary.class);
                startActivity(i);
            }
        });

    }
}