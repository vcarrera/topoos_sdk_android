package topoos.Rules;

import java.io.IOException;

import android.content.Context;
import topoos.AccessTokenOAuth;
import topoos.APIAccess.APICaller;
import topoos.APIAccess.Operations.*;
import topoos.APIAccess.Results.*;
import topoos.APIAccess.Results.Objects.*;
import topoos.Exception.TopoosException;
/**
 * 
 * @author MAJS
 *
 */
class Translator {
	private static String method = "GET";
	private static String format = "json";
	private static Integer version = topoos.Constants.APIVERSION;

	
	public static Rule AddTrackOutOfBounds (Double lat, Double lng, Integer radius, AccessTokenOAuth accessTokenPregenerated) throws IOException, TopoosException{
		Rule Rule = null;
		if (accessTokenPregenerated.isValid()) {
			RulesAdd rulesAdd = new RulesAdd("Get",
					method, format, version,
					accessTokenPregenerated.getAccessToken(),lat,lng,radius,RulesAdd.TYPE_TRACK_OUT_OF_BOUNDS);
			RuleResult ruleResult = new RuleResult();
			APICaller.ExecuteOperation(rulesAdd, ruleResult);
			Rule = ruleResult.getRule();
		} else { throw new TopoosException(TopoosException.NOT_VALID_TOKEN); }
		return Rule;
		
	}
	
	public static Rule AddTrackOutOfBounds (Context context, Double lat, Double lng, Integer radius) throws IOException, TopoosException{
		AccessTokenOAuth accessTokenPregenerated = new AccessTokenOAuth();
		accessTokenPregenerated.Load_Token(context);
		return AddTrackOutOfBounds(lat, lng, radius, accessTokenPregenerated);
		
	}
}