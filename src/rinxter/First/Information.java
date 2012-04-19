package rinxter.First;

import java.util.HashMap;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Information {
	

	private HashMap<String, String> x = new HashMap<String, String>();	
	
	public Information(JSONObject data) throws JSONException
	{


		JSONArray info_name = data.names();
		JSONArray info_data = data.toJSONArray(info_name);
		
		for(int i = 0; i < info_name.length(); i++)
		{	
			String name = info_name.getString(i);
			String data_s = info_data.getString(i);
			data_s.replace("<b>", " ");
			data_s.replace("</b>", " ");
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

}
