package com.mojilala.simple_sms.serverless;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.mojilala.simple_sms.SimpleSMS;
import org.apache.log4j.Logger;

import java.util.Map;

public class Handler implements RequestHandler<Map<String, Object>, String> {

	private static final Logger LOG = Logger.getLogger(Handler.class);

	@Override
	public String handleRequest(Map<String, Object> input, Context context) {
        LOG.info("received: " + input);

        Map<String, Object> body = (Map<String, Object>) input.get("body");

        if (!body.containsKey("message") || !body.containsKey("phoneNumber"))
            return new ErrorResponse("message & phoneNumber body attributes cannot be blank!").toJson();

        String senderId = (String) body.getOrDefault("senderId", "Mojilala");
        String smsType = (String) body.getOrDefault("smsType", "Transactional");

        SimpleSMS simpleSMS = new SimpleSMS(senderId, smsType);


        String message = (String) body.get("message");
        String phoneNumber = (String) body.get("phoneNumber");

        String res;
        try {
            res = simpleSMS.sendSMSMessage(message, phoneNumber);
            LOG.info("Sent an SMS '" + message + "' to " + phoneNumber + " : " + res);
        } catch (Exception e) {
            LOG.error("An error occured: " + e.getMessage());
            res = new ErrorResponse("An error occured: " + e).toJson();
        }

        return res;
    }
}
