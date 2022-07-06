package com.sap.reversal.rest;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sap.reversal.constant.CommonConstants;
import com.sap.reversal.exception.ApplicationException;
import com.sap.reversal.exception.RequestValidationException;
import com.sap.reversal.helper.WordReverseHelper;
import com.sap.reversal.util.ValidationUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = "/word/reversal")
@Api(tags = { "word-reversal-service" })
@Slf4j
public class WordReverseController extends AbstractRestHandler {

	@Autowired
	private WordReverseHelper wordReverseHelper;
	
	@Autowired
	private MessageSource messages;

	@RequestMapping(value = "/rearrange/string", method = RequestMethod.GET, produces = { "application/json" })
	@ApiOperation(
			value = "ReArrange words in reversal order for small string size or a line", response = JSONObject.class, 
			notes = "All the characters of the words in the given payload string will get reversed. Position of the words and punctuation symbols will remain intact")
	public JSONObject reArrangeString(@RequestParam String payload) throws Exception {
		log.debug("Requested payload is : " + payload);
		return validateAndReverse(payload);
	}

	
	@RequestMapping(value = "/rearrange/text", method = RequestMethod.POST, produces = { "application/json" })
	@ApiOperation(
		value = "ReArrange words in reversal order for large string size or Text", response = JSONObject.class, 
		notes = "All the characters of the words in the given payload text will get reversed. Position of the words and punctuation symbols will remain intact")
	public JSONObject reArrangeText(@RequestBody String payload) throws Exception {
		log.debug("Requested payload is : " + payload);
		return validateAndReverse(payload);
	}
	
	@SuppressWarnings("unchecked")
	private JSONObject validateAndReverse(String payload) throws ParseException, NoSuchMessageException, ApplicationException {
		String payloadString = null;
		try {
			payloadString = ValidationUtil.validatePayload(payload);
		} catch (RequestValidationException e) {
			throw new RequestValidationException(messages.getMessage("err.invalid.payload.type", null, null));
		}catch (ParseException e) {
			throw new ApplicationException(messages.getMessage("err.general.error.message", null, null));
		}
		JSONObject outputJsonObj = new JSONObject();
	    outputJsonObj.put(CommonConstants.RESPONSE_KEY, wordReverseHelper.reArrangeString(payloadString));
		return outputJsonObj;
	}
}
