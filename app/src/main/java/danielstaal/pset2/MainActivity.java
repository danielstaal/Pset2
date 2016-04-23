package danielstaal.pset2;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    private Context mContext;
    private Spinner dropdown;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dropdown = (Spinner)findViewById(R.id.choose);
        String[] items = new String[]{"simple", "tarzan", "university", "clothes", "dance"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);

        Button start = (Button)findViewById(R.id.startbutton);

        assert start != null;
        start.setOnClickListener(
            new View.OnClickListener()
            {
                public void onClick(View view)
                {
                    String text = dropdown.getSelectedItem().toString();
                    text = getFileName(text);

                    mContext = MainActivity.this;
                    InputStream is = null;

                    // get the text file with a text with missing words
                    try {
                        is = mContext.getAssets().open(text);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    // start the askforwords activity
                    Intent askforwords = new Intent(MainActivity.this, AskWords.class);
                    Story story = new danielstaal.pset2.Story(is);
                    askforwords.putExtra("story", story);
                    startActivity(askforwords);
                }
            });
    }

    private String getFileName(String s){
        if(s.equals("simple")) {
            return "madlib0_simple.txt";
        }
        if(s.equals("tarzan")) {
            return "madlib1_tarzan.txt";
        }
        if(s.equals("university")) {
            return "madlib2_university.txt";
        }
        if(s.equals("clothes")) {
            return "madlib3_clothes.txt";
        }
        if(s.equals("dance")) {
            return "madlib4_dance.txt";
        }
        return "";
    }
}
