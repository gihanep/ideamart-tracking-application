package com.ideamart.sample.common;

/**
 * Created by Ehelepola on 20/01/2016.
 */
public final class Constants {

    public static final class ApplicationConstants {

        //IdeaMart APIs local
        public static final String USSD_URL = "http://127.0.0.1:7000/ussd/send";
        public static final String SMS_URL = "http://127.0.0.1:7000/sms/send";
        public static final String SUBSCRIPTION_MESSAGE_URL = "http://127.0.0.1:7000/subscription/send";
        public static final String SUBSCRIPTION_BASESIZE_URL = "http://127.0.0.1:7000/subscription/query-base";
        public static final String SUBSCRIPTION_STATUS_URL = "http://127.0.0.1:7000/subscription/getStatus";
        public static final String LBS_URL = "http://127.0.0.1:7000/lbs/locate";

        //IdeaMart APIs public
//        public static final String USSD_URL = "http://api.dialog.lk/ussd/send";
//        public static final String SMS_URL = "http://api.dialog.lk/sms/send";
//        public static final String SUBSCRIPTION_MESSAGE_URL = "http://api.dialog.lk/subscription/send";
//        public static final String SUBSCRIPTION_BASESIZE_URL = "http://api.dialog.lk/subscription/query-base";
//        public static final String SUBSCRIPTION_STATUS_URL = "http://api.dialog.lk/subscription/getStatus";
//        public static final String LBS_URL = "http://api.dialog.lk/lbs/locate";

        public static final String USSD_OP_MO_INIT = "mo-init";
        public static final String USSD_OP_MT_CONT = "mt-cont";
        public static final String USSD_OP_MT_FIN = "mt-fin";

        public static final String APP_ID = "APP_00001";
        public static final String PASSWORD = "password";

        public static final String JDBC_URL = "jdbc:mysql://localhost:3306/Pradeep";
        public static final String DATABASE_USERNAME = "root";
        public static final String DATABASE_PASSWORD = "";
        public static final String REG_ACTION = "1";
        public static final String VERSION = "1.0";


    }

    public static final class ApplicationMessages {
        public static final String WELCOME_MSG = "1. Register wenna\n99. Exit";
        public static final String SUBSCRIBE_MESSAGE = "1. Location balanna\n2. Pin ekak ganna\n3. Pin eka balanna\n4. Help\n5. Contact us\n0. Back\n99. Exit";
        public static final String SEARCH_LOCATION = "obata soyanna kenage pin ankaya athulath karanna(labagen naththam (number) eyage " +
                "phone eken dial karala laba ganna)";
        public static final String USSD_COMMON_MSG = "\n" +
                "0. Back\n" +
                "99. Exit";
        public static final String HELP_MESSAGE = "Keti Paniwidayak Magein Siyalu Thorathuru Labenu Atha.\n0. Back\n99. Exit";
        public static final String CONTACT = "facebook: fb.com/mytracker.\n" +
                "       \n0. Back\n99.Exit";
        public static final String HELP_SMS = "welcome to (app eke name) ussd application.\n" +
                "               Inna Thana Balaganimata :- (app keyword) <space> GPS <space> oba soyana kenage Pin Ankaya.\n" +
                "               sent to(app number)\n";
        public static final String HELP_USSD = "(number) dial kara Subscribe karanna -> Pin ekak ganna -> " +
                "Location select karama oyage ho adala kenage pin eka athul karanna";
        public static final String ExIT_MESSAGE = "Thank you for using. come again!";

    }
}
