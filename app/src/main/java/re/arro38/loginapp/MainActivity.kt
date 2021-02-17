package re.arro38.loginapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class MainActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val bt = findViewById<Button>(R.id.button)
        val btRegister = findViewById<Button>(R.id.btregister)

        val email = findViewById<EditText>(R.id.etEmail)
        val pwd = findViewById<EditText>(R.id.etPwd)
        val resultat = findViewById<TextView>(R.id.tvResult)
//        bt.setOnClickListener{
//            Fuel.get("http://192.168.125.97/Login/login.php")
//                    .body("")
//                    .responseString() { result -> resultat.text = result.get()
//                    }
//
//        }
        auth = Firebase.auth
        bt.setOnClickListener {
            auth.signInWithEmailAndPassword(email.text.toString(), pwd.text.toString())
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            val intent = Intent(this,AccueilActivity::class.java)
                            startActivity(intent)
                            val user = auth.currentUser
                        } else {
                            // If sign in fails, display a message to the user.
                            resultat.text = "bad !"
                            // ...
                        }

                        // ...
                    }
        }
        btRegister.setOnClickListener {
            val intent = Intent(this,RegisterActivity::class.java)
            startActivity(intent)
        }
        val btApi = findViewById<Button>(R.id.btApi)
        btApi.setOnClickListener {
            val intent = Intent(this,LivreActivity::class.java)
            startActivity(intent)
        }
    }


}