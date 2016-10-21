package com.ideamart.sample.subcription;

import com.ideamart.sample.common.Constants;
import hms.kite.samples.api.SdpException;
import hms.kite.samples.api.subscription.SubscriptionRequestSender;
import hms.kite.samples.api.subscription.messages.SubscriptionRequest;
import hms.kite.samples.api.subscription.messages.SubscriptionResponse;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by tharinda on 10/21/16.
 */
public class Subscription {

    private String subscriptionStatus;

    public String subscribeUser(String address) throws MalformedURLException {
        SubscriptionRequestSender subscriptionRequestSender =
                new SubscriptionRequestSender(new URL(Constants.ApplicationConstants.SUBSCRIPTION_MESSAGE_URL));

        SubscriptionRequest subscriptionRequest = new SubscriptionRequest();
        subscriptionRequest.setApplicationId(Constants.ApplicationConstants.APP_ID);
        subscriptionRequest.setPassword(Constants.ApplicationConstants.PASSWORD);
        subscriptionRequest.setSubscriberId(address);
        subscriptionRequest.setAction(Constants.ApplicationConstants.REG_ACTION);
        subscriptionRequest.setVersion(Constants.ApplicationConstants.VERSION);

        try {
            // Get SubscriptionResponse and assign Subscription Register Status to display
            SubscriptionResponse subscriptionResponse
                    = subscriptionRequestSender.sendSubscriptionRequest(subscriptionRequest);
            return subscriptionResponse.getStatusDetail();
        } catch (SdpException e) {
            System.out.println("Error Occurred due to" + e);
        }
        return null;
    }
}
