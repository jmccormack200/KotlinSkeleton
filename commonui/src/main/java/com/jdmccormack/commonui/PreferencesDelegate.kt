package com.jdmccormack.commonui

import android.content.SharedPreferences
import androidx.core.content.edit
import androidx.preference.PreferenceManager
import com.jdmccormack.commonui.BaseApplication.Companion.applicationContext
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * Yet another implementation of Shared Preferences using Delegated Properties
 *
 * Check out https://medium.com/@FrostRocketInc/delegated-shared-preferences-in-kotlin-45b82d6e52d0
 * for a detailed walkthrough.
 *
 * @author Matthew Groves
 */

abstract class PreferenceDelegate<T> : ReadWriteProperty<Any, T> {
    protected val sharedPreferences: SharedPreferences by lazy { PreferenceManager.getDefaultSharedPreferences(applicationContext) }
}

class StringPreferenceDelegate(private val key: String, private val defaultValue: String = "") : PreferenceDelegate<String>() {
    override fun getValue(thisRef: Any, property: KProperty<*>) = sharedPreferences.getString(key, defaultValue)!!
    override fun setValue(thisRef: Any, property: KProperty<*>, value: String) = sharedPreferences.edit { putString(key, value) }
}

class IntPreferenceDelegate(private val key: String, private val defaultValue: Int = 0) : PreferenceDelegate<Int>() {
    override fun getValue(thisRef: Any, property: KProperty<*>) = sharedPreferences.getInt(key, defaultValue)
    override fun setValue(thisRef: Any, property: KProperty<*>, value: Int) = sharedPreferences.edit { putInt(key, value) }
}

class LongPreferenceDelegate(private val key: String, private val defaultValue: Long = 0.toLong()) : PreferenceDelegate<Long>() {
    override fun getValue(thisRef: Any, property: KProperty<*>) = sharedPreferences.getLong(key, defaultValue)
    override fun setValue(thisRef: Any, property: KProperty<*>, value: Long) = sharedPreferences.edit { putLong(key, value) }
}

class FloatPreferenceDelegate(private val key: String, private val defaultValue: Float = 0.toFloat()) : PreferenceDelegate<Float>() {
    override fun getValue(thisRef: Any, property: KProperty<*>) = sharedPreferences.getFloat(key, defaultValue)
    override fun setValue(thisRef: Any, property: KProperty<*>, value: Float) = sharedPreferences.edit { putFloat(key, value) }
}

class BooleanPreferenceDelegate(private val key: String, private val defaultValue: Boolean = false) : PreferenceDelegate<Boolean>() {
    override fun getValue(thisRef: Any, property: KProperty<*>) = sharedPreferences.getBoolean(key, defaultValue)
    override fun setValue(thisRef: Any, property: KProperty<*>, value: Boolean) = sharedPreferences.edit { putBoolean(key, value) }
}
