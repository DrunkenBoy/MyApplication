/* Die Anruf-Activity wird über den Button "Freund anrufen" aufgerufen.
Sie nimmt die unter Einstellungen gespeicherte Nummer und ruft diese im "Wählen" Fenster auf.
Es ist möglich diese Person nun anzurufen.
*/
package studienarbeit.myapplication;


import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class Anruf extends Activity {
    String nummer= Einstellungen.handynummer;   //Hier ist die unter Einstellungen gespeicherte Nummer hinterlegt
    Button b1;                                  //Über diesen Button wird der Anruf gestartet


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anruf);
        b1=(Button)findViewById(R.id.button);

    }
    public void call(View view) {       //Die call- Funktion realisiert den Anruf
        // Über "ACTION_DIAL" wird der Wahlbildschirm mit der über "Uri.fromParts" übergebenen Nummer aufgerufen
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", nummer, null));    // Uri.fromParts erwartet  (String scheme, String ssp, String fragment)
        startActivity(intent);

    }
    //<-- Im Auskommentierten befindet sich die Möglichkeit über "ACTION_CALL" direkt in den Verbindungsaufbau zu springen -->

        /*Intent intent=new Intent(Intent.ACTION_CALL,Uri.parse("01110101"));
        try{
            startActivity(intent);
        }

        catch (android.content.ActivityNotFoundException ex){
            Toast.makeText(getApplicationContext(),"yourActivity is not founded",Toast.LENGTH_SHORT).show();
        }*/
}








