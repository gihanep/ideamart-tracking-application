/*
 *Copyright 2015 Tharinda Dilshan Ehelepola
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.ideamart.sample.sms.operations;

import com.ideamart.sample.common.Constants;
import com.ideamart.sample.lbs.LBS;
import com.ideamart.sample.sms.send.SendMessage;
import com.ideamart.sample.usermgt.UserDAO;
import hms.kite.samples.api.sms.messages.MoSmsReq;

/**
 * This class is created for do operations for messages.
 */
public class Operations {

    public void passToDatabase(MoSmsReq moSmsReq) {

        SendMessage sendMessage = new SendMessage();
        sendMessage.SendMessage("Message received",moSmsReq.getApplicationId(),moSmsReq.getSourceAddress()
                , Constants.ApplicationConstants.PASSWORD, Constants.ApplicationConstants.SMS_URL);
    }

    public void getLocation(String userAddress, String pin) throws Exception {
        UserDAO userDAO = new UserDAO();
        userDAO.updateCount(userAddress);
        String address = userDAO.getUserAddressByPin(pin);
        if(address.equals("null")) {
            SendMessage sendMessage = new SendMessage();
            sendMessage.SendMessage("Pin you Entered is not registered", Constants.ApplicationConstants.APP_ID , userAddress
                    , Constants.ApplicationConstants.PASSWORD, Constants.ApplicationConstants.SMS_URL);
        } else {
            LBS lbs = new LBS();
            String finalMessage = lbs.getLocation(address);
            SendMessage sendMessage = new SendMessage();
            sendMessage.SendMessage(finalMessage, Constants.ApplicationConstants.APP_ID , userAddress
                    , Constants.ApplicationConstants.PASSWORD, Constants.ApplicationConstants.SMS_URL);
        }
    }

    public void errorMessage(String address) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.SendMessage("Message format error", Constants.ApplicationConstants.APP_ID , address
                , Constants.ApplicationConstants.PASSWORD, Constants.ApplicationConstants.SMS_URL);
    }
}
