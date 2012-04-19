package rinxter.First;

import java.net.URI;
import java.net.URISyntaxException;

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
        setContentView(rinxter.First.R.layout.main);
        Button bout_btn = (Button) findViewById(rinxter.First.R.id.bout_btn);
        
        
        bout_btn.setOnClickListener(new View.OnClickListener() {			
			@Override
			public void onClick(View v) {
				URI bouts = null;
				try {
					bouts = new URI("http://rinxter.net/wftda/ds?type=boutList&leagueId=1&season=2011");
				} catch (URISyntaxException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Intent i =  new Intent(getApplicationContext(), SecondScreenActivity.class);
				i.putExtra("URI", bouts);
				startActivity(i);
			}
        });
        Button team_btn = (Button) findViewById(R.id.team_btn);
        team_btn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				URI teams = null;
				try {
					teams = new URI("http://rinxter.net/wftda/ds?type=teamList&leagueId=1&season=2011");
				} catch (URISyntaxException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Intent i = new Intent(getApplicationContext(), SecondScreenActivity.class);
				i.putExtra("URI", teams);
				startActivity(i);
			}
		});
    }
	
}
