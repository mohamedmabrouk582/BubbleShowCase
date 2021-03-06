package com.mabrouk.showcases

import android.app.Activity
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.view.View
import android.view.ViewTreeObserver
import androidx.annotation.IdRes
import androidx.core.content.ContextCompat
import java.lang.ref.WeakReference
import java.util.ArrayList

/*
* Created By mabrouk on 28/05/19
* BubbleShowCase
*/
class BubbleShowCaseBuilder{

    internal var mActivity: WeakReference<Activity>? = null
    internal var mImage: Drawable? = null
    internal var mTitle: String? = null
    internal var mSubtitle: String? = null
    internal var mCloseAction: Drawable? = null
    internal var mBackgroundColor: Int? = null
    internal var mTextColor: Int? = null
    internal var mContentColor: Int? = null
    internal var mTitleTextSize: Int? = null
    internal var mSubtitleTextSize: Int? = null
    internal var mHighlightMode: BubbleShowCase.HighlightMode? = null
    internal var mDisableTargetClick: Boolean = false
    internal var mDisableCloseAction: Boolean = false
    internal var mShowOnce: String? = null
    internal var mIsFirstOfSequence: Boolean? = null
    internal var mIsLastOfSequence: Boolean? = null
    internal val mArrowPositionList = ArrayList<BubbleShowCase.ArrowPosition>()
    internal var mTargetView: WeakReference<View>? = null
    internal var mBubbleShowCaseListener: BubbleShowCaseListener? = null
    internal var mSequenceShowCaseListener: SequenceShowCaseListener? = null
    internal var mTitleFont:Typeface?=null
    internal var mSubTitleFont:Typeface?=null
    internal var nextDrawable:Drawable?=null
    internal var nextButtonText:String?=null
    internal var nextButtonTextColor:Int?=null
    internal var nextButtonTextSize:Float?=null
    internal var nextButtonTextFont:Typeface?=null
    internal var showNextButton:Boolean?=null
    internal var nextButtonResourceId:Int?=null
    internal var skipText:String?=null
    internal var skipTextColor:Int?=null
    internal var skipTextFont:Typeface?=null
    internal var skipTextSize:Float?=null
    internal var skipDrawable:Drawable?=null
    internal var skipResourceId:Int?=null
    internal var showSkip:Boolean?=null
    internal var scrollHere:Boolean=false
    internal var allowAnimation:Boolean=true






    private var onGlobalLayoutListenerTargetView: ViewTreeObserver.OnGlobalLayoutListener? = null

    /**
     * Builder constructor. It needs an instance of the current activity to convert it to a weak reference in order to avoid memory leaks
     */
    constructor(activity: Activity){
        mActivity = WeakReference(activity)
    }

    /**
     * Title of the BubbleShowCase. This text is bolded in the view.
     */
    fun title(title: String): BubbleShowCaseBuilder {
        mTitle = title
        return this
    }

    /**
     * Additional description of the BubbleShowCase. This text has a regular format
     */
    fun description(subtitle: String): BubbleShowCaseBuilder {
        mSubtitle = subtitle
        return this
    }

    /**
     * Image drawable to inserted as main image in the BubbleShowCase
     *  - If this param is not passed, the BubbleShowCase will not have main image
     */
    fun image(image: Drawable): BubbleShowCaseBuilder {
        mImage = image
        return this
    }

    /**
     * Image resource id to insert the corresponding drawable as main image in the BubbleShowCase
     *  - If this param is not passed, the BubbleShowCase will not have main image
     */
    fun imageResourceId(resId: Int): BubbleShowCaseBuilder {
        mImage = ContextCompat.getDrawable(mActivity?.get()!!, resId)
        return this
    }

    /**
     * Image drawable to be inserted as close icon in the BubbleShowCase.
     *  - If this param is not defined, a default close icon is displayed
     */
    fun closeActionImage(image: Drawable?): BubbleShowCaseBuilder {
        mCloseAction = image
        return this
    }

    /**
     * Image resource id to insert the corresponding drawable as close icon in the BubbleShowCase.
     *  - If this param is not defined, a default close icon is displayed
     */
    fun closeActionImageResourceId(resId: Int): BubbleShowCaseBuilder {
        mCloseAction = ContextCompat.getDrawable(mActivity?.get()!!, resId)
        return this
    }


    /**
     * Background color of the BubbleShowCase.
     *  - #3F51B5 color will be set if this param is not defined
     */
    fun backgroundColor(color: Int): BubbleShowCaseBuilder {
        mBackgroundColor = color
        return this
    }

    /**
     * Background color of the BubbleShowCase.
     *  - #3F51B5 color will be set if this param is not defined
     */
    fun backgroundColorResourceId(colorResId: Int): BubbleShowCaseBuilder {
        mBackgroundColor = ContextCompat.getColor(mActivity?.get()!!, colorResId)
        return this
    }

    /**
     * Text color of the BubbleShowCase.
     *  - White color will be set if this param is not defined
     */
    fun titleColor(color: Int): BubbleShowCaseBuilder {
        mTextColor = color
        return this
    }

    fun contentColor(color: Int): BubbleShowCaseBuilder {
        mContentColor = color
        return this
    }

    /**
     * Text color of the BubbleShowCase.
     *  - White color will be set if this param is not defined
     */
    fun titleColorResourceId(colorResId: Int): BubbleShowCaseBuilder {
        mTextColor = ContextCompat.getColor(mActivity?.get()!!, colorResId)
        return this
    }

    fun contentColorResourceId(colorResId: Int): BubbleShowCaseBuilder {
        mContentColor = ContextCompat.getColor(mActivity?.get()!!, colorResId)
        return this
    }


    /**
     * Title text size in SP.
     * - Default value -> 16 sp
     */
    fun titleTextSize(textSize: Int): BubbleShowCaseBuilder {
        mTitleTextSize = textSize
        return this
    }

    /**
     * Description text size in SP.
     * - Default value -> 14 sp
     */
    fun descriptionTextSize(textSize: Int): BubbleShowCaseBuilder {
        mSubtitleTextSize = textSize
        return this
    }

    /**
     * If an unique id is passed in this function, this BubbleShowCase will only be showed once
     * - ID to identify the BubbleShowCase
     */
    fun showOnce(id: String): BubbleShowCaseBuilder {
        mShowOnce = id
        return this
    }

    /**
     * Target view to be highlighted. Set a TargetView is essential to figure out BubbleShowCase position
     * - If a target view is not defined, the BubbleShowCase final position will be the center of the screen
     */
    fun targetView(targetView: View): BubbleShowCaseBuilder {
        mTargetView = WeakReference(targetView)
        return this
    }

    /**
     * If this variable is true, when user clicks on the target, the showcase will not be dismissed
     *  Default value -> false
     */
    fun disableTargetClick(isDisabled: Boolean): BubbleShowCaseBuilder{
        mDisableTargetClick = isDisabled
        return this
    }

    /**
     * If this variable is true, close action button will be gone
     *  Default value -> false
     */
    fun disableCloseAction(isDisabled: Boolean): BubbleShowCaseBuilder{
        mDisableCloseAction = isDisabled
        return this
    }

    /**
     * Insert an arrowPosition to force the position of the BubbleShowCase.
     * - ArrowPosition enum values: LEFT, RIGHT, TOP and DOWN
     * - If an arrow position is not defined, the BubbleShowCase will be set in a default position depending on the targetView
     */
    fun arrowPosition(arrowPosition: BubbleShowCase.ArrowPosition): BubbleShowCaseBuilder {
        mArrowPositionList.clear()
        mArrowPositionList.add(arrowPosition)
        return this
    }

    /**
     * Insert a set of arrowPosition to force the position of the BubbleShowCase.
     * - ArrowPosition enum values: LEFT, RIGHT, TOP and DOWN
     * - If the number of arrow positions is 0 or more than 1, BubbleShowCase will be set on the center of the screen
     */
    fun arrowPosition(arrowPosition: List<BubbleShowCase.ArrowPosition>): BubbleShowCaseBuilder {
        mArrowPositionList.clear()
        mArrowPositionList.addAll(arrowPosition)
        return this
    }

    /**
     * Highlight mode. It represents the way that the target view will be highlighted
     * - VIEW_LAYOUT: Default value. All the view box is highlighted (the rectangle where the view is contained). Example: For a TextView, all the element is highlighted (characters and background)
     * - VIEW_SURFACE: Only the view surface is highlighted, but not the background. Example: For a TextView, only the characters will be highlighted
     */
    fun highlightMode(highlightMode: BubbleShowCase.HighlightMode): BubbleShowCaseBuilder {
        mHighlightMode = highlightMode
        return this
    }

    /**
     * Add a BubbleShowCaseListener in order to listen the user actions:
     * - onTargetClick -> It is triggered when the user clicks on the target view
     * - onCloseClick -> It is triggered when the user clicks on the close icon
     */
    fun listener(bubbleShowCaseListener: BubbleShowCaseListener): BubbleShowCaseBuilder {
        mBubbleShowCaseListener = bubbleShowCaseListener
        return this
    }

    /**
     * Add a sequence listener in order to know when a BubbleShowCase has been dismissed to show another one
     */
    internal fun sequenceListener(sequenceShowCaseListener: SequenceShowCaseListener): BubbleShowCaseBuilder {
        mSequenceShowCaseListener = sequenceShowCaseListener
        return this
    }

    internal fun isFirstOfSequence(isFirst: Boolean): BubbleShowCaseBuilder{
        mIsFirstOfSequence = isFirst
        return this
    }

    internal fun isLastOfSequence(isLast: Boolean): BubbleShowCaseBuilder{
        mIsLastOfSequence = isLast
        return this
    }

    internal fun titleFont(font:Typeface) : BubbleShowCaseBuilder{
        this.mTitleFont=font
        return this
    }

    internal fun subTitleFont(font: Typeface):BubbleShowCaseBuilder{
        this.mSubTitleFont=font
        return this
    }

    fun nextButtonText(text:String): BubbleShowCaseBuilder {
        nextButtonText=text
        return this
    }

    fun nextButtonTextColor(color:Int): BubbleShowCaseBuilder{
        nextButtonTextColor=color
        return this
    }


    fun nextButtonTextSize(size:Float): BubbleShowCaseBuilder{
        nextButtonTextSize=size
        return this
    }

    fun nextButtonTextFont(font: Typeface): BubbleShowCaseBuilder{
        nextButtonTextFont=font
        return this
    }


    fun nextButtonDrawable(drawable: Drawable): BubbleShowCaseBuilder {
        nextDrawable=drawable
        return this
    }

    fun showNextButton(show:Boolean) : BubbleShowCaseBuilder{
        showNextButton=show
        return this
    }

    fun nextButtonResourceId(id:Int?): BubbleShowCaseBuilder {
        nextButtonResourceId=id
        return this
    }

    fun skipDrawable(drawable: Drawable?):BubbleShowCaseBuilder{
        skipDrawable=drawable
        return this
    }

    fun skipResourceId(icon:Int?):BubbleShowCaseBuilder {
        skipResourceId=icon
        return this
    }

    fun skipText(text:String?):BubbleShowCaseBuilder{
        skipText=text
        return this
    }

    fun skipTextColor(color:Int?):BubbleShowCaseBuilder{
        skipTextColor=color
        return this
    }

    fun titleFont(font: Typeface?):BubbleShowCaseBuilder{
        mTitleFont=font
        return this
    }


    fun contentFont(font: Typeface?):BubbleShowCaseBuilder{
        mSubTitleFont=font
        return this
    }

    fun skipTextFont(font: Typeface?):BubbleShowCaseBuilder{
        skipTextFont=font
        return this
    }

    fun skipTextSize(size:Float?):BubbleShowCaseBuilder{
        skipTextSize=size
        return this
    }

    fun showSkip(show:Boolean?):BubbleShowCaseBuilder{
        showSkip=show
        return this
    }

    fun nextButtonColorResourceId(colorResId: Int): BubbleShowCaseBuilder {
        nextButtonTextColor = ContextCompat.getColor(mActivity?.get()!!, colorResId)
        return this
    }

    fun skipTextColorResourceId(colorResId: Int): BubbleShowCaseBuilder {
        skipTextColor = ContextCompat.getColor(mActivity?.get()!!, colorResId)
        return this
    }

    fun scroolHere(scroll:Boolean) : BubbleShowCaseBuilder{
        this.scrollHere=scroll
        return this
    }

    fun allowTargetAnimation(allow:Boolean):BubbleShowCaseBuilder{
        this.allowAnimation=allow
        return this
    }

    /**
     * Build the BubbleShowCase object from the builder one
     */
    private fun build(): BubbleShowCase {
        if(mIsFirstOfSequence ==null)
            mIsFirstOfSequence = true
        if(mIsLastOfSequence ==null)
            mIsLastOfSequence = true

        return BubbleShowCase(this)
    }

    /**
     * Show the BubbleShowCase using the params added previously
     */
    fun show(overLayColor: Int=R.color.transparent_grey): BubbleShowCase{
        val bubbleShowCase = build()
        if (mTargetView != null) {
            val targetView = mTargetView!!.get()
            if (targetView!!.height == 0 || targetView.width == 0) {
                //If the view is not already painted, we wait for it waiting for view changes using OnGlobalLayoutListener
                onGlobalLayoutListenerTargetView = ViewTreeObserver.OnGlobalLayoutListener {
                    bubbleShowCase.show(overLayColor)
                    targetView.viewTreeObserver.removeOnGlobalLayoutListener(onGlobalLayoutListenerTargetView)
                }
                targetView.viewTreeObserver.addOnGlobalLayoutListener(onGlobalLayoutListenerTargetView)
            } else {
                bubbleShowCase.show(overLayColor)
            }
        } else {
            bubbleShowCase.show(overLayColor)
        }
        return bubbleShowCase
    }

}