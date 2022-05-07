package azyz.Utils;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

/**
 *
 * @author azizf
 */
public class SMSApiCategory {

    public SMSApiCategory() {
    }
    public static final String ACCOUNT_SID = "AC4fa2168ed39dc6c0610707e761051830";
    public static final String AUTH_TOKEN = "36cd071a757f23ac9c607a7edbb79e2f";

    public void sendSMS(String num, String msg) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        Message message = Message.creator(new PhoneNumber("+21626457879"),
                new PhoneNumber("+13203445177"),
                "Votre nouveau Category est ajouté avec succée, " + msg).create();

        System.out.println(message.getSid());

    }
}
