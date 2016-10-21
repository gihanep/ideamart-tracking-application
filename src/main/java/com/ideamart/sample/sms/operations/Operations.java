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

import com.ideamart.sample.sms.db.dbClass;
import com.ideamart.sample.sms.send.SendMessage;
import hms.kite.samples.api.sms.messages.MoSmsReq;
import java.util.HashMap;

/**
 * This class is created for do operations for messages.
 */
public class Operations {

    private String message;
    private HashMap<String, String> map;

    public Operations(String message) {
        this.message = message;
    }

    public void passToDatabase(MoSmsReq moSmsReq) {
        String [] messageParts = message.split(" ");
        if(messageParts.length == 3) {
            map = dbClass.getDBInstance();
            map.put(messageParts[1], messageParts[2]);
            SendMessage sendMessage = new SendMessage();
            //:TODO: Password and URL should be replaced when you hosting the application
            sendMessage.SendMessage("Your Message Saved",moSmsReq.getApplicationId(),moSmsReq.getSourceAddress()
                    ,"password","http://127.0.0.1:7000/sms/send");
        } else {
            SendMessage sendMessage = new SendMessage();
            sendMessage.SendMessage("Message format is wrong",moSmsReq.getApplicationId(),moSmsReq.getSourceAddress()
                    ,"password","http://127.0.0.1:7000/sms/send");
        }
    }
}
