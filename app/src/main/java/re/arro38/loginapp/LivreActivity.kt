package re.arro38.loginapp

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import okhttp3.OkHttpClient
import okhttp3.Request


class LivreActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_livre)
        val etIdLivre = findViewById<EditText>(R.id.etIdLivre)
        val btGetLivre = findViewById<Button>(R.id.btGetLivre)
        val tvAuteur = findViewById<TextView>(R.id.tvAuteur)
        val tvTitre = findViewById<TextView>(R.id.tvTitre)
        var client = OkHttpClient()

        fun run(url: String):String {
            val request: Request = Request.Builder()
                .url(url)
                .build()
            client.newCall(request).execute().use { response -> return response.body!!.string() }
        }

        btGetLivre.setOnClickListener {
            val IdLivre = etIdLivre.text

            //TODO REQUETE GET https://module5.etienne-vaytilingom.re/Livres/ IdLivre => Mettre le résultat dans tvAuteur.text et tvTitre.text
            val thread = Thread {
                Log.d("REPONSE",run("https://module5.etienne-vaytilingom.re/livres/33"))
            }
            thread.start()

        }
    }
}