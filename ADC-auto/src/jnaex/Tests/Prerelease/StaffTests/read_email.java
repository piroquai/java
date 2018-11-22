package jnaex.Tests.Prerelease.StaffTests;

import jnaex.RFWin.Proc;

import java.util.Properties;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Store;

/**
 * Created by Evgenia on 13-Jan-17.
 */
public class read_email
{
    public static void main(String[] args) {
        Proc.setLogScenarioPrefix("ReadEmail");
        Properties props = new Properties();
        props.setProperty("mail.store.protocol", "imaps");
        try {
            Proc.setLogStepPrefix("I");
            Session session = Session.getInstance(props, null);
            Store store = session.getStore();
            store.connect("imap.yandex.ru", "zhesia13@yandex.ru", "akunamatata");
            Folder folder = store.getFolder("INBOX");
            folder.open(Folder.READ_ONLY);
            Proc.setLogStepPrefix("II");
            Message message[] = folder.getMessages();
            for (int i = 0, n = message.length; i < n; i++) {

                System.out.println(i + ": " + message[i].getFrom()[0] + "\t"
                        + message[i].getSubject());
                String content = message[i].getContent().toString();
                if (content.length() > 200) {
                    content = content.substring(0, 200);
                }
                System.out.print(content);
            }
        } catch (Exception mex) {
            mex.printStackTrace();
        }
    }
}
