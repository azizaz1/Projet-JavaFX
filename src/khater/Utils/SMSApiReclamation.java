package khater.Utils;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

/**
 *
 * @author Admin
 */
public class SMSApiReclamation {

    public SMSApiReclamation() {
    }
    public static final String ACCOUNT_SID = "ACacbede5b5cfa2780654e31a2226232c0";
    public static final String AUTH_TOKEN = "a87b68f3d401f3d773ae516a9566daea";

    public void sendSMS(String num, String msg) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        Message message = Message.creator(new PhoneNumber("+21622204202"),
                new PhoneNumber("+16812502823"),
                "Reclamation Créer avec succée, " + msg).create();

        System.out.println(message.getSid());

    }
}
