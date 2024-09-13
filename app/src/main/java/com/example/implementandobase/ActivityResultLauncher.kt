package com.example.implementandobase

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.appcompat.app.AppCompatActivity
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class SignInActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var signInLauncher: ActivityResultLauncher<Intent>
    private lateinit var editEmail: EditText
    private lateinit var editSenha: EditText
    private lateinit var signInButton: Button

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize Firebase Auth
        auth = FirebaseAuth.getInstance()

        // Initialize UI components
        editEmail = findViewById(R.id.edit_email)
        editSenha = findViewById(R.id.edit_senha)
        signInButton = findViewById(R.id.onSignInResult)

        // Configure the sign-in launcher
        signInLauncher = registerForActivityResult(
            FirebaseAuthUIActivityResultContract()
        ) { result -> onSignInResult(result) }

        // Set click listener for the sign-in button
        signInButton.setOnClickListener {
            startSignIn()
        }

        FirebaseApp.initializeApp(this) // Initialize Firebase

        val auth = FirebaseAuth.getInstance() // Now you can safely access Firebase Authentication
        // ... your Firebase Authentication code ...

        FirebaseApp.initializeApp(this) // 'this' refers to the Activity context

    }

    override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
        if (currentUser != null) {
            updateUI(currentUser)
        }
    }

    private fun startSignIn() {
        val email = editEmail.text.toString()
        val password = editSenha.text.toString()

        if (email.isNotEmpty() && password.isNotEmpty()) {
            val providers = listOf(
                AuthUI.IdpConfig.EmailBuilder().build(),
                AuthUI.IdpConfig.GoogleBuilder().build()
            )

            val signInIntent = AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(providers)
                .setTosAndPrivacyPolicyUrls(
                    "https://example.com/terms.html",
                    "https://example.com/privacy.html"
                )
                .build()

            signInLauncher.launch(signInIntent)
        } else {
            Toast.makeText(this, "Por favor, preencha todos os campos.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun onSignInResult(result: FirebaseAuthUIAuthenticationResult) {
        if (result.resultCode == Activity.RESULT_OK) {
            val user: FirebaseUser? = FirebaseAuth.getInstance().currentUser
            Toast.makeText(this, "Bem-vindo, ${user?.displayName}!", Toast.LENGTH_SHORT).show()
            updateUI(user)
        } else {
            Toast.makeText(this, "Falha na autenticação.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun updateUI(user: FirebaseUser?) {
        if (user != null) {
            Log.d(TAG, "User: ${user.email}")
        } else {
            Log.d(TAG, "User not authenticated")
        }
    }

    companion object {
        private const val TAG = "SignInActivity"
    }

}



//package com.example.implementandobase
//
//import android.annotation.SuppressLint
//import android.app.Activity
//import android.content.ContentValues.TAG
//import android.content.Intent
//import android.os.Bundle
//import android.util.Log
//import android.widget.Button
//import android.widget.EditText
//import android.widget.Toast
//import androidx.activity.result.ActivityResult
//import androidx.activity.result.ActivityResultCallback
//import androidx.activity.result.ActivityResultLauncher
//import androidx.activity.result.contract.ActivityResultContracts
//import androidx.appcompat.app.AppCompatActivity
//import com.firebase.ui.auth.AuthUI
//import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
//import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult
//import com.google.firebase.Firebase
//import com.google.firebase.auth.ActionCodeSettings
//import com.google.firebase.auth.FirebaseAuth
//import com.google.firebase.auth.FirebaseUser
//import com.google.firebase.auth.auth
//
//class ActivityResultLauncher : AppCompatActivity() {
//
//    private lateinit var auth: FirebaseAuth
//
//    private lateinit var signInLauncher: ActivityResultLauncher<Intent>
//    private lateinit var editEmail: EditText
//    private lateinit var editSenha: EditText
//    private lateinit var signInButton: Button
//
//    @SuppressLint("MissingInflatedId")
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main) // Certifique-se de que o nome do layout corresponde ao XML
//
//        // Initialize Firebase Auth
//        auth = Firebase.auth
//
//        // Inicializa os componentes da interface do usuário
//        editEmail = findViewById(R.id.edit_email)
//        editSenha = findViewById(R.id.edit_senha)
//        signInButton = findViewById(R.id.onSignInResult)
//
//        // Configura o launcher para a autenticação
//        signInLauncher = registerForActivityResult(
//            FirebaseAuthUIActivityResultContract()
//        ) { result ->
//            onSignInResult(result)
//        }
//
//        // Configura o clique do botão para iniciar o fluxo de autenticação
//        signInButton.setOnClickListener {
//            startSignIn()
//        }
//    }
//
//    public override fun onStart() {
//        super.onStart()
//        // Check if user is signed in (non-null) and update UI accordingly.
//        val currentUser = auth.currentUser
//        if (currentUser != null) {
//            reload()
//        }
//    }
//
//    private fun reload() {
//        TODO("Not yet implemented")
//    }
//
//    private fun createUserWithEmailAndPassword(email: String, password: String) {
//        auth.createUserWithEmailAndPassword(email, password)
//            .addOnCompleteListener(this) { task ->
//                if (task.isSuccessful) {
//                    // Sign in success, update UI with the signed-in user's information
//                    Log.d(TAG, "createUserWithEmail:success")
//                    val user = auth.currentUser
//                    updateUI(user)
//                } else {
//                    // If sign in fails, display a message to the user
//                    Log.w(TAG, "createUserWithEmail:failure", task.exception)
//                    Toast.makeText(
//                        baseContext,
//                        "Authentication failed.",
//                        Toast.LENGTH_SHORT
//                    ).show()
//                    updateUI(null)
//                }
//            }
//    }
//
//    private fun updateUI(user: FirebaseUser?) {
//        // Atualize a UI com as informações do usuário
//        if (user != null) {
//            // Usuário autenticado com sucesso
//            // Exemplo: iniciar uma nova Activity, exibir dados do usuário, etc.
//            Log.d(TAG, "User: ${user.email}")
//        } else {
//            // Usuário não autenticado
//            // Exemplo: limpar campos, mostrar mensagem, etc.
//            Log.d(TAG, "User not authenticated")
//        }
//    }
//
//    fun signInWithEmailAndPassword(email: String, password: String) {
//        auth.createUserWithEmailAndPassword(email, password)
//            .addOnCompleteListener(this) { task ->
//            if (task.isSuccessful) {
//                // Sign in success, update UI with the signed-in user's information
//                Log.d(TAG, "signInWithEmail:success")
//                val user = auth.currentUser
//                updateUI(user)
//            } else {
//                // If sign in fails, display a message to the user.
//                Log.w(TAG, "signInWithEmail:failure", task.exception)
//                Toast.makeText(
//                    baseContext,
//                    "Authentication failed.",
//                    Toast.LENGTH_SHORT,
//                ).show()
//                updateUI(null)
//            }
//        }
//    }
//
//    private fun startSignIn() {
//        val email = editEmail.text.toString()
//        val password = editSenha.text.toString()
//
//        if (email.isNotEmpty() && password.isNotEmpty()) {
//            // Configura os provedores de autenticação
//            val providers = listOf(
//                AuthUI.IdpConfig.EmailBuilder().build(),
//                AuthUI.IdpConfig.GoogleBuilder().build()
//                // Adicione outros provedores, se necessário
//            )
//
//            // Configura o ActionCodeSettings para autenticação por link de e-mail
//            val actionCodeSettings = ActionCodeSettings.newBuilder()
//                .setAndroidPackageName(
//                    /* yourPackageName= */ "com.example.implementandobase",
//                    /* installIfNotAvailable= */ true,
//                    /* minimumVersion= */ null
//                )
//                .setHandleCodeInApp(true)
//                .setUrl("https://example.com.implementandobase")
//                .build()
//
//            // Configura a autenticação por link de e-mail
//            val emailProvider = AuthUI.IdpConfig.EmailBuilder()
//                .enableEmailLinkSignIn()
//                .setActionCodeSettings(actionCodeSettings)
//                .build()
//
//            // Verifica se a intenção pode ser manipulada
//            if (AuthUI.canHandleIntent(intent)) {
//                val link = intent.extras?.getString("email_link_sign_in")
//                if (link != null) {
//                    val signInIntent = AuthUI.getInstance()
//                        .createSignInIntentBuilder()
//                        .setEmailLink(link)
//                        .setAvailableProviders(listOf(emailProvider))
//                        .build()
//                    signInLauncher.launch(signInIntent)
//                    return
//                }
//            }
//
//            // Configura e inicia a intenção de login
//            val signInIntent = AuthUI.getInstance()
//                .createSignInIntentBuilder()
//                .setAvailableProviders(providers)
////                .setLogo(R.drawable.my_great_logo) // Defina o logotipo drawable
////                .setTheme(R.style.MySuperAppTheme) // Defina o tema
//                .setTosAndPrivacyPolicyUrls(
//                    "https://example.com.implementandobase/terms.html",
//                    "https://example.com.implementandobase/privacy.html"
//                )
//                .build()
//            signInLauncher.launch(signInIntent)
//        } else {
//            Toast.makeText(this, "Por favor, preencha todos os campos.", Toast.LENGTH_SHORT).show()
//        }
//    }
//
//    private fun onSignInResult(result: FirebaseAuthUIAuthenticationResult) {
//        if (result.resultCode == Activity.RESULT_OK) {
//            val user: FirebaseUser? = FirebaseAuth.getInstance().currentUser
//            Toast.makeText(this, "Bem-vindo, ${user?.displayName}!", Toast.LENGTH_SHORT).show()
//        } else {
//            Toast.makeText(this, "Falha na autenticação.", Toast.LENGTH_SHORT).show()
//        }
//    }
//
//    private fun signOut() {
//        AuthUI.getInstance()
//            .signOut(this)
//            .addOnCompleteListener { task ->
//                if (task.isSuccessful) {
//                    Toast.makeText(this, "Desconectado com sucesso.", Toast.LENGTH_SHORT).show()
//                } else {
//                    Toast.makeText(this, "Falha ao desconectar.", Toast.LENGTH_SHORT).show()
//                }
//            }
//    }
//
//    private fun deleteAccount() {
//        AuthUI.getInstance()
//            .delete(this)
//            .addOnCompleteListener { task ->
//                if (task.isSuccessful) {
//                    Toast.makeText(this, "Conta deletada com sucesso.", Toast.LENGTH_SHORT).show()
//                } else {
//                    Toast.makeText(this, "Falha ao deletar a conta.", Toast.LENGTH_SHORT).show()
//                }
//            }
//    }
//}