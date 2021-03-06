package com.personal.accountantAssistant.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import com.personal.accountantAssistant.ui.MainActivity
import com.personal.accountantAssistant.ui.payments.PaymentsDetailsActivity
import com.personal.accountantAssistant.ui.payments.entities.Payments
import com.personal.accountantAssistant.ui.payments.enums.PaymentsType
import java.util.*

object ActivityUtils {

    @JvmStatic
    fun startMainActivity(packageContext: Context) {
        Intent(packageContext, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK
            packageContext.startActivity(this)
        }
    }

    @JvmStatic
    fun startActivity(packageContext: Context,
                      activityClass: Class<*>?) {
        Intent(packageContext, activityClass).apply {
            packageContext.startActivity(this)
        }
    }

    fun startPaymentDetailsActivity(packageContext: Context,
                                    paymentsType: PaymentsType?) {
        val activity = parse(packageContext)
        val payment = Payments()
        payment.type = paymentsType
        val activityIntent = Intent(packageContext, PaymentsDetailsActivity::class.java)
        activityIntent.putExtra(Constants.ENTITY, payment)
        activity.startActivityForResult(activityIntent, Constants.DETAIL_REQUEST_CODE)
    }

    fun refreshBy(context: Context) {
        val activity = parse(context)
        Objects.requireNonNull(activity).recreate()
    }

    @JvmStatic
    fun parse(context: Context): Activity {
        return context as Activity
    }

    @JvmStatic
    fun parse(activity: Activity?): Context? {
        return activity?.applicationContext
    }
}