package sg.edu.rp.c346.id19034275.third_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class row extends NewTable {

    int id,score;
    String name,date;

    public row(int id, String date, String name, int score)
    {
        this.id = id;
        this.score = score;
        this.name = name;
        this.date = date;
    }

}