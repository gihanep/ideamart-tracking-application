package com.ideamart.sample.common;

/**
 * Created by Ehelepola on 20/01/2016.
 */
public final class Constants {

    public static final class ApplicationConstants {

        public static final String USSD_OP_MO_INIT = "mo-init";
        public static final String USSD_OP_MT_CONT = "mt-cont";
        public static final String USSD_OP_MT_FIN = "mt-fin";
        public static final String USSD_URL = "http://127.0.0.1:7000/ussd/send";
        public static final String APP_ID = "APP_00001";
        public static final String PASSWORD = "password";
        public static final String SMS_URL = "http://127.0.0.1:7000/sms/send";
        public static final String JDBC_URL = "jdbc:mysql://localhost:3306/Pradeep";
        public static final String DATABASE_USERNAME = "root";
        public static final String DATABASE_PASSWORD = "";

        // Subscription Message Url (OPT_IN/OPT_OUT)
        public static final String SUBSCRIPTION_MESSAGE_URL = "http://127.0.0.1:7000/subscription/send";

        // Subscription BaseSize Url for get the Base Size.
        public static final String SUBSCRIPTION_BASESIZE_URL = "http://127.0.0.1:7000/subscription/query-base";

        // Subscription Status Url
        public static final String SUBSCRIPTION_STATUS_URL = "http://127.0.0.1:7000/subscription/getStatus";
        // For registration action should be 1 (0 - Opt Out | 1 - Opt In)
        public static final String REG_ACTION = "1";

        //Specific Version
        public static final String VERSION = "1.0";

        public static final String LBS_URL = "http://127.0.0.1:7000/lbs/locate";
    }

    public static final class ApplicationMessages {
        public static final String WELCOME_MSG = "1. Subscribe\n99. Exit";
        public static final String SUBSCRIBE_MESSAGE = "1. Search Location\n2. Register For Pin\n3. Forget Pin\n4. Help\n5. Contact us\n0. Back\n99. Exit";
        public static final String SEARCH_LOCATION = "obata soyanna awashya kenage pin ankaya athulath karanna";
        public static final String REGISTER_FOR_PIN = "keti paniwidayakin obe pin ankaya labenu atha.\n" +
                "0. Back\n" +
                "99. Exit";
        public static final String HELP_MESSAGE = "Keti Paniwidayak Magein Siyalu Thorathuru Labenu Atha.\n0. Back\n99. Exit";
        public static final String CONTACT = "Sp Name:-S.M.P.K Senevirathna\n" +
                "       App Developer Details: tharinda221@gmail.com.\n" +
                "       \n0. Back\n99.Exit";
        public static final String HELP_SMS = "welcome to (app eke name) ussd application.\n" +
                "               Inna Thana Balaganimata :- (app keyword) <space> GPS <space> oba soyana kenage Pin Ankaya.\n" +
                "               sent to(app number)\n";
        public static final String ExIT_MESSAGE = "Thank you for using. come again!";

    }
}
