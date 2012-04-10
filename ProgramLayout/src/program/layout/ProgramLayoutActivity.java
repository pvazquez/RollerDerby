package program.layout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ProgramLayoutActivity extends Activity {
    /** Called when the activity is first created. */
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Button b1 =  (Button) findViewById(R.id.button1);

        b1.setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
		
			Intent myIntent = new Intent(getApplicationContext(), ProgramLayout2.class);
		
			startActivity(myIntent);
			
		}
	});
       

        
    }
    
}

