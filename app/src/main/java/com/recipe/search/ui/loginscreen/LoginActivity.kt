package com.recipe.search.ui.loginscreen

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.recipe.search.ui.mainscreen.MainActivity
import com.recipe.search.R
import com.recipe.search.common.BaseActivity
import com.recipe.search.common.Navigator
import com.recipe.search.common.RC_SIGN_IN
import com.recipe.search.databinding.ActivityLoginBinding

/**
 * Created by Chetan on 22/03/20.
 */
class LoginActivity : BaseActivity() {

    private lateinit var viewModel: LoginViewModel
    private lateinit var googleSignInClient: GoogleSignInClient
    private lateinit var binding: ActivityLoginBinding

    override fun setupLiveDataComponents() {
        //TODO("Not yet implemented")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)

        viewModel = ViewModelProvider(
            this,
            LoginViewModelFactory(application)
        ).get(LoginViewModel::class.java)

        binding.viewModel = viewModel
        if (GoogleSignIn.getLastSignedInAccount(this) != null) {
            goToMainActivity()
        } else {
            binding.signInButton.setOnClickListener { signIn() }

            setupGoogleLoginClient()
        }
    }

    private fun setupGoogleLoginClient() {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//            .requestIdToken(application.getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        googleSignInClient = GoogleSignIn.getClient(this, gso)
    }

    private fun signIn() {
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)
                Log.d("LoginActivity", account?.displayName!!)
                goToMainActivity();


            } catch (e: ApiException) {
                Toast.makeText(this, R.string.error_login, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun goToMainActivity() {
        resolveNavigation(
            Navigator(
                Navigator.NavigationAction.StartActivityFinishCurrent,
                MainActivity::class.java,
                null
            )
        )
    }
}
