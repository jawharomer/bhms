package com.joh.bhms.commons;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class CusTimeDeSerializer extends JsonDeserializer<Date> {

	private static final Logger logger = Logger.getLogger(CusTimeDeSerializer.class);

	@Override
	public Date deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
		// System.out.println("Deserializer");
		DateFormat df = new SimpleDateFormat("hh:mm");

		Date date;
		try {
			date = df.parse(p.getText());
		} catch (ParseException e) {
			// e.printStackTrace();
			logger.debug("can not parse time not valied time=" + p.getText());
			return null;
		}
		// System.out.println("Date and Time: " + date);

		return date;
	}

}
