package com.example.nativebaseproject.common.inapp_review

import android.app.Activity
import com.google.android.play.core.review.ReviewManagerFactory

/**
 * Created by ThangNNT on 23/03/2023.
 */
fun launchInAppReview(activity: Activity, onError: (java.lang.Exception)-> Unit, onComplete: ()-> Unit){
    val manager = ReviewManagerFactory.create(activity)
    //val manager = FakeReviewManager(activity)
    val request = manager.requestReviewFlow()
    request.addOnCompleteListener { task ->
        if (task.isSuccessful) {
            // We got the ReviewInfo object
            val reviewInfo = task.result
            val flow = manager.launchReviewFlow(activity, reviewInfo)
            flow.addOnCompleteListener { _ ->
                // The flow has finished. The API does not indicate whether the user
                // reviewed or not, or even whether the review dialog was shown. Thus, no
                // matter the result, we continue our app flow.
                onComplete.invoke()
            }
        } else {
            // There was some problem, log or handle the error code.
            task.exception?.let{
                onError.invoke(it)
            }
        }
    }
}