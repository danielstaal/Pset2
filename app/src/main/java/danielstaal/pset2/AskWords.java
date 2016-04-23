package danielstaal.pset2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by Daniel on 4/20/2016.
 *
 * Class to ask the user for input strings to fit in the story
 */

public class AskWords extends AppCompatActivity {

    private TextView info;
    private Story story;
    private EditText input;
    private String nextPlaceholder;
    private String infoText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ask_words);

        info = (TextView)findViewById(R.id.info);
        story = (Story) getIntent().getExtras().getSerializable("story");
        infoText = "Remaining words to fill in: " + story.getPlaceholderRemainingCount();
        info.setText(infoText);

        nextPlaceholder = story.getNextPlaceholder();
        input = (EditText)findViewById(R.id.userInput);
        input.setHint(nextPlaceholder);

        Button ok = (Button)findViewById(R.id.ok);
        assert ok != null;
        ok.setOnClickListener(
                new View.OnClickListener()
                {
                    public void onClick(View view)
                    {
                        info.setText("");
                        String infoText = "Remaining words to fill in: " + (story.getPlaceholderRemainingCount()-1);
                        info.setText(infoText);
                        story.fillInPlaceholder(input.getText().toString());
                        // Until all words are filled in ask for words, then pass the
                        // result to the Result activity
                        if(!story.isFilledIn()) {
                            nextPlaceholder = story.getNextPlaceholder();
                            input.setText("");
                            input.setHint(nextPlaceholder);
                        }else{
                            Intent result = new Intent(AskWords.this, Result.class);
                            result.putExtra("result", story.toString());
                            startActivity(result);
                            finish();
                        }
                    }
                });
    }


}











