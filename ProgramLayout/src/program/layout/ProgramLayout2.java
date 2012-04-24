package program.layout;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class ProgramLayout2 extends Activity{

	public void onCreate( Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		super.setRequestedOrientation(0);
		setContentView(program.layout.R.layout.second_activity);
	
		ArrayList<Bouts> bout = new ArrayList<Bouts>();
		bout.add(new Bouts("2012-30-31", "League", "Hunter College, NY, NY", "Brooklyn Bombshells / Queens of Pain", "Completed"));
		System.out.println("stuff");
		
		for(int i = 0; i < bout.size(); i++){
			Bouts pb = bout.get(i);
			System.out.println(pb.Date + pb.Sanctioned);
		}
		
		TableLayout tl = (TableLayout) findViewById(R.id.maintable);
		
        // Go through each item in the array
        for (int current = 0; current < bout.size(); current++)
        {
            // Create a TableRow and give it an ID
        	Bouts currentbout = bout.get(current);
        	System.out.println(currentbout.Date);
            TableRow tr = new TableRow(this);
            tr.setId(current);
            tr.setLayoutParams(new LayoutParams(
                    LayoutParams.FILL_PARENT,
                    LayoutParams.WRAP_CONTENT));   

            // Create a TextView to house the name of the province
            TextView labelDate = new TextView(this);
            labelDate.setId(100+current);
            labelDate.setText(currentbout.Date);
            labelDate.setTextColor(Color.WHITE);
            labelDate.setLayoutParams(new LayoutParams(
                    LayoutParams.FILL_PARENT,
                    LayoutParams.WRAP_CONTENT));
            tr.addView(labelDate);
/*
            // Create a TextView to house the values
            TextView valueTV = new TextView(this);
            valueTV.setId(current);
            valueTV.setText("$0");
            valueTV.setTextColor(Color.BLACK);
            valueTV.setLayoutParams(new LayoutParams(
                    LayoutParams.FILL_PARENT,
                    LayoutParams.WRAP_CONTENT));
            tr.addView(valueTV);
*/
            // Add the TableRow to the TableLayout
            tl.addView(tr, new TableLayout.LayoutParams(
                    LayoutParams.FILL_PARENT,
                    LayoutParams.WRAP_CONTENT));
        }
		
		
		
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
