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
package com.ideamart.sample.sms.receive;

import com.ideamart.sample.sms.operations.Operations;
import hms.kite.samples.api.sms.MoSmsListener;
import hms.kite.samples.api.sms.messages.MoSmsReq;

/**
 * This class is created for receive messages.
 */
public class Receiver implements MoSmsListener {
    @Override
    public void init() {

    }

    @Override
    public void onReceivedSms(MoSmsReq moSmsReq) {

        try {
            String message = moSmsReq.getMessage();
            String [] messageParts = message.split(" ");
            String operation = messageParts[1].toLowerCase();
            Operations operations = new Operations();

            if(operation.equals("gps")) {
                operations.getLocation(moSmsReq.getSourceAddress(), messageParts[2]);
            } else {
                operations.errorMessage(moSmsReq.getSourceAddress());
            }
        } catch (Exception e) {
            Operations operations = new Operations();
            operations.errorMessage(moSmsReq.getSourceAddress());
            e.printStackTrace();

        }

    }
}
