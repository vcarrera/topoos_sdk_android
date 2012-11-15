package topoos.POI;

import java.util.List;

import android.content.Context;
import topoos.AccessTokenOAuth;
import topoos.APIAccess.APICaller;
import topoos.APIAccess.Operations.*;
import topoos.APIAccess.Results.*;
import topoos.APIAccess.Results.Objects.*;

class Translator {

	private static String method = "GET";
	private static String format = "json";
	private static Integer version = topoos.Constants.APIVERSION;

	public static POI Add(String name, Double lat, Double lng,
			Integer[] categories, Double accuracy, Double vaccuracy,
			Double elevation, String desc, String address, String cross_street,
			String city, String country, String postal_code, String phone,
			String twitter, AccessTokenOAuth accessTokenPregenerated) {
		POI poi = null;
		if (accessTokenPregenerated.isValid()) {

			String strcategories = "";
			for (int i = 0; i < categories.length; i++) {
				if (i < categories.length - 1) {
					strcategories = strcategories + categories[i] + ",";
				} else {
					strcategories = strcategories + categories[i];
				}
			}
			POISAdd pOISAdd = new POISAdd("Add", method, format, version,
					accessTokenPregenerated.getAccessToken(), lat, lng,
					accuracy, vaccuracy, elevation, strcategories, name, desc,
					address, cross_street, city, country, postal_code, phone,
					twitter);
			POIResult poiResult = new POIResult();
			APICaller.ExecuteOperation(pOISAdd, poiResult);
			poi = poiResult.getPoi();
		}
		return poi;
	}

	public static POI Get(Integer POIID,
			AccessTokenOAuth accessTokenPregenerated) {
		POI poi = null;
		if (accessTokenPregenerated.isValid()) {
			POISGet pOISGet = new POISGet("Get", method, format, version,
					accessTokenPregenerated.getAccessToken(), POIID);
			POIResult poiResult = new POIResult();
			APICaller.ExecuteOperation(pOISGet, poiResult);
			poi = poiResult.getPoi();
		}
		return poi;
	}

	public static List<POI> GetNear(Double lat, Double lng, Integer radius,
			Integer[] categories, AccessTokenOAuth accessTokenPregenerated) {
		List<POI> poilist = null;
		if (accessTokenPregenerated.isValid()) {
			String strcategories = "";
			for (int i = 0; i < categories.length; i++) {
				if (i < categories.length - 1) {
					strcategories = strcategories + categories[i] + ",";
				} else {
					strcategories = strcategories + categories[i];
				}
			}
			POISGet_near pOISGet_near = new POISGet_near("GetNear", method,
					format, version, accessTokenPregenerated.getAccessToken(),
					lat, lng, radius, strcategories);
			POICollectionResult pOICollectionResult = new POICollectionResult();
			APICaller.ExecuteOperation(pOISGet_near, pOICollectionResult);
			poilist = pOICollectionResult.getPoicollection().getPoiList();
		}
		return poilist;
	}

	public static List<POI> GetWhere(Integer[] categories, Integer[] POIS, String city,
			String country, String postal_code, String q,
			AccessTokenOAuth accessTokenPregenerated) {
		List<POI> poilist = null;
		if (accessTokenPregenerated.isValid()) {
			String strcategories = "";
			for (int i = 0; i < categories.length; i++) {
				if (i < categories.length - 1) {
					strcategories = strcategories + categories[i] + ",";
				} else {
					strcategories = strcategories + categories[i];
				}
			}
			String strpois = "";
			for (int i = 0; i < POIS.length; i++) {
				if (i < POIS.length - 1) {
					strpois = strpois + POIS[i] + ",";
				} else {
					strpois = strpois + POIS[i];
				}
			}
			POISGet_where pOISGet_where = new POISGet_where("GetWhere", method,
					format, version, accessTokenPregenerated.getAccessToken(),
					strpois, strcategories, city, country, postal_code, q);
			POICollectionResult pOICollectionResult = new POICollectionResult();
			APICaller.ExecuteOperation(pOISGet_where, pOICollectionResult);
			poilist = pOICollectionResult.getPoicollection().getPoiList();
		}
		return poilist;
	}

	public static Boolean Update(Integer POIID, String name, Double lat,
			Double lng, Integer[] categories, Double accuracy, Double vaccuracy,
			Double elevation, String desc, String address, String cross_stret,
			String city, String country, String postal_code, String phone,
			String twitter, AccessTokenOAuth accessTokenPregenerated) {
		POI poi = null;
		if (accessTokenPregenerated.isValid()) {
			String strcategories = "";
			for (int i = 0; i < categories.length; i++) {
				if (i < categories.length - 1) {
					strcategories = strcategories + categories[i] + ",";
				} else {
					strcategories = strcategories + categories[i];
				}
			}
			POISUpdate pOISUpdate = new POISUpdate("Update", method, format,
					version, accessTokenPregenerated.getAccessToken(), POIID,
					lat, lng, accuracy, vaccuracy, elevation, strcategories);
			POIResult poiResult = new POIResult();
			APICaller.ExecuteOperation(pOISUpdate, poiResult);
			poi = poiResult.getPoi();
		}
		return poi != null;
	}

	public static Boolean Delete(Integer POIID,
			AccessTokenOAuth accessTokenPregenerated) {
		boolean delete = false;
		if (accessTokenPregenerated.isValid()) {
			POISDelete pOISDelete = new POISDelete("Delete", method, format,
					version, accessTokenPregenerated.getAccessToken(), POIID);
			GenericResult genericResult = new GenericResult();
			APICaller.ExecuteOperation(pOISDelete, genericResult);
			delete = genericResult.getCode() == 200;
		}
		return delete;
	}

	public static POI Add(Context context, String name, Double lat, Double lng,
			Integer[] categories, Double accuracy, Double vaccuracy,
			Double elevation, String desc, String address, String cross_street,
			String city, String country, String postal_code, String phone,
			String twitter) {
		AccessTokenOAuth accessTokenPregenerated = new AccessTokenOAuth();
		accessTokenPregenerated.Load_Token(context);
		return Add(name, lat, lng, categories, accuracy, vaccuracy, elevation,
				desc, address, cross_street, city, country, postal_code, phone,
				twitter, accessTokenPregenerated);
	}

	public static POI Get(Context context, Integer POIID) {
		AccessTokenOAuth accessTokenPregenerated = new AccessTokenOAuth();
		accessTokenPregenerated.Load_Token(context);
		return Get(POIID, accessTokenPregenerated);
	}

	public static List<POI> GetNear(Context context, Double lat, Double lng,
			Integer radius, Integer[] categories) {
		AccessTokenOAuth accessTokenPregenerated = new AccessTokenOAuth();
		accessTokenPregenerated.Load_Token(context);
		return GetNear(lat, lng, radius, categories, accessTokenPregenerated);

	}

	public static List<POI> GetWhere(Context context, Integer[] categories,
			Integer[] POIS, String city, String country, String postal_code,
			String q) {
		AccessTokenOAuth accessTokenPregenerated = new AccessTokenOAuth();
		accessTokenPregenerated.Load_Token(context);
		return GetWhere(categories, POIS, city, country, postal_code, q,
				accessTokenPregenerated);
	}

	public static Boolean Update(Context context, Integer POIID, String name,
			Double lat, Double lng, Integer[] categories, Double accuracy,
			Double vaccuracy, Double elevation, String desc, String address,
			String cross_stret, String city, String country,
			String postal_code, String phone, String twitter) {
		AccessTokenOAuth accessTokenPregenerated = new AccessTokenOAuth();
		accessTokenPregenerated.Load_Token(context);
		return Update(POIID, name, lat, lng, categories, accuracy, vaccuracy,
				elevation, desc, address, cross_stret, city, country,
				postal_code, phone, twitter, accessTokenPregenerated);
	}

	public static Boolean Delete(Context context, Integer POIID) {
		AccessTokenOAuth accessTokenPregenerated = new AccessTokenOAuth();
		accessTokenPregenerated.Load_Token(context);
		return Delete(POIID, accessTokenPregenerated);
	}

}
