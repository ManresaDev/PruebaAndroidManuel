package com.manresa.pruebaandroidmanuel.utils

import android.content.Context
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class ShowAlert {
    companion object{
        fun showConfirmation(context : Context, title : String, negativeButton : String, positiveButton : String, message : String ) : Boolean{
           var confirmacion = false
            MaterialAlertDialogBuilder(context)
                .setTitle(title)
                .setMessage(message)
                .setNegativeButton(negativeButton){ dialog, which ->
                    dialog.dismiss()
                    confirmacion = false
                }
                .setPositiveButton(positiveButton) { dialog, which ->
                    dialog.dismiss()
                    confirmacion = true
                }

                .show()
            return confirmacion
        }

    }
}