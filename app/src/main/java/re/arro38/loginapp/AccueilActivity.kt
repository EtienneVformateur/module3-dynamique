package re.arro38.loginapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class AccueilActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_accueil)
        val  tvwelcom = findViewById<TextView>(R.id.tvWelcome)
        val btVerif = findViewById<Button>(R.id.btverif2)
        btVerif.visibility = View.INVISIBLE
        auth = Firebase.auth
        val user = auth.currentUser
        if (user?.isEmailVerified == true){
            tvwelcom.text = user.email.toString() +" vérifiée"
        }
        else{
            tvwelcom.text = user?.email.toString() +" non vérifiée"
            btVerif.visibility = View.VISIBLE
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
                        } else
//                            Log.e(TAG, "sendEmailVerification", task.exception)
                            Toast.makeText(baseContext,
                                    "Failed to send verification email.",
                                    Toast.LENGTH_SHORT).show()
                        }

                    }
        }



}