package com.mabrouk.showcases

import android.content.Context
import android.graphics.*
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.IdRes
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import java.lang.ref.WeakReference

import java.util.ArrayList

/*
* Created By mabrouk on 28/05/19
* BubbleShowCase
*/

class BubbleMessageView : ConstraintLayout {

    private val WIDTH_ARROW = 20

    private var itemView: View? = null

    private var imageViewIcon: ImageView? = null
    private var textViewTitle: TextView? = null
    private var textViewSubtitle: TextView? = null
    private var imageViewClose: ImageView? = null
    private var nextButton:Button?=null
    private var skipButton:Button?=null
    private var showCaseMessageViewLayout: ConstraintLayout? = null

    private var targetViewScreenLocation: RectF? = null
    private var mBackgroundColor: Int = ContextCompat.getColor(context, R.color.blue_default)
    private var arrowPositionList = ArrayList<BubbleShowCase.ArrowPosition>()

    private var paint: Paint? = null

    constructor(context: Context) : super(context) {
        initView()
    }

    constructor(context: Context, builder: Builder) : super(context) {
        initView()
        setAttributes(builder)
        setBubbleListener(builder)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        initView()
    }

    private fun initView() {
        setWillNotDraw(false)

        inflateXML()
        bindViews()
    }

    private fun inflateXML() {
        itemView = inflate(context, R.layout.view_bubble_message, this)
    }

    private fun bindViews() {
        imageViewIcon = findViewById(R.id.imageViewShowCase)
        imageViewClose = findViewById(R.id.imageViewShowCaseClose)
        textViewTitle = findViewById(R.id.textViewShowCaseTitle)
        textViewSubtitle = findViewById(R.id.textViewShowCaseText)
        showCaseMessageViewLayout = findViewById(R.id.showCaseMessageViewLayout)
        nextButton=findViewById(R.id.buttonShowCaseNext)
        skipButton=findViewById(R.id.skipTxt)
    }

    private fun setAttributes(builder: Builder){



        if (builder.showNextButton!=null && builder.showNextButton!!)
            nextButton?.visibility=View.VISIBLE

        if(builder.mImage!=null){
            imageViewIcon?.visibility = View.VISIBLE
            imageViewIcon?.setImageDrawable(builder.mImage!!)
        }
        if(builder.mCloseAction!=null){
            imageViewClose?.visibility = View.VISIBLE
            imageViewClose?.setImageDrawable(builder.mCloseAction!!)
        }

        if(builder.mDisableCloseAction!=null && builder.mDisableCloseAction!!){
            imageViewClose?.visibility = View.INVISIBLE
        }

        if (builder.showSkip!=null && builder.showSkip!!)
            skipButton?.visibility=View.VISIBLE

        builder.skipResourceId?.let {
            skipButton?.visibility=View.VISIBLE
            skipButton?.setBackgroundResource(it)
        }

        builder.skipDrawable?.let {
            skipButton?.visibility=View.VISIBLE
            skipButton?.background=it
        }

        builder.skipText?.let {
            skipButton?.visibility=View.VISIBLE
            skipButton?.text=it
        }

        builder.skipTextSize?.let {
            skipButton?.visibility=View.VISIBLE
            skipButton?.textSize=it
        }

        builder.skipTextColor?.let {
            skipButton?.visibility=View.VISIBLE
            skipButton?.setTextColor(it)
        }

        builder.skipTextFont?.let {
            skipButton?.visibility=View.VISIBLE
            skipButton?.typeface=it
        }

        builder.nextButtonText?.let {
            nextButton?.visibility=View.VISIBLE
            nextButton?.text=it
        }

        builder.nextButtonTextSize?.let {
            nextButton?.visibility=View.VISIBLE
            nextButton?.textSize=it
        }

        builder.nextButtonTextColor?.let {
            nextButton?.visibility=View.VISIBLE
            nextButton?.setTextColor(it)
        }

        builder.nextButtonTextFont?.let {
            nextButton?.visibility=View.VISIBLE
            nextButton?.typeface=it
        }

        builder.nextDrawable?.let {
            nextButton?.visibility=View.VISIBLE
            nextButton?.background=builder.nextDrawable
        }

        builder.nextButtonResourceId?.let {
            nextButton?.visibility=View.VISIBLE
            nextButton?.setBackgroundResource(it)
        }

        builder.mSubTitleFont?.let {
            textViewSubtitle?.visibility = View.VISIBLE
            textViewSubtitle?.typeface=builder.mSubTitleFont
        }

        builder.mTitleFont?.let {
            textViewTitle?.visibility = View.VISIBLE
            textViewTitle?.typeface=builder.mTitleFont
        }

        builder.mTitle?.let {
            textViewTitle?.visibility = View.VISIBLE
            textViewTitle?.text = builder.mTitle
        }
        builder.mSubtitle?.let {
            textViewSubtitle?.visibility = View.VISIBLE
            textViewSubtitle?.text = builder.mSubtitle
        }
        builder.mTextColor?.let {
            textViewTitle?.visibility = View.VISIBLE
            textViewTitle?.setTextColor(builder.mTextColor!!)
        }
        builder.mContentColor?.let {
            textViewSubtitle?.visibility = View.VISIBLE
            textViewSubtitle?.setTextColor(builder.mContentColor!!)
        }
        builder.mTitleTextSize?.let {
            textViewTitle?.visibility = View.VISIBLE
            textViewTitle?.setTextSize(TypedValue.COMPLEX_UNIT_SP, builder.mTitleTextSize!!.toFloat())
        }
        builder.mSubtitleTextSize?.let {
            textViewSubtitle?.visibility = View.VISIBLE
            textViewSubtitle?.setTextSize(TypedValue.COMPLEX_UNIT_SP, builder.mSubtitleTextSize!!.toFloat())
        }
        builder.mBackgroundColor?.let { mBackgroundColor = builder.mBackgroundColor!! }
        arrowPositionList = builder.mArrowPosition
        targetViewScreenLocation = builder.mTargetViewScreenLocation
    }

    private fun setBubbleListener(builder: Builder){
        imageViewClose?.setOnClickListener {builder.mListener?.onCloseActionImageClick()}
        itemView?.setOnClickListener {builder.mListener?.onBubbleClick()}
        nextButton?.setOnClickListener { builder.mListener?.onNextClick()}
        skipButton?.setOnClickListener { builder.mListener?.onSkipClick() }
    }


    //END REGION

    //REGION AUX FUNCTIONS

    private fun getViewWidth(): Int = width

    private fun getMargin(): Int = ScreenUtils.dpToPx(20)

    private fun getSecurityArrowMargin(): Int = getMargin() + ScreenUtils.dpToPx(2 * WIDTH_ARROW / 3)

    //END REGION

    //REGION SHOW ITEM

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        prepareToDraw()
        drawRectangle(canvas)

        for (arrowPosition in arrowPositionList) {
            drawArrow(canvas, arrowPosition, targetViewScreenLocation)
        }
    }

    private fun prepareToDraw() {
        paint = Paint(Paint.ANTI_ALIAS_FLAG)
        paint!!.color = mBackgroundColor
        paint!!.style = Paint.Style.FILL
        paint!!.strokeWidth = 4.0f
    }

    private fun drawRectangle(canvas: Canvas) {
        val rect = RectF(getMargin().toFloat(),
                getMargin().toFloat(),
                getViewWidth() - getMargin().toFloat(),
                height - getMargin().toFloat())
        canvas.drawRoundRect(rect, 10f, 10f, paint!!)
    }

    private fun drawArrow(canvas: Canvas, arrowPosition: BubbleShowCase.ArrowPosition, targetViewLocationOnScreen: RectF?) {
        val xPosition: Int
        val yPosition: Int

        when (arrowPosition) {
            BubbleShowCase.ArrowPosition.LEFT -> {
                xPosition = getMargin()
                yPosition = if(targetViewLocationOnScreen!=null) getArrowVerticalPositionDependingOnTarget(targetViewLocationOnScreen) else height / 2
            }
            BubbleShowCase.ArrowPosition.RIGHT -> {
                xPosition = getViewWidth() - getMargin()
                yPosition = if(targetViewLocationOnScreen!=null) getArrowVerticalPositionDependingOnTarget(targetViewLocationOnScreen) else height / 2
            }
            BubbleShowCase.ArrowPosition.TOP -> {
                xPosition = if(targetViewLocationOnScreen!=null) getArrowHorizontalPositionDependingOnTarget(targetViewLocationOnScreen) else width / 2
                yPosition = getMargin()
            }
            BubbleShowCase.ArrowPosition.BOTTOM -> {
                xPosition = if(targetViewLocationOnScreen!=null) getArrowHorizontalPositionDependingOnTarget(targetViewLocationOnScreen) else width / 2
                yPosition = height - getMargin()
            }
        }

        drawRhombus(canvas, paint, xPosition, yPosition, ScreenUtils.dpToPx(WIDTH_ARROW))
    }

    private fun getArrowHorizontalPositionDependingOnTarget(targetViewLocationOnScreen: RectF?): Int {
        val xPosition: Int
        when {
            isOutOfRightBound(targetViewLocationOnScreen) -> xPosition = width - getSecurityArrowMargin()
            isOutOfLeftBound(targetViewLocationOnScreen) -> xPosition = getSecurityArrowMargin()
            else -> xPosition = Math.round(targetViewLocationOnScreen!!.centerX() - ScreenUtils.getAxisXpositionOfViewOnScreen(this))
        }
        return xPosition
    }

    private fun getArrowVerticalPositionDependingOnTarget(targetViewLocationOnScreen: RectF?): Int {
        val yPosition: Int
        when {
            isOutOfBottomBound(targetViewLocationOnScreen) -> yPosition = height - getSecurityArrowMargin()
            isOutOfTopBound(targetViewLocationOnScreen) -> yPosition = getSecurityArrowMargin()
            else -> yPosition = Math.round(targetViewLocationOnScreen!!.centerY() + ScreenUtils.getStatusBarHeight(context) - ScreenUtils.getAxisYpositionOfViewOnScreen(this))
        }
        return yPosition
    }

    private fun isOutOfRightBound(targetViewLocationOnScreen: RectF?): Boolean {
        return targetViewLocationOnScreen!!.centerX() > ScreenUtils.getAxisXpositionOfViewOnScreen(this) + width - getSecurityArrowMargin()
    }

    private fun isOutOfLeftBound(targetViewLocationOnScreen: RectF?): Boolean {
        return targetViewLocationOnScreen!!.centerX() < ScreenUtils.getAxisXpositionOfViewOnScreen(this) + getSecurityArrowMargin()
    }

    private fun isOutOfBottomBound(targetViewLocationOnScreen: RectF?): Boolean {
        return targetViewLocationOnScreen!!.centerY() > ScreenUtils.getAxisYpositionOfViewOnScreen(this) + height - getSecurityArrowMargin() - ScreenUtils.getStatusBarHeight(context)
    }

    private fun isOutOfTopBound(targetViewLocationOnScreen: RectF?): Boolean {
        return targetViewLocationOnScreen!!.centerY() < ScreenUtils.getAxisYpositionOfViewOnScreen(this) + getSecurityArrowMargin() - ScreenUtils.getStatusBarHeight(context)
    }


    private fun drawRhombus(canvas: Canvas, paint: Paint?, x: Int, y: Int, width: Int) {
        val halfRhombusWidth = width / 2

        val path = Path()
        path.moveTo(x.toFloat(), (y + halfRhombusWidth).toFloat()) // Top
        path.lineTo((x - halfRhombusWidth).toFloat(), y.toFloat()) // Left
        path.lineTo(x.toFloat(), (y - halfRhombusWidth).toFloat()) // Bottom
        path.lineTo((x + halfRhombusWidth).toFloat(), y.toFloat()) // Right
        path.lineTo(x.toFloat(), (y + halfRhombusWidth).toFloat()) // Back to Top
        path.close()
        canvas.drawPath(path, paint!!)
    }


    //END REGION

    /**
     * Builder for BubbleMessageView class
     */
    class Builder{
        lateinit var mContext: WeakReference<Context>
        var mTargetViewScreenLocation: RectF? = null
        var mImage: Drawable? = null
        var mDisableCloseAction: Boolean? = null
        var mTitle: String? = null
        var mSubtitle: String? = null
        var mCloseAction: Drawable? = null
        var mBackgroundColor: Int? = null
        var mTextColor: Int? = null
        var mContentColor: Int? = null
        var mTitleTextSize: Int? = null
        var mSubtitleTextSize: Int? = null
        var mArrowPosition  = ArrayList<BubbleShowCase.ArrowPosition>()
        var mListener: OnBubbleMessageViewListener? = null
        var mTitleFont: Typeface?=null
        var mSubTitleFont: Typeface?=null
        var nextDrawable:Drawable?=null
        var nextButtonText:String?=null
        var nextButtonTextColor:Int?=null
        var nextButtonTextSize:Float?=null
        var nextButtonTextFont:Typeface?=null
        var showNextButton:Boolean?=null
        var nextButtonResourceId:Int?=null
        var skipText:String?=null
        var skipTextColor:Int?=null
        var skipTextFont:Typeface?=null
        var skipTextSize:Float?=null
        var skipDrawable:Drawable?=null
        var skipResourceId:Int?=null
        var showSkip:Boolean?=null
        var scrollHere:Boolean=false

        fun scrollHere(scroll:Boolean) : Builder{
            this.scrollHere=scroll
            return this
        }

        fun skipDrawable(drawable: Drawable?):Builder{
            skipDrawable=drawable
            return this
        }

        fun skipResourceId(icon:Int?):Builder {
            skipResourceId=icon
            return this
        }

        fun skipText(text:String?):Builder{
            skipText=text
            return this
        }

        fun skipTextColor(color:Int?):Builder{
            skipTextColor=color
            return this
        }

        fun skipTextFont(font: Typeface?):Builder{
            skipTextFont=font
            return this
        }

        fun skipTextSize(size:Float?):Builder{
            skipTextSize=size
            return this
        }

        fun showSkip(show:Boolean?):Builder{
            showSkip=show
            return this
        }

        fun contentColor(color:Int?):Builder{
            mContentColor=color
            return this
        }

        fun nextButtonResourceId( id:Int?):Builder{
            nextButtonResourceId=id
            return this
        }


        fun showNextButton(show:Boolean?):Builder{
            showNextButton=show
            return this
        }


        fun nextButtonText(text:String?):Builder{
            nextButtonText=text
            return this
        }

        fun nextButtonTextColor(color:Int?):Builder{
            nextButtonTextColor=color
            return this
        }

        fun nextButtonTextSize(size:Float?):Builder{
            nextButtonTextSize=size
            return this
        }

        fun nextButtonTextFont(font: Typeface?):Builder{
            nextButtonTextFont=font
            return this
        }


        fun nextButtonDrawable(drawable: Drawable?):Builder{
            nextDrawable=drawable
            return this
        }

        fun titleFont(font:Typeface?):Builder{
            mTitleFont=font
            return this
        }

        fun subTitleFont(font:Typeface?):Builder{
            mSubTitleFont=font
            return this
        }

        fun from(context: Context): Builder{
            mContext = WeakReference(context)
            return this
        }

        fun title(title: String?): Builder {
            mTitle = title
            return this
        }

        fun subtitle(subtitle: String?): Builder {
            mSubtitle = subtitle
            return this
        }

        fun image(image: Drawable?): Builder {
            mImage = image
            return this
        }

        fun closeActionImage(image: Drawable?): Builder {
            mCloseAction = image
            return this
        }

        fun disableCloseAction(isDisabled: Boolean): Builder {
            mDisableCloseAction = isDisabled
            return this
        }

        fun targetViewScreenLocation(targetViewLocationOnScreen: RectF): Builder{
            mTargetViewScreenLocation = targetViewLocationOnScreen
            return this
        }

        fun backgroundColor(backgroundColor: Int?): Builder {
            mBackgroundColor = backgroundColor
            return this
        }

        fun textColor(textColor: Int?): Builder {
            mTextColor = textColor
            return this
        }

        fun titleTextSize(textSize: Int?): Builder {
            mTitleTextSize = textSize
            return this
        }

        fun subtitleTextSize(textSize: Int?): Builder {
            mSubtitleTextSize = textSize
            return this
        }

        fun arrowPosition(arrowPosition: List<BubbleShowCase.ArrowPosition>): Builder {
            mArrowPosition.clear()
            mArrowPosition.addAll(arrowPosition)
            return this
        }

        fun listener(listener: OnBubbleMessageViewListener?): Builder {
            mListener = listener
            return this
        }

        fun build(): BubbleMessageView{
            return BubbleMessageView(mContext.get()!!, this)
        }
    }
}