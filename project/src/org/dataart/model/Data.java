package org.dataart.model;

import java.util.ArrayList;
import java.util.HashMap;

@SuppressWarnings("serial")
public class Data extends ArrayList<HashMap<String, Object>> {
	public static enum DATA_TYPES {
		INVALID, CALENDAR, DOUBLE, FLOAT, INTEGER, LONG, PATH, STRING
	};

	/**
	 * Returns the type of Object obj. Uses 'instanceof'.
	 * @param obj Object to test
	 * @return DATA_TYPES value associated with the given type.
	 */
	public static DATA_TYPES getDataType(Object obj) {
		if (obj instanceof java.util.Calendar) {
			return DATA_TYPES.CALENDAR;
		} else if (obj instanceof Double) {
			return DATA_TYPES.DOUBLE;
		} else if (obj instanceof Float) {
			return DATA_TYPES.FLOAT;
		} else if (obj instanceof Integer) {
			return DATA_TYPES.INTEGER;
		} else if (obj instanceof java.nio.file.Path) {
			return DATA_TYPES.PATH;
		} else if (obj instanceof Long) {
			return DATA_TYPES.LONG;
		} else if (obj instanceof String) {
			return DATA_TYPES.STRING;
		}

		return DATA_TYPES.INVALID;
	}

	/**
	 * Returns true if this type is a number type and can be added
	 * 
	 * @param obj
	 *            the Object to test
	 * @return true if this is a number type
	 */
	public static boolean isNumberType(Object obj) {
		DATA_TYPES type = getDataType(obj);
		if (type == DATA_TYPES.DOUBLE || type == DATA_TYPES.FLOAT
				|| type == DATA_TYPES.INTEGER || type == DATA_TYPES.LONG) {
			return true;
		} else {
			return false;
		}
	}
}
