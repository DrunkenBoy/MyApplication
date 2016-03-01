/* Die Einstellungen-Activity wird über den Button "Einstellungen" aufgerufen.
In ihr kann die Handynummer der Kontaktperson gespeichert werden, welche dann in
den anderen Activitys verwendet wird.
*/
package studienarbeit.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class Einstellungen extends AppCompatActivity {
    public static String handynummer = "Handynummer Kontaktperson"; //Die hier veränderte Variable ist in allen Klassen verfügbar

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_einstellungen);
    }

    public void safenumber(View view){
        EditText handynummer_kontaktperson = (EditText) findViewById(R.id.EditText);     //Über "EditText" wird die Eingabe gespeichert
        handynummer = handynummer_kontaktperson.getText().toString();                    // in die globale Variable überschrieben
        Toast.makeText(Einstellungen.this, "Die Kontaktdaten sind eingelesen", Toast.LENGTH_LONG).show();
    }

}
