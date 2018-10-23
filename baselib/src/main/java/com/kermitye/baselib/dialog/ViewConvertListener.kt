package com.kermitye.baselib.dialog

import android.os.Parcel
import android.os.Parcelable



/**
 * Created by kermitye on 2018/10/19 13:44
 */
abstract class ViewConvertListener : Parcelable {

    abstract fun convertView(holder: DialogViewHolder, dialog: BaseMsgDialog)

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {}

    constructor() {}

    protected constructor(source: Parcel) {}

    companion object {
        val CREATOR: Parcelable.Creator<ViewConvertListener> = object : Parcelable.Creator<ViewConvertListener> {
            override fun createFromParcel(source: Parcel): ViewConvertListener {
                return object : ViewConvertListener(source) {
                    override fun convertView(holder: DialogViewHolder, dialog: BaseMsgDialog) {

                    }
                }
            }

            override fun newArray(size: Int): Array<ViewConvertListener?> {
                return arrayOfNulls(size)
            }
        }
    }
}