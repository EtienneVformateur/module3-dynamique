package re.arro38.loginapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class BddActivity : AppCompatActivity() {
    val db = Firebase.firestore
    val  TAG = "BDD"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bdd)
        val btCreerUser = findViewById<Button>(R.id.btCreerUser)
        val btGET = findViewById<Button>(R.id.btGetBDD)
        btCreerUser.setOnClickListener {
            // Create a new user with a first and last name
            val livre = hashMapOf(
                "titre" to "Mon livre",
                "auteur" to "Mr Fernandez"
            )

// Add a new document with a generated ID
            db.collection("livres")
                .add(livre)
                .addOnSuccessListener { documentReference ->
                    Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
                }
                .addOnFailureListener { e ->
                    Log.w(TAG, "Error adding document", e)
                }
        }

        btGET.setOnClickListener {
            db.collection("livres")
                .get()
                .addOnSuccessListener { result ->
                    for (document in result) {
                        Log.d(TAG, "${document.id} => ${document.data}")
                    }
                }
                .addOnFailureListener { exception ->
                    Log.w(TAG, "Error getting documents.", exception)
                }
        }


    }
}