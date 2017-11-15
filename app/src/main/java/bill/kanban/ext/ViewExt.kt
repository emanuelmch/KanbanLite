package bill.kanban.ext

import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

fun Fragment.inflate(@LayoutRes layout: Int,
                     root: ViewGroup? = null,
                     attachToRoot: Boolean = root != null): View =
        LayoutInflater.from(context).inflate(layout, root, attachToRoot)

fun Fragment.withArgument(key: String, value: Int) =
        this.apply {
            arguments = arguments ?: Bundle()
            arguments!!.putInt(key, value)
        }
