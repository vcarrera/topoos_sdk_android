package topoos.APIAccess.Operations;

import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
/**
 * Class that implements the utils of Api
 * @author MAJS
 *
 */
class APIUtils {
	/**
	 * Convert to String a date that is received as parameter
	 * @param date
	 * @return String
	 */
	
	public static String toStringDate(Date date){
		String formatoFecha = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";
		SimpleDateFormat sdf = new SimpleDateFormat(formatoFecha);
		sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
		return date== null? null : URLEncoder.encode(sdf.format(date));
	}
	/**
	 * Convert to String a double value that received as parameter
	 * @param doub
	 * @return String
	 */
	public static String toStringDouble(Double doub){
		return doub==null? null:URLEncoder.encode(Double.toString(doub).replace(',',
				'.'));
	}
	
	/**
	 * Convert to String an integer value that received as parameter 
	 * @param inte
	 * @return String
	 */
	public static String toStringInteger(Integer inte){
		return inte==null? null:URLEncoder.encode(Integer.toString(inte));
	}
	/**
	 * Convert to String an url that received as parameter
	 * @param str
	 * @return String
	 */
	public static String toStringUrlEncoded(String str){
		return str==null? null:URLEncoder.encode(str);
	}
	/**
	 *  Convert to String an boolean value that received as parameter
	 * @param bool
	 * @return String
	 */
	public static String toStringBoolean(Boolean bool){
		return bool==null? null:URLEncoder.encode(Boolean.toString(bool));
	}
}
