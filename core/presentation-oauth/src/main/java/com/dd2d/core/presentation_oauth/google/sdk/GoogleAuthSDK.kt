package com.dd2d.core.presentation_oauth.google.sdk

import android.content.Context
import androidx.credentials.CredentialManager
import androidx.credentials.CustomCredential
import androidx.credentials.GetCredentialRequest
import androidx.credentials.GetCredentialResponse
import androidx.credentials.PasswordCredential
import androidx.credentials.PublicKeyCredential
import androidx.credentials.exceptions.GetCredentialCancellationException
import androidx.credentials.exceptions.GetCredentialException
import androidx.credentials.exceptions.GetCredentialInterruptedException
import androidx.credentials.exceptions.GetCredentialProviderConfigurationException
import androidx.credentials.exceptions.NoCredentialException
import com.dd2d.core.core.exception.ClientException
import com.dd2d.core.presentation_oauth.BuildConfig
import com.dd2d.core.presentation_oauth.R
import com.dd2d.core.presentation_oauth.google.model.OAuthResult
import com.dd2d.core.presentation_oauth.google.model.OAuthState
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

internal class GoogleAuthSDK(private val context: Context) {
    private val _state = MutableStateFlow<OAuthState>(OAuthState.Idle)
    val state = _state.asStateFlow()
    fun consumeState() = _state.update { OAuthState.Idle }

    private val manager = CredentialManager.create(context)
    private val googleIdOption: GetGoogleIdOption = GetGoogleIdOption.Builder()
        .setFilterByAuthorizedAccounts(false)
        .setServerClientId(BuildConfig.GOOGLE_WEB_CLIENT_ID)
        .build()

    private val request = GetCredentialRequest.Builder()
        .addCredentialOption(googleIdOption)
        .build()


    suspend fun oAuth() {
        _state.update { OAuthState.Loading }

        val result = try {
            handleResponse(manager.getCredential(context, request))
        }
        catch (e: GetCredentialException) {
            handleException(e)
        }

        _state.update { result }
    }

    private fun handleResponse(response: GetCredentialResponse): OAuthState {
        return when(val credential = response.credential) {
            is CustomCredential -> {
                val idToken = GoogleIdTokenCredential.createFrom(credential.data)
                val result = OAuthResult(
                    token = idToken.idToken,
                    name = idToken.givenName,
                    email = idToken.id,
                    profileImageUrl = idToken.profilePictureUri?.path,
                )
                OAuthState.Success(result)
            }
            is PublicKeyCredential -> {
                val exception = ClientException.UnsupportedOperationException(
                    message = context.getString(R.string.un_support_auth_type),
                    code = 1
                )
                OAuthState.Error(exception)
            }

            is PasswordCredential -> {
                val exception = ClientException.UnsupportedOperationException(
                    message = context.getString(R.string.un_support_auth_type),
                    code = 2
                )
                OAuthState.Error(exception)
            }
            else -> {
                val exception = ClientException.OperationFailException(
                    message = context.getString(R.string.auth_fail),
                    code = 3
                )
                OAuthState.Error(exception)
            }
        }
    }

    private fun handleException(e: GetCredentialException): OAuthState {
        return when(e) {
            is NoCredentialException -> {
                val exception = ClientException.OperationFailException(
                    message = context.getString(R.string.not_found_credential),
                    code = null,
                    cause = e
                )
                OAuthState.Error(exception)
            }
            is GetCredentialProviderConfigurationException -> {
                val exception = ClientException.OperationFailException(
                    message = context.getString(R.string.no_google_play),
                    code = null,
                    cause = e
                )
                OAuthState.Error(exception)
            }
            is GetCredentialCancellationException -> OAuthState.Idle
            is GetCredentialInterruptedException -> OAuthState.Idle
            else -> {
                val exception = ClientException.UnknownException(
                    message = context.getString(R.string.unknown_error_for_auth),
                    code = null,
                    cause = e
                )
                OAuthState.Error(exception)
            }
        }
    }
}