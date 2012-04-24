package rinxter.First;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class SecondScreenActivity extends Activity {
	
	ArrayList<Information> info = new ArrayList<Information>();
	
	public void onCreate(Bundle savedInstanceState)
	{
		//gets the information from the website using a URI connection
		super.onCreate(savedInstanceState);
		super.setRequestedOrientation(0);
		setContentView(rinxter.First.R.layout.screen2);
		Intent i = getIntent();
		Bundle x = i.getExtras();
		URI access = (URI) x.get("URI");
		
		ArrayList<String> al = null;
		HttpClient client = new DefaultHttpClient();
		HttpGet request = new HttpGet();
		try {
			request.setURI(access);
			HttpResponse response = client.execute(request);
			InputStream ips = response.getEntity().getContent();
			BufferedReader buf = new BufferedReader(new InputStreamReader(ips, "UTF-8"));
			
			al = new ArrayList<String>();
			String s;
			while(true)
			{
				s = buf.readLine();
				if(s==null || s.length()==0)
					break;
				al.add(s);
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		//getting data from an array of hash tables
		try {
			JSONObject myj = new JSONObject(al.get(0).substring(1));
			info.add(new Information(myj));
			for(int j = 1; j < al.size()-1; j++)
				info.add(new Information(new JSONObject(al.get(j))));
			myj = new JSONObject(al.get(al.size()-1).substring(0, al.get(al.size()-1).length()-1));
			info.add(new Information(myj));
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		
		/* Layout of the screen*/
		FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(
		        ViewGroup.LayoutParams.FILL_PARENT,
		        ViewGroup.LayoutParams.FILL_PARENT);
		TableLayout.LayoutParams rowLp = new TableLayout.LayoutParams(
		        ViewGroup.LayoutParams.FILL_PARENT,
		        ViewGroup.LayoutParams.FILL_PARENT,
		        1.0f);
		rowLp.setMargins(2, 1, 2, 1);
		TableRow.LayoutParams cellLp = new TableRow.LayoutParams(
		        ViewGroup.LayoutParams.FILL_PARENT,
		        ViewGroup.LayoutParams.FILL_PARENT,
		        1.0f);
		cellLp.setMargins(2, 0, 2, 0);
		
		TableLayout maintable = (TableLayout) findViewById(R.id.maintable);
		maintable.setLayoutParams(lp);
		for(int i1 = 0; i1 < info.size(); i1++)
		{
			TableRow TR = new TableRow(this);
			TR.setBackgroundColor(Color.BLACK);
			TR.setPadding(3, 1, 3, 1);
            TR.setLayoutParams(rowLp);
            if(i1 == 0)
            {
	            for(String key : info.get(i1).getKeys())
	            {
	            	TextView tv = new TextView(this);
	            	tv.setLayoutParams(cellLp);              
	            	TR.addView(tv);
	            	tv.setPadding(2, 2, 2, 2);
	            	tv.setBackgroundColor(Color.WHITE);
	            	tv.setTextColor(Color.BLUE);
	            	tv.setText(key);
	            }
            }
            else
            {
            	for(String key: info.get(i1).getKeys())
            	{
            		String z = info.get(i1).getData(key);
	            	TextView tv = new TextView(this);
	            	tv.setLayoutParams(cellLp);              
	            	TR.addView(tv);
	            	tv.setText(z);
	            	tv.setPadding(2, 2, 2, 2);
	            	tv.setBackgroundColor(Color.WHITE);
	            	tv.setTextColor(Color.BLACK);
            	}
            	
            }     
        	maintable.addView(TR);
		}
		TableRow TR = new TableRow(this);
		TR.setLayoutParams(rowLp);
		Button back = new Button(this);
		back.setLayoutParams(cellLp);
		back.setOnClickListener(new View.OnClickListener() {		
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		TR.addView(back);
		maintable.addView(TR);
		back.setText("Back");
	}
}

