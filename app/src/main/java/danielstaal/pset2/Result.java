package danielstaal.pset2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Daniel on 4/22/2016.
 *
 * Class to print the result to the screen and restart
 */
public class Result extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result);

        // get the result string passed by the AskWords activity
        Bundle extras = getIntent().getExtras();
        String result = extras.getString("result");

        TextView tv = (TextView)findViewById(R.id.resultText);

        tv.setText(Html.fromHtml(result));

        Button restart = (Button)findViewById(R.id.restart);
        assert restart != null;
        restart.setOnClickListener(
                new View.OnClickListener()
                {
                    public void onClick(View view) {
                        Intent restart = new Intent(Result.this, MainActivity.class);
                        startActivity(restart);
                        finish();
                    }
                });
    }

}
