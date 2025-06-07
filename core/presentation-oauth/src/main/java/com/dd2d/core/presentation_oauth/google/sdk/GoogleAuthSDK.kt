package com.dd2d.core.presentation_oauth.google.sdk

import android.content.Context
import android.os.Build
import androidx.credentials.ClearCredentialStateRequest
import androidx.credentials.CredentialManager
import androidx.credentials.CustomCredential
import androidx.credentials.GetCredentialRequest
import androidx.credentials.GetCredentialResponse
import androidx.credentials.PasswordCredential
import androidx.credentials.PublicKeyCredential
import androidx.credentials.exceptions.GetCredentialCancellationException
import androidx.credentials.exceptions.GetCredentialException
import androidx.credentials.exceptions.GetCredentialInterruptedException
import androidx.credentials.exceptions.GetCredentialUnknownException
import androidx.credentials.exceptions.NoCredentialException
import com.dd2d.core.core.exception.ClientException
import com.dd2d.core.presentation_oauth.BuildConfig
import com.dd2d.core.presentation_oauth.google.model.OAuthResult
import com.dd2d.core.presentation_oauth.google.model.OAuthState
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GetSignInWithGoogleOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.android.libraries.identity.googleid.GoogleIdTokenParsingException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

internal class GoogleAuthSDK(
  private val context: Context,
  private val scope: CoroutineScope,
) : OAuthSDK() {
  private val manager = CredentialManager.create(context)

  private val googleIdOption = GetGoogleIdOption.Builder()
    .setServerClientId(BuildConfig.GOOGLE_WEB_CLIENT_ID)
    .setFilterByAuthorizedAccounts(false)
    .build()
  private val requestWithIdOption = GetCredentialRequest.Builder()
    .addCredentialOption(googleIdOption)
    .build()

  private val googleSignInOption = GetSignInWithGoogleOption
    .Builder(BuildConfig.GOOGLE_WEB_CLIENT_ID)
    .build()
  private val requestWithSignInOption = GetCredentialRequest.Builder()
    .addCredentialOption(googleSignInOption)
    .build()


  init {
    scope.launch {
      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
        manager.prepareGetCredential(requestWithIdOption)
      }
    }
  }

  override fun signIn() {
    scope.launch {
      stateToLoading()

      val resultState = try {
        val result = manager.getCredential(context, requestWithIdOption)
        handleResult(result)
      } catch (e: GetCredentialCancellationException) {
        OAuthState.Idle
      } catch (e: NoCredentialException) {
        val result = manager.getCredential(context, requestWithSignInOption)
        handleResult(result)
      } catch (e: GetCredentialException) {
        handleException(e)
      }

      stateTo(resultState)
    }
  }

  override fun signOut() {}

  override fun withdraw() {
    scope.launch {
      manager.clearCredentialState(ClearCredentialStateRequest())
    }
  }

  private fun handleResult(result: GetCredentialResponse): OAuthState {
    return when (val credential = result.credential) {
      is CustomCredential -> {
        try {
          val data = GoogleIdTokenCredential.createFrom(credential.data)
          OAuthState.Success(
            result = OAuthResult(
              token = data.idToken,
              name = data.displayName,
              email = data.id,
              profileImageUrl = data.profilePictureUri?.path,
            )
          )
        } catch (e: GoogleIdTokenParsingException) {
          OAuthState.Error(
            exception = ClientException.OperationFailException(
              message = "로그인에 실패했습니다. 잠시후 다시 시도해 주세요.",
              code = 1,
              cause = e
            )
          )
        }
      }

      is PublicKeyCredential -> unsupportedOperation(code = 1)
      is PasswordCredential -> unsupportedOperation(code = 2)
      else -> unsupportedOperation(code = 3)
    }
  }

  private fun handleException(e: GetCredentialException): OAuthState {
    return when (e) {
      is GetCredentialCancellationException -> OAuthState.Idle
      is GetCredentialInterruptedException -> OAuthState.Idle
      is GetCredentialUnknownException -> {
        OAuthState.Error(
          exception = ClientException.UnknownException(
            message = "로그인에 실패했습니다. 잠시후 다시 시도해 주세요.",
            code = 2,
            cause = e,
          )
        )
      }

      is NoCredentialException -> {
        OAuthState.Error(
          exception = ClientException.OperationFailException(
            message = "로그인 가능한 구글 계정을 찾을 수 없습니다."
          )
        )
      }

      else -> {
        OAuthState.Error(
          exception = ClientException.UnknownException(
            message = "로그인에 실패했습니다. 잠시후 다시 시도해 주세요.",
            code = 3,
            cause = e,
          )
        )
      }
    }
  }

  private fun unsupportedOperation(code: Int): OAuthState.Error {
    return OAuthState.Error(
      exception = ClientException.UnsupportedOperationException(
        message = "지원하지 않는 로그인 방식입니다.",
        code = code,
      )
    )
  }
}