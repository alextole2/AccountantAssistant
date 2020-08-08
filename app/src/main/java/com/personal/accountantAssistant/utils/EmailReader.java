package com.personal.accountantAssistant.utils;

import android.util.Log;

import java.util.Properties;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Store;

public class EmailReader extends javax.mail.Authenticator {
    //private static final String TAG = "GMailReader";

    private String mailhost = "imap.gmail.com";
    private Session session;
    private Store store;

    public EmailReader(String user, String password) {

        Properties props = System.getProperties();

        if (ParserUtils.isNullObject(props)) {
            System.out.println("Properties are null !!");
        } else {
            props.setProperty("mail.store.protocol", "imaps");

            /*Log.d(TAG, "Transport: " + props.getProperty("mail.transport.protocol"));
            Log.d(TAG, "Store: " + props.getProperty("mail.store.protocol"));
            Log.d(TAG, "Host: " + props.getProperty("mail.imap.host"));
            Log.d(TAG, "Authentication: " + props.getProperty("mail.imap.auth"));
            Log.d(TAG, "Port: " + props.getProperty("mail.imap.port"));*/
        }

        try {
            session = Session.getDefaultInstance(props, null);
            store = session.getStore("imaps");
            store.connect(mailhost, user, password);
            System.out.println("Store: " + store.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public synchronized Message[] readMail() throws Exception {
        Message[] messages = new Message[]{};
        try {
            Folder folder = store.getFolder("Inbox");
            folder.open(Folder.READ_ONLY);
            /* TODO to rework
            Message[] msgs = folder.getMessages(1, 10);
            FetchProfile fp = new FetchProfile();
            fp.add(FetchProfile.Item.ENVELOPE);
            folder.fetch(msgs, fp);
            */
            messages = folder.getMessages();
        } catch (Exception e) {
            Log.e("readMail", e.getMessage(), e);
        }
        return messages;
    }
}
