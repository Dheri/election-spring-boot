/**
 * 
 */
package in.parteek.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;

/**
 * Created on : 2019-03-06, 11:59:31 p.m.
 *
 * @author Parteek Dheri
 */
public class PieChart {
	private Gson gsonObj = new Gson();
	private Map<Object, Object> map = null;
	private List<Map<Object, Object>> list = new ArrayList<Map<Object, Object>>();

	private HashMap<String, Integer> data;

	public PieChart(HashMap<String, Integer> data) {
		this.data = data;
		init();
	}

	private void init() {
		for (String key : data.keySet()) {
			String value = data.get(key).toString();
			map = new HashMap<Object, Object>();
			map.put("lbl", key);
			map.put("y", value);
			map.put("exploded", true);
			list.add(map);

		}
	}

	public String getChartAsJson() {
		String json = gsonObj.toJson(list);
		// convert string to int in json
		return json.replaceAll("\"y\":\"(\\d*)\"", "\"y\":$1");

	}

}
