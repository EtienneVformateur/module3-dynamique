package re.arro38.loginapp

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class RegisterActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private val TAG = "EmailPassword"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val bt = findViewById<Button>(R.id.button)
        val btRegister = findViewById<Button>(R.id.btregister)
        val btVerif = findViewById<Button>(R.id.btVerif)

        val email = findViewById<EditText>(R.id.etEmail)
        val pwd = findViewById<EditText>(R.id.etPwd)
        val resultat = findViewById<TextView>(R.id.tvResult)
        bt.visibility = View.GONE
        resultat.visibility = View.GONE

        auth = Firebase.auth

        btRegister.setOnClickListener {
            //Fonction inscription ( email , pwd)
            auth.createUserWithEmailAndPassword(email.text.toString(), pwd.text.toString())
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            Log.d(TAG, "signInWithEmail:success")
                            Toast.makeText(baseContext, "Enregistrement ok.",
                                    Toast.LENGTH_SHORT).show()
                            btRegister.visibility = View.GONE
                            email.visibility = View.GONE
                            pwd.visibility = View.GONE
                            btVerif.visibility = View.VISIBLE

                        } else {
                            Log.w(TAG, "signInWithEmail:failure", task.exception)
                            Toast.makeText(baseContext, "Enregistrement échoué.",
                                    Toast.LENGTH_SHORT).show()
                        }

                        // ...
                    }
        }
        btVerif.setOnClickListener {
            btVerif.isEnabled = false
            val user = auth.currentUser!!
            user.sendEmailVerification()
                    .addOnCompleteListener(this) { task ->
                        // [START_EXCLUDE]
                        // Re-enable button
                        btVerif.isEnabled = true

                        if (task.isSuccessful) {
                            Toast.makeText(baseContext,
                                    "Verification email sent to ${user.email} ",
                                    Toast.LENGTH_SHORT).show()
                        } else {
                            Log.e(TAG, "sendEmailVerification", task.exception)
                            Toast.makeText(baseContext,
                                    "Failed to send verification email.",
                                    Toast.LENGTH_SHORT).show()
                        }

                    }
        }
    }
}