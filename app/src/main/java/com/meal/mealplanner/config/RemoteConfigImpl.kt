package com.meal.mealplanner.config

import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings
import com.meal.mealplanner.R
import java.net.UnknownHostException
import javax.inject.Inject

class RemoteConfigImpl @Inject constructor(): RemoteConfig {
    private var remoteConfig: FirebaseRemoteConfig? = null

    init {
        getFirebaseRemoteConfig()
    }

    private fun getFirebaseRemoteConfig() {
        try {
            remoteConfig = Firebase.remoteConfig
            val configSettings = remoteConfigSettings {
                minimumFetchIntervalInSeconds = 1
            }
            remoteConfig?.setConfigSettingsAsync(configSettings)
            remoteConfig?.setDefaultsAsync(R.xml.remote_config_defaults)
            remoteConfig?.fetchAndActivate()
                ?.addOnCompleteListener {}
        } catch (e: UnknownHostException) {

        } catch (e: Exception) {

        }
    }

    override fun getString(key: String): String {
        return remoteConfig?.getString(key).orEmpty()
    }
}