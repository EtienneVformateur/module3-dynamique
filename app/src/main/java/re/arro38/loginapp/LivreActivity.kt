package re.arro38.loginapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class LivreActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_livre)

        val etIdLivre = findViewById<EditText>(R.id.etIdLivre)
        val btGetLivre = findViewById<Button>(R.id.btGetLivre)
        val tvAuteur = findViewById<TextView>(R.id.tvAuteur)
        val tvTitre = findViewById<TextView>(R.id.tvTitre)

        btGetLivre.setOnClickListener {
            val IdLivre = etIdLivre.text
            //TODO REQUETE GET https://module5.etienne-vaytilingom.re/Livres/ IdLivre => Mettre le résultat dans tvAuteur.text et tvTitre.text
        }
    }
}