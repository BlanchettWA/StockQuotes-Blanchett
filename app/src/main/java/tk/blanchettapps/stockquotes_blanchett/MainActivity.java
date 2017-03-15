package tk.blanchettapps.stockquotes_blanchett;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    boolean grabbingData = false;
    Button doStuff;
    EditText userInput;
    String symbolText = "";
    ProgressBar loader;
TextView[] valueWindows = new TextView[6];
    Context appcontext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        appcontext = getApplicationContext();
        userInput = (EditText) findViewById(R.id.targetSymbol);

        doStuff = (Button) findViewById(R.id.button);

        userInput.addTextChangedListener(new TextWatcher() {
            @Override


            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }


            @Override
            public void afterTextChanged(Editable s) {

                if (s.length() > 0) {symbolText = s.toString();}
                else {symbolText = "";}
            }
        });

        loader = (ProgressBar)findViewById(R.id.loadProgress);
        loader.setVisibility(View.GONE);

        doStuff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rStock doTask = new rStock();
                doTask.execute(symbolText);
            }

        });

        valueWindows[0] = (TextView) findViewById(R.id.retrievedStockSymbol);
        valueWindows[1] = (TextView) findViewById(R.id.retirevedCompanyBox);
        valueWindows[2] = (TextView) findViewById(R.id.lastTradeTimeBox);
        valueWindows[3] = (TextView) findViewById(R.id.lastTradePriceBox);
        valueWindows[4] = (TextView) findViewById(R.id.changeBox);
        valueWindows[5] = (TextView) findViewById(R.id.rangeBox);
    }


    private class rStock extends AsyncTask<String, Void, String[]> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            grabbingData = true;
            loader.setVisibility(View.VISIBLE);
        }

        @Override


        protected String[] doInBackground(String... params) {
            Stock toproc = new Stock(params[0]);
            String[] result = new String[6];
            try {

                if (params[0].length() < 0){
                    IOException badlenght = new IOException();
                    Toast.makeText(appcontext, "Please type a symbol", Toast.LENGTH_SHORT).show();
                    throw badlenght;
                } else {

                    toproc.load();
                    result[0] = toproc.getSymbol();
                    result[1] = toproc.getName();
                    result[2] = toproc.getLastTradeTime();
                    result[3] = toproc.getLastTradePrice();
                    result[4] = toproc.getChange();
                    result[5] = toproc.getRange();
                }
            } catch (IOException e) {
                e.printStackTrace();
                result = null;
            }
            return result;
        }

        @Override
        protected void onPostExecute(String[] strings) {
            super.onPostExecute(strings);
            loader.setVisibility(View.GONE);
            if (strings == null){ Toast.makeText(appcontext, "Invalid stock symbol!", Toast.LENGTH_SHORT).show();}
            else
            {
                for(int i = 0; i < strings.length;i++ ){
                    valueWindows[i].setText(strings[i]);
                }
            }
        }


    }
}