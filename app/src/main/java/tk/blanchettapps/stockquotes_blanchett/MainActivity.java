package tk.blanchettapps.stockquotes_blanchett;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    Stock dataGrab;
    String[] stockValues;
    boolean grabbingData = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }







}

class retreiveStocks extends AsyncTask<String, Void, String[]>
{
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(String[] strings) {
        super.onPostExecute(strings);
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
    }

    @Override
    protected String[] doInBackground(String... params)
    {




        return null;
    }
}
