package com.mabrouk.showcases

import android.graphics.drawable.Drawable
import android.view.View
import androidx.annotation.IdRes


/*
* Created By mabrouk on 09/06/19
* BubbleShowCase
*/
data class ShowCaseModel(val view: View, val title:String, val content:String,val key:String, val backGround:Int = R.color.transparent_grey,val nextButtonResourceId:Int, val textColor:Int=R.color.black,val contentColor:Int=R.color.black,val titleSize:Int=12,val contentSize:Int=12,val scrollHere:Boolean=false)