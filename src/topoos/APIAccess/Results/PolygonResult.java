package topoos.APIAccess.Results;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONTokener;

import topoos.Constants;
import topoos.Exception.TopoosException;
import topoos.Objects.Location;

// TODO: Auto-generated Javadoc
/**
 * The Class PolygonResult.
 *
 * @see APICallResult
 * @author MAJS
 */
public class PolygonResult extends APICallResult{
	
	/** The polygon. */
	List<Location> polygon=null;
	
	/**
	 * Instantiates a new polygon result.
	 */
	public PolygonResult() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Instantiates a new polygon result.
	 *
	 * @param error the error
	 * @param result the result
	 */
	public PolygonResult(String error, String result) {
		super(error, result);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Instantiates a new polygon result.
	 *
	 * @param error the error
	 * @param result the result
	 * @param polygon the polygon
	 */
	public PolygonResult(String error, String result, List<Location> polygon) {
		super(error, result);
		this.polygon = polygon;
	}

	/* (non-Javadoc)
	 * @see topoos.APIAccess.Results.APICallResult#setParameters()
	 */
	@Override
	public void setParameters() throws TopoosException {
		// TODO Auto-generated method stub
		
		ArrayList<Location> polygon=null;
		try {
			JSONArray jArray = (JSONArray) new JSONTokener(this.Result)
					.nextValue();
			polygon = new ArrayList<Location>();
			// Extracting content
			for (int i = 0; i < jArray.length(); i++) {
				polygon.add(new Location(jArray.getJSONObject(i).getDouble("latitude"),jArray.getJSONObject(i).getDouble("longitude")));
			}
			//this.checkinCollection=new CheckinCollection(array);
		} catch (Exception e) {
			if (Constants.DEBUG){
				e.printStackTrace();
			}
			throw new TopoosException(TopoosException.ERROR_PARSE);
		}
		this.polygon=polygon;
		
		
		/* Get a SAXParser from the SAXPArserFactory. */
		/*if (this.Result != null) {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db;
			try {
				polygon= new ArrayList<Location>();
				db = dbf.newDocumentBuilder();
				Document doc = db.parse(this.Result);
				NodeList nodelist = doc.getElementsByTagName("coordinates");
				if (nodelist != null) {
					Node node = nodelist.item(0);
					String coordinates=node.getNodeValue();
					String[] coordinatesList=coordinates.split("\n");
					for (String pos:coordinatesList){
						Double lat=Double.valueOf(pos.split(",")[0]);
						Double lng=Double.valueOf(pos.split(",")[1]);
						polygon.add(new Location(lat,lng));
					}
				}
			} catch (Exception e) {
				if (Constants.DEBUG){
					e.printStackTrace();
				}
				throw new TopoosException(TopoosException.ERROR_PARSE);
			}
		}*/

	}

	/**
	 * Gets the polygon.
	 *
	 * @return the polygon
	 */
	public List<Location> getPolygon() {
		return polygon;
	}

	/**
	 * Sets the polygon.
	 *
	 * @param polygon the polygon to set
	 */
	public void setPolygon(List<Location> polygon) {
		this.polygon = polygon;
	}
	
	
	
}
