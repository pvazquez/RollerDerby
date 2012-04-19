package rinxter.First;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.R.xml;

public class Information {
	

	private HashMap<String, String> x = new HashMap<String, String>();	
	
	public Information(JSONObject data) throws JSONException
	{


		JSONArray info_name = data.names();
		JSONArray info_data = data.toJSONArray(info_name);
		
		for(int i = 0; i < info_name.length(); i++)
		{		
			x.put(info_name.getString(i), info_data.getString(i));
		}
	}
	
	public String getData(String key)
	{
		return x.get(key);
	}
	
	public Set<String> getKeys()
	{
		return x.keySet();
	}
	
	public String[] getDataArray()
	{
		int i = 0;
		String data[] = null;
		for(String key : x.keySet())
		{
			data[i] = x.get(key);
			i++;
		}
		return data;
	}
}
