package re.arro38.loginapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.core.extensions.jsonBody
import com.google.gson.Gson
import com.google.gson.JsonObject
import org.json.JSONObject

class CreerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_creer)

        val etAuteur = findViewById<EditText>(R.id.etCAuteur)
        val etTitre = findViewById<EditText>(R.id.etCTitre)
        val btCreer = findViewById<Button>(R.id.btCreer)




        btCreer.setOnClickListener {
            val auteur = etAuteur.text.toString()
            val titre = etTitre.text.toString()
            val url = "https://module5.etienne-vaytilingom.re/livres"
            val json = JSONObject()
            json.put("auteur",auteur)
            json.put("titre",titre)

            Fuel.post(url)
                .jsonBody(json.toString())
                .also { println(it) }
                .responseString() { result -> println(result.get())}
        }
    }
}
