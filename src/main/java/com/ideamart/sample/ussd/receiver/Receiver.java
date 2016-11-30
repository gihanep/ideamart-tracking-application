package com.ideamart.sample.ussd.receiver;

import com.ideamart.sample.common.Constants;
import com.ideamart.sample.lbs.LBS;
import com.ideamart.sample.sms.send.ScheduledMessage;
import com.ideamart.sample.sms.send.SendMessage;
import com.ideamart.sample.subcription.Subscription;
import com.ideamart.sample.usermgt.User;
import com.ideamart.sample.usermgt.UserDAO;
import hms.kite.samples.api.SdpException;
import hms.kite.samples.api.StatusCodes;
import hms.kite.samples.api.ussd.MoUssdListener;
import hms.kite.samples.api.ussd.UssdRequestSender;
import hms.kite.samples.api.ussd.messages.MoUssdReq;
import hms.kite.samples.api.ussd.messages.MtUssdReq;
import hms.kite.samples.api.ussd.messages.MtUssdResp;

import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Arrays;

/**
 * This class is created to receive USSD messages
 */
public class Receiver implements MoUssdListener {

    private UssdRequestSender ussdMtSender;
    private String[] privateNumbers = {"tel:AZ110N9CCX6oc2Vqnw+UnDAzB6SJcMF5CkK2UOEgTR2KwfaZ4KDZcwNDIq8viBORtMF6j"};
    //"tel:B%3C4mM3G8otswwsxt84tttry45JlO+MJQgz+kJXOiRgandOzuHzjyfZM+Y2ake+ExryL"

    @Override
    public void init() {
        try {
            ussdMtSender = new UssdRequestSender(new URL(Constants.ApplicationConstants.USSD_URL));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onReceivedUssd(MoUssdReq moUssdReq) {
        UserDAO userDAO = new UserDAO();
        if (Constants.ApplicationConstants.USSD_OP_MO_INIT.equals(moUssdReq.getUssdOperation())) {

            try {
                Subscription subscription = new Subscription();

                if (!userDAO.userAvailability(moUssdReq.getSourceAddress())) {
                    User user = new User(moUssdReq.getSourceAddress(), null, "1", moUssdReq.getMessage(), 1);
                    userDAO.AddUser(user);
                }
                if (subscription.getStatus(moUssdReq.getSourceAddress()) || (Arrays.asList(privateNumbers).contains(moUssdReq.getSourceAddress()))) {
                    userDAO.updateFlow(moUssdReq.getSourceAddress(), "2");
                    MtUssdReq request = createRequest(moUssdReq, Constants.ApplicationMessages.SUBSCRIBE_MESSAGE, Constants.ApplicationConstants.USSD_OP_MT_CONT);
                    sendRequest(request);
                } else {
                    userDAO.updateFlow(moUssdReq.getSourceAddress(), "1");
                    MtUssdReq request = createRequest(moUssdReq, Constants.ApplicationMessages.WELCOME_MSG, Constants.ApplicationConstants.USSD_OP_MT_CONT);
                    sendRequest(request);
                }


            } catch (SdpException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {

            try {
                String message = moUssdReq.getMessage();
                String flow = userDAO.getFlow(moUssdReq.getSourceAddress());
                if (flow.equals("1")) {
                    if (message.equals("1")) {
                        userDAO.updateFlow(moUssdReq.getSourceAddress(), "2");
                        Subscription subscription = new Subscription();
                        subscription.subscribeUser(moUssdReq.getSourceAddress());
                        MtUssdReq request = createRequest(moUssdReq, Constants.ApplicationMessages.SUBSCRIBE_MESSAGE, Constants.ApplicationConstants.USSD_OP_MT_CONT);
                        sendRequest(request);
                        ScheduledMessage scheduledMessage = new ScheduledMessage();
                        scheduledMessage.SendScheduledMessage(Constants.ApplicationMessages.WELCOME_SMS, moUssdReq, 13);
                    } else if (message.equals("99")) {
                        MtUssdReq request = createRequest(moUssdReq, Constants.ApplicationMessages.ExIT_MESSAGE, Constants.ApplicationConstants.USSD_OP_MT_CONT);
                        sendRequest(request);
                    } else {
                        userDAO.updateFlow(moUssdReq.getSourceAddress(), "1");
                        MtUssdReq request = createRequest(moUssdReq, Constants.ApplicationMessages.WELCOME_MSG, Constants.ApplicationConstants.USSD_OP_MT_CONT);
                        sendRequest(request);
                    }

                } else if (flow.equals("2")) {
                    if (message.equals("1")) {
                        userDAO.updateCount(moUssdReq.getSourceAddress());
                        userDAO.updateFlow(moUssdReq.getSourceAddress(), "3");
                        MtUssdReq request = createRequest(moUssdReq, Constants.ApplicationMessages.SEARCH_LOCATION, Constants.ApplicationConstants.USSD_OP_MT_CONT);
                        sendRequest(request);
                    } else if (message.equals("2")) {
                        userDAO.updateFlow(moUssdReq.getSourceAddress(), "3");
                        if (!userDAO.userPinAvailability(moUssdReq.getSourceAddress())) {
                            userDAO.AddUserPin(moUssdReq.getSourceAddress());
                        }
                        String pin = userDAO.getUserPinByAddress(moUssdReq.getSourceAddress());
//                        SendMessage sendMessage = new SendMessage();
//                        sendMessage.SendMessage("Your pin is: " + pin, moUssdReq.getApplicationId(), moUssdReq.getSourceAddress()
//                                , Constants.ApplicationConstants.PASSWORD, Constants.ApplicationConstants.SMS_URL);
                        MtUssdReq request = createRequest(moUssdReq, "Your pin is: " + pin + Constants.ApplicationMessages.USSD_COMMON_MSG, Constants.ApplicationConstants.USSD_OP_MT_CONT);
                        sendRequest(request);
                    } else if (message.equals("3")) {
                        userDAO.updateFlow(moUssdReq.getSourceAddress(), "3");
                        String pin = userDAO.getUserPinByAddress(moUssdReq.getSourceAddress());
//                        SendMessage sendMessage = new SendMessage();
//                        sendMessage.SendMessage("Your pin is: " + pin, moUssdReq.getApplicationId(), moUssdReq.getSourceAddress()
//                                , Constants.ApplicationConstants.PASSWORD, Constants.ApplicationConstants.SMS_URL);
                        MtUssdReq request = createRequest(moUssdReq, "Your pin is: " + pin + Constants.ApplicationMessages.USSD_COMMON_MSG, Constants.ApplicationConstants.USSD_OP_MT_CONT);
                        sendRequest(request);
                    } else if (message.equals("4")) {
                        userDAO.updateFlow(moUssdReq.getSourceAddress(), "3");
//                        SendMessage sendMessage = new SendMessage();
//                        sendMessage.SendMessage(Constants.ApplicationMessages.HELP_SMS, moUssdReq.getApplicationId(), moUssdReq.getSourceAddress()
//                                , Constants.ApplicationConstants.PASSWORD, Constants.ApplicationConstants.SMS_URL);
                        MtUssdReq request = createRequest(moUssdReq, Constants.ApplicationMessages.HELP_USSD + Constants.ApplicationMessages.USSD_COMMON_MSG, Constants.ApplicationConstants.USSD_OP_MT_CONT);
                        sendRequest(request);
                    } else if (message.equals("5")) {
                        userDAO.updateFlow(moUssdReq.getSourceAddress(), "3");
                        MtUssdReq request = createRequest(moUssdReq, Constants.ApplicationMessages.CONTACT, Constants.ApplicationConstants.USSD_OP_MT_CONT);
                        sendRequest(request);
                    } else if (message.equals("0")) {
                        userDAO.updateFlow(moUssdReq.getSourceAddress(), "1");
                        MtUssdReq request = createRequest(moUssdReq, Constants.ApplicationMessages.WELCOME_MSG, Constants.ApplicationConstants.USSD_OP_MT_CONT);
                        sendRequest(request);
                    } else if (message.equals("99")) {
                        MtUssdReq request = createRequest(moUssdReq, Constants.ApplicationMessages.ExIT_MESSAGE, Constants.ApplicationConstants.USSD_OP_MT_FIN);
                        sendRequest(request);
                    } else {
                        MtUssdReq request = createRequest(moUssdReq, Constants.ApplicationMessages.SUBSCRIBE_MESSAGE, Constants.ApplicationConstants.USSD_OP_MT_CONT);
                        sendRequest(request);
                    }
                } else if (flow.equals("3")) {
                    if (message.equals("0")) {
                        userDAO.updateFlow(moUssdReq.getSourceAddress(), "2");
                        MtUssdReq request = createRequest(moUssdReq, Constants.ApplicationMessages.SUBSCRIBE_MESSAGE, Constants.ApplicationConstants.USSD_OP_MT_CONT);
                        sendRequest(request);
                    } else if (message.equals("99")) {
                        MtUssdReq request = createRequest(moUssdReq, Constants.ApplicationMessages.ExIT_MESSAGE, Constants.ApplicationConstants.USSD_OP_MT_FIN);
                        sendRequest(request);
                    } else {
                        Subscription subscription = new Subscription();
                        userDAO.updateFlow(moUssdReq.getSourceAddress(), "3");
                        String address = userDAO.getUserAddressByPin(message);
                        if (address.equals("null")) {
                            MtUssdReq request = createRequest(moUssdReq, "Wrong pin\n0. Back\n99. Exit", Constants.ApplicationConstants.USSD_OP_MT_CONT);
                            sendRequest(request);
                        } else if (!subscription.getStatus(address)) {
                            MtUssdReq request = createRequest(moUssdReq, "Oba pin ankay yomu kala kena app eken unreg vi atha. " +
                                    "location eka balanna adala kena app eke reg wela sitiya yuthui\n0. Back\n99. Exit", Constants.ApplicationConstants.USSD_OP_MT_CONT);
                            sendRequest(request);
                        } else {
                            LBS lbs = new LBS();
                            String location = lbs.getLocation(address);
                            String finalMessage = location + "\n0. Back\n99. Exit";
                            MtUssdReq request = createRequest(moUssdReq, finalMessage, Constants.ApplicationConstants.USSD_OP_MT_CONT);
                            sendRequest(request);
                            SendMessage sendMessage = new SendMessage();
                            sendMessage.SendMessage("Your address is: " + location, moUssdReq.getApplicationId(), moUssdReq.getSourceAddress()
                                    , Constants.ApplicationConstants.PASSWORD, Constants.ApplicationConstants.SMS_URL);
                        }
                    }
                } else {
                    userDAO.updateFlow(moUssdReq.getSourceAddress(), "2");
                    MtUssdReq request = createRequest(moUssdReq, Constants.ApplicationMessages.SUBSCRIBE_MESSAGE, Constants.ApplicationConstants.USSD_OP_MT_CONT);
                    sendRequest(request);
                }

            } catch (SdpException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public MtUssdReq createRequest(MoUssdReq moUssdReq, String menuContent, String ussdOperation) {

        MtUssdReq request = new MtUssdReq();
        request.setApplicationId(moUssdReq.getApplicationId());
        request.setEncoding(moUssdReq.getEncoding());
        request.setMessage(menuContent);
        request.setPassword(Constants.ApplicationConstants.PASSWORD);
        request.setSessionId(moUssdReq.getSessionId());
        request.setUssdOperation(ussdOperation);
        request.setVersion(moUssdReq.getVersion());
        request.setDestinationAddress(moUssdReq.getSourceAddress());
        return request;
    }

    public MtUssdResp sendRequest(MtUssdReq request) throws SdpException {
        // sending request to service
        MtUssdResp response = null;
        try {
            System.out.println();
            response = ussdMtSender.sendUssdRequest(request);
        } catch (SdpException e) {
            throw e;
        }

        // response status
        String statusCode = response.getStatusCode();
        String statusDetails = response.getStatusDetail();
        if (StatusCodes.SuccessK.equals(statusCode)) {
            System.out.println("Message sent succeeded");
        } else {
            System.out.println("Message sent failed");
        }
        return response;
    }
}
