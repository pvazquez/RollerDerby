package program.layout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ProgramLayout2 extends Activity{

	public void onCreate( Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(program.layout.R.layout.second_activity);
		
		Button b2 =  (Button) findViewById(program.layout.R.id.button2);
	       
	       b2.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
			
				Intent myIntent = new Intent(arg0.getContext(), ProgramLayoutActivity.class);
			
			startActivity(myIntent);
				
			}
		});
	       
	       
	        
	    }
}
