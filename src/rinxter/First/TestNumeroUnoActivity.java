package rinxter.First;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TestNumeroUnoActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Intent i =  new Intent(this, SecondScreenActivity.class);
        Button b = (Button) findViewById(rinxter.First.R.id.btn1);
        b.setOnClickListener(new View.OnClickListener() {			
			@Override
			public void onClick(View v) {
				startActivity(i);
			}
        });
        setContentView(rinxter.First.R.layout.main);
    }
	
}
