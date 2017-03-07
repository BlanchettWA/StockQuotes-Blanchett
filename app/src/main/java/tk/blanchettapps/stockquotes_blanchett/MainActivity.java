package tk.blanchettapps.stockquotes_blanchett;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    Stock dataGrab;
    boolean workingTask = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }







}

class retreiveStocks extends AsyncTask<Void, Void, Void>
{

    @Override
    protected Void doInBackground(Void... params) {
        return null;
    }
}
