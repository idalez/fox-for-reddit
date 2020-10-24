package io.github.gusandrianos.foxforreddit.utilities

import android.app.Application

object FoxToolkit {
    fun getBearer(application: Application): String {
        val token = InjectorUtils.getInstance().provideTokenRepository().getToken(application)
        return " " + token.tokenType + " " + token.accessToken
    }
}