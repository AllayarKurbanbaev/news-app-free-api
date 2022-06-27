package uz.gita.newsappallayar.utils

import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import uz.gita.newsappallayar.R

fun View.onClick(action: (View?) -> Unit) {
    setOnClickListener {
        it?.let { action(it) }
    }
}

fun Fragment.snackMessage(message: String) {
    Snackbar.make(requireView(), message, Snackbar.LENGTH_SHORT).apply {
        setBackgroundTint(ContextCompat.getColor(requireContext(), R.color.teal_200))
            .setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
    }.show()
}

fun Fragment.snackErrorMessage(message: String) {
    Snackbar.make(requireView(), message, Snackbar.LENGTH_SHORT).apply {
        setBackgroundTint(ContextCompat.getColor(requireContext(), android.R.color.holo_red_dark))
            .setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
    }.show()
}

fun <T> Call<T>.myEnqueue(
    onSuccess: (response: Response<T>) -> Unit,
    onFailure: (t: Throwable) -> Unit
) {
    this.enqueue(object : Callback<T> {
        override fun onResponse(call: Call<T>, response: Response<T>) {
            onSuccess.invoke(response)
        }

        override fun onFailure(call: Call<T>, t: Throwable) {
            onFailure.invoke(t)
        }
    })
}