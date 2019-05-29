package com.mabrouk.showcases

/*
* Created By mabrouk on 28/05/19
* BubbleShowCase
*/
interface OnBubbleMessageViewListener {
    /**
     * It is called when a user clicks the close action image in the BubbleMessageView
     */
    fun onCloseActionImageClick()


    /**
     * It is called when a user clicks the BubbleMessageView
     */
    fun onBubbleClick()

    fun onNextClick()

    fun onSkipClick()
}