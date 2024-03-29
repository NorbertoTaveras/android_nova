package com.norbertotaveras.android_nova.utils.android

import android.content.Context
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import com.norbertotaveras.android_nova.BuildConfig

object AndroidUtils {

    fun generateCustomUserAgent(context: Context?, moduleVersion: Any?): String {
        val version = getAppVersion(context)
        val appName = getAppName(context)
        val sdkVersion: String? = getSdkVersion()
        return "$appName/$version; RsSDK/$sdkVersion; $moduleVersion; ${System.getProperty("http.agent")}"
    }

    @JvmStatic
    fun getAppVersion(context: Context?): String? {
        val manager = context?.packageManager
        var info: PackageInfo? = null
        try {
            info = manager?.getPackageInfo(context.packageName, 0)
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }
        return info?.versionName
    }

    @JvmStatic
    fun getAppName(context: Context?): String? {
        val applicationInfo = context?.applicationInfo
        val stringId = applicationInfo?.labelRes ?: 0
        return if (stringId == 0) applicationInfo?.nonLocalizedLabel.toString() else context?.getString(
            stringId
        )
    }

    @JvmStatic
    fun getSdkVersion(): String {
        return BuildConfig.SDK_VERSION_NAME
    }
}