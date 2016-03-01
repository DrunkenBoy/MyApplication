package studienarbeit.myapplication;

/* Die Bildaufnahme-Activity wird über den Button "Bild aufnehmen" aufgerufen.
Dieses Bild wird auf der Seite hinterlegt und kann für eine bessere Einschätzung
der Situation sowie zur Beweislage seinem Partner gezeigt werden.
*/

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;



public class Bildaufnahme extends AppCompatActivity {
    Button b1;      //Button b1 zum Aufrufen der Kamera
    ImageView iv;   //Variable zum Weiterverarbeiten des durch die Kamera aufgenommenen Fotos


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bildaufnahme);
        b1=(Button)findViewById(R.id.button);       //Zuordnung des Buttons aus dem Layout
        iv=(ImageView)findViewById(R.id.imageView); //Zuordnung der imageView aus dem layout

        b1.setOnClickListener(new View.OnClickListener() {      //"setOnClickListener" überwacht den Button auf Aktivierung
            @Override
            public void onClick(View v) {   //bei Klick auf den Button wird diese Funktion ausgeführt
                Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);   //Über "ACTION_IMAGE_CAPTURE" kommt man an den mit Hilfe der Kamera aufgenommene Foto
                startActivityForResult(intent, 0); //starten der Activity und auf Rückgabewert warten
            }
        });

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);

        Bitmap bp = (Bitmap) data.getExtras().get("data");  //Abspeichern des Fotos in bp
        iv.setImageBitmap(bp);      //Übergibt einer Bitmap den Inhalt von ImageView
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


}
