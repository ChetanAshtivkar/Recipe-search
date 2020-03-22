package com.recipe.search.common

import android.content.Intent
import android.os.Bundle
import java.io.Serializable

/**
 * Created by Chetan on 22/03/20.
 */
class Navigator {

    private var dataToSend: Map<String, Serializable>? = null
    private var requestCode: Int = 0
    private var bundle: Bundle? = null
    private var intent: Intent? = null
    private var delayedExecution: Boolean = false

    private var destination: Class<*>? = null
    private var navigationAction: NavigationAction

    /**
     * @param destination destination
     * @param bundle      bundle
     */

    constructor(navigationAction: NavigationAction, destination: Class<*>, bundle: Bundle?) {
        this.bundle = bundle
        this.destination = destination
        this.navigationAction = navigationAction
    }

    /**
     * Start Activity for result
     *
     * @param destination destination
     * @param bundle      bundle
     * @param requestCode requestCode
     */
    constructor(destination: Class<*>, bundle: Bundle?, requestCode: Int) {
        this.bundle = bundle
        this.destination = destination
        this.requestCode = requestCode
        this.navigationAction = NavigationAction.StartActivityForResult
    }

    /**
     * Send result back to the Activity
     *
     * @param dataToSend
     * @param delayedExecution
     */
    constructor(dataToSend: Map<String, Serializable>?, delayedExecution: Boolean) {
        this.delayedExecution = delayedExecution
        this.navigationAction = NavigationAction.SendResult
        this.dataToSend = dataToSend
    }

    /**
     * Finish current Activity
     *
     * @param delayedExecution
     */
    constructor(delayedExecution: Boolean) {
        this.navigationAction = NavigationAction.FinishCurrent
        this.delayedExecution = delayedExecution
    }

    /**
     * Handle Implicit Intent
     *
     * @param intent
     * @param requestCode
     */
    constructor(intent: Intent, requestCode: Int) {
        this.intent = intent
        this.navigationAction = NavigationAction.ImplicitIntent
        this.requestCode = requestCode
    }

    fun getDataToSend(): Map<String, Serializable>? {
        return dataToSend
    }

    fun getDestination(): Class<*>? {
        return destination
    }

    fun getBundle(): Bundle? {
        return bundle
    }

    fun getRequestCode(): Int {
        return requestCode
    }

    fun getNavigationAction(): NavigationAction {
        return navigationAction
    }

    fun getImplicitIntent(): Intent? {
        return intent
    }

    fun isDelayedExecution(): Boolean {
        return delayedExecution
    }

    enum class NavigationAction {
        StartActivity,
        StartActivityFinishCurrent,
        StartActivityForResult,
        SendResult,
        FinishCurrent,
        ImplicitIntent,
        StartActivityWithCloseAll
        /*, JetPackNavigationWithKnownHost, JetPackNavigationWithView*/
    }
}
