package azyz.Utils;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

/**
 *
 * @author azizf
 */
public class SMSApiProduit {

    public SMSApiProduit() {
    }
    public static final String ACCOUNT_SID = "AC555fa1feb2ca36005c69abb9ba4c9645";
    public static final String AUTH_TOKEN = "138cad4a48c8d7e650ef12e49c0221cf";

    public void sendSMS(String num, String msg) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        Message message = Message.creator(new PhoneNumber("+21650871280"),
                new PhoneNumber("+17407154037"),
                "Votre nouveau Produit est ajouté avec succée, " + msg).create();

        System.out.println(message.getSid());

    }
}
