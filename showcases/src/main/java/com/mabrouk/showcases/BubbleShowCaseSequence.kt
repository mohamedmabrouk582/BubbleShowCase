package com.mabrouk.showcases

/*
* Created By mabrouk on 28/05/19
* BubbleShowCase
*/
class BubbleShowCaseSequence{
    private val mBubbleShowCaseBuilderList = ArrayList<BubbleShowCaseBuilder>()

    init{
        mBubbleShowCaseBuilderList.clear()
    }

    fun addShowCase(bubbleShowCaseBuilder: BubbleShowCaseBuilder): BubbleShowCaseSequence {
        mBubbleShowCaseBuilderList.add(bubbleShowCaseBuilder)
        return this
    }

    fun addShowCases(bubbleShowCaseBuilderList: List<BubbleShowCaseBuilder>): BubbleShowCaseSequence {
        mBubbleShowCaseBuilderList.addAll(bubbleShowCaseBuilderList)
        return this
    }

    fun show(overLayColor:Int = R.color.transparent_grey) = showing(0,overLayColor)

     fun showing(position: Int,overLayColor:Int = R.color.transparent_grey){
        if(position >= mBubbleShowCaseBuilderList.size)
            return

        when(position){
            0 -> {
                mBubbleShowCaseBuilderList[position].isFirstOfSequence(true)
                mBubbleShowCaseBuilderList[position].isLastOfSequence(false)
            }
            mBubbleShowCaseBuilderList.size-1 -> {
                mBubbleShowCaseBuilderList[position].isFirstOfSequence(false)
                mBubbleShowCaseBuilderList[position].isLastOfSequence(true)
            }
            else -> {
                mBubbleShowCaseBuilderList[position].isFirstOfSequence(false)
                mBubbleShowCaseBuilderList[position].isLastOfSequence(false)
            }
        }
        mBubbleShowCaseBuilderList[position].sequenceListener(object : SequenceShowCaseListener{
            override fun onDismiss() {
                showing(position + 1,overLayColor)
            }
        }).show(overLayColor)
    }

}