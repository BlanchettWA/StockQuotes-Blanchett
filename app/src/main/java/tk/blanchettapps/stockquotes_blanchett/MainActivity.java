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
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

     String[] stockValues;
    boolean grabbingData = false;
    Button doStuff;
    EditText userInput;
    String symbolText;
    Context appcontext = getApplicationContext();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userInput = (EditText)findViewById(R.id.targetSymbol);

        doStuff = (Button)findViewById(R.id.button);

        userInput.addTextChangedListener(new TextWatcher() {
            @Override



            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {}


            @Override
            public void afterTextChanged(Editable s) {

                if (s.length() > 0)
                {
                    symbolText = s.toString();
                    userInput.setText(symbolText);
                }
                else
                {
                    userInput.setText(R.string.valueless);
                }
            }
        });


        doStuff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {


            }

        });




    }



}

class rStock extends AsyncTask<String,Void, String[]> {
    @Override
    protected String[] doInBackground(String... params) {
        Stock toproc = new Stock(params[0]);
        String[] result = new String[6];
        try
        {
            toproc.load();
            result[0] = toproc.getSymbol();
            result[1] = toproc.getName();
            result[2] = toproc.getLastTradeTime();
            result[3] = toproc.getLastTradePrice();
            result[4] = toproc.getChange();
            result[5] = toproc.getRange();

        }
        catch (IOException e)
        {
            e.printStackTrace();
            result = null;
        }

        return result;
    }
}