package com.sap.reversal.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.util.StringUtils;

import com.sap.reversal.constant.CommonConstants;
import com.sap.reversal.exception.RequestValidationException;

public class ValidationUtil {

	public static String validatePayload(String payload) throws RequestValidationException, ParseException {

		if (payload == null) {
			throw new RequestValidationException();
		}
		JSONParser parser = new JSONParser();
		JSONObject json = (JSONObject) parser.parse(payload);

		String payloadStr = (String) json.get(CommonConstants.REQUEST_KEY);
		if (ValidationUtil.isNullOrEmpty(payloadStr)) {
			throw new RequestValidationException();
		}
		return payloadStr;
	}

	public static boolean isNull(String value) {
		return StringUtils.isEmpty(value);
	}

	public static boolean isEmpty(String value) {
		return StringUtils.isEmpty(value);
	}

	public static boolean isNullOrEmpty(String... value) {
		for (String s : value) {
			if (StringUtils.isEmpty(s)) {
				return true;
			}
		}
		return false;
	}

	public static boolean isValid(String value, String pattern) {
		Pattern r = Pattern.compile(pattern);
		Matcher m = r.matcher(value);
		return m.matches();
	}
}
