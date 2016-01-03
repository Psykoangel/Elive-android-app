package fr.elive.android.app.wrapper;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.os.Parcelable;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;

/**
 * Created by Psyko on 29/12/2015.
 */
public class NfcWrapper {
    private static NfcWrapper instance = new NfcWrapper();

    public static NfcWrapper Instance() {
        return instance;
    }

    public static final String MIME_TEXT_PLAIN = "text/plain";

    private NfcAdapter mNfcAdapter;
    private Context mContext;

    private NfcWrapper() {
        mContext = null;
    }

    public boolean setNfcAdapter(Context context){
        if (mContext == null) {
            mContext = context;
        }

        mNfcAdapter = NfcAdapter.getDefaultAdapter(mContext);

        if (mNfcAdapter == null) {
            Toast.makeText(mContext, "This device doesn't support NFC.", Toast.LENGTH_LONG).show();
            return false;
        }

        if (!mNfcAdapter.isEnabled()) {
            Toast.makeText(mContext, "NFC is not enable.", Toast.LENGTH_LONG).show();
            return false;
        } else {
            return true;
        }
    }

    //To use in the OnResume() method of the foregroundActivity
    public void activateForegroundIntentCatching(Activity activity){
        PendingIntent pendingIntent = PendingIntent.getActivity(mContext, 0, new Intent(mContext, mContext.getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);
        mNfcAdapter.enableForegroundDispatch(activity, pendingIntent, null, null);
    }

    //To use in the OnPause() method of the foregroundActivity
    public void desactivateForegroundIntentCatching(Activity activity){
        mNfcAdapter.disableForegroundDispatch(activity);
    }

    //To use in the onNewIntent() of the reading activity
    public void handleTagIntent(Intent intent){
        String action = intent.getAction();
        if (NfcAdapter.ACTION_NDEF_DISCOVERED.equals(action)) {

            /*
            String type = intent.getType();
            if (MIME_TEXT_PLAIN.equals(type)) {

                Tag tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
                new NdefReaderTask().execute(tag);

            } else {
                Log.d(this.getClass().getName(), "Wrong mime type: " + type);
            }
            */
        } else if (NfcAdapter.ACTION_TECH_DISCOVERED.equals(action)) {

            // In case we would still use the Tech Discovered Intent
            /*
            Tag tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
            String[] techList = tag.getTechList();
            String searchedTech = Ndef.class.getName();

            for (String tech : techList) {
                if (searchedTech.equals(tech)) {
                    new NdefReaderTask().execute(tag);
                    break;
                }
            }
            */
        } else if (NfcAdapter.ACTION_TAG_DISCOVERED.equals(action)){
            Parcelable[] rawMsgs = intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES);
            NdefMessage[] messages;
            if (rawMsgs != null) {
                messages = new NdefMessage[rawMsgs.length];
                for (int i = 0; i < rawMsgs.length; i++) {
                    messages[i] = (NdefMessage) rawMsgs[i];


                    NdefRecord record = messages[i].getRecords()[i];
                    byte[] id = record.getId();
                    short tnf = record.getTnf();
                    byte[] type = record.getType();
                    String message = getTextData(record.getPayload());
                }
            }
        }
    }

    private String getTextData(byte[] payload) {

        String textEncoding = ((payload[0] & 128) == 0) ? new String("UTF-8") : new String("UTF-16");

        int languageCodeLength = payload[0] & 0063;

        try {
            return new String(payload, languageCodeLength + 1, payload.length - languageCodeLength - 1, textEncoding);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }
}
