/* Die Sprachnachricht-Activity wird über den Button "Sprachnachricht senden" aufgerufen.
In dieser Activity wird über die Speech-to-text Funktion eine Nachricht in einer Textdatei abgelegt.
Anschließend kann die Nachricht über eine SMS an die unter Einstellungen gespeicherte Nummer versendet werden.
*/
package studienarbeit.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Locale;

public class Sprachnachricht extends AppCompatActivity {
    ImageButton imbtn;
    EditText et;
    final int SPEECHINTENT_RED_CODE =11;
    String finaltext;
    String nummer = Einstellungen.handynummer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sprachnachricht);

        et = (EditText) findViewById(R.id.editText);
        imbtn= (ImageButton) findViewById(R.id.imageButton); //"ImageButton" ruft über das Klicken auf ein Bild eine Funktion aufrufen
        imbtn.setOnClickListener(new View.OnClickListener(){     //Bei Klick auf den Button wird die "onClick" Funktion aufgerufen
            public void onClick(View v){
                Intent spreechRecognitionIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);     //startet Activity, welche den Benutzer auffordert einen Text zu sprechen und diesen aufnimmt
                spreechRecognitionIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault().toString()); //aufgenommener Text wird umgewandelt in einen String
                startActivityForResult(spreechRecognitionIntent,SPEECHINTENT_RED_CODE); //
            }
        });
        Button startBtn = (Button) findViewById(R.id.button7); //Bei drücken auf den Button "startBtn" wird das SMS Fenster geöffnet
        startBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                sendSMS();
            }
        });



    }
    protected void onActivityResult(int requestcode, int resultcode, Intent data) { // Mit Hilfe dieser Methode wird die Spracheingabe in einen Text umgewandelt
        super.onActivityResult(requestcode, resultcode, data);

        if(requestcode == SPEECHINTENT_RED_CODE && resultcode == RESULT_OK){    //
            ArrayList<String> speechResults = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);

            if(et.getText().length() > 0){
                finaltext= et.getText().toString() +" "+speechResults.get(0);
            }else{
                finaltext = speechResults.get(0);
            }
        et.setText(finaltext); //Anzeigen des aufgenommenen Text in einem EditText-Feld

        }
    }
    protected void sendSMS() { //Vorhandener SMS-Client aufrufen, aufgenommener Text einfügen und eingetragene Nummer einfügen
        Log.i("Send SMS", "");
        Intent smsIntent = new Intent(Intent.ACTION_VIEW);

        smsIntent.setData(Uri.parse("smsto:"));
        smsIntent.setType("vnd.android-dir/mms-sms");
        smsIntent.putExtra("address"  , nummer);    //Übergabe der Handynummer der Kontaktperson
        smsIntent.putExtra("sms_body"  , finaltext);    //Übergabe des aufgenommenen Textes

        try {
            startActivity(smsIntent);
            finish();
            Log.i("Finished sending SMS...", "");
        }
        catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(Sprachnachricht.this,
                    "SMS failed, please try again later.", Toast.LENGTH_SHORT).show(); // Ausgabe im Fehlerfall
        }
    }


}
