package topoos.APIAccess.Results;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONTokener;

import topoos.Constants;
import topoos.Exception.TopoosException;
import topoos.Objects.*;
// TODO: Auto-generated Javadoc

/**
 * The Class CheckinCollectionResult.
 *
 * @see APICallResult
 * @author topoos
 */
public class CheckinCollectionResult extends APICallResult {

	/** The checkin collection. */
	private CheckinCollection checkinCollection = null;

	/**
	 * Instantiates a new checkin collection result.
	 */
	public CheckinCollectionResult() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Instantiates a new checkin collection result.
	 *
	 * @param error the error
	 * @param result the result
	 */
	public CheckinCollectionResult(String error, String result) {
		super(error, result);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Instantiates a new checkin collection result.
	 *
	 * @param error the error
	 * @param result the result
	 * @param checkinCollection the checkin collection
	 */
	public CheckinCollectionResult(String error, String result,
			CheckinCollection checkinCollection) {
		super(error, result);
		this.checkinCollection = checkinCollection;
	}

	/* (non-Javadoc)
	 * @see topoos.APIAccess.Results.APICallResult#setParameters()
	 */
	@Override
	public void setParameters() throws TopoosException {
		// TODO Auto-generated method stub
		try {
			JSONArray jArray = (JSONArray) new JSONTokener(this.Result)
					.nextValue();
			ArrayList<Checkin> array = new ArrayList<Checkin>();
			// Extracting content
			for (int i = 0; i < jArray.length(); i++) {
				array.add(new Checkin(jArray.getJSONObject(i).getInt("id"),
						jArray.getJSONObject(i).getInt("device_id"), jArray
								.getJSONObject(i).getInt("poi_id"), APIUtils
								.toDateString(jArray.getJSONObject(i)
										.getString("register_time")), APIUtils
								.toDateString(jArray.getJSONObject(i)
										.getString("timestamp"))));
			}
			this.checkinCollection=new CheckinCollection(array);
		} catch (Exception e) {
			if (Constants.DEBUG){
				e.printStackTrace();
			}
			throw new TopoosException(TopoosException.ERROR_PARSE);
		}
	}

	/**
	 * Gets the checkin collection.
	 *
	 * @return the checkinCollection
	 */
	public CheckinCollection getCheckinCollection() {
		return checkinCollection;
	}

	/**
	 * Sets the checkin collection.
	 *
	 * @param checkinCollection the checkinCollection to set
	 */
	public void setCheckinCollection(CheckinCollection checkinCollection) {
		this.checkinCollection = checkinCollection;
	}

}
