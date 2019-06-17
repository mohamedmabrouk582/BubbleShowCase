package com.mabrouk.showcases

import android.app.Activity
import java.util.ArrayList


/*
* Created By mabrouk on 09/06/19
* BubbleShowCase
*/

class ShowCaseUtil {
    companion object{
//        fun showCase(activity: Activity, caseModels: List<ShowCaseModel>, listener: BubbleShowCaseListener) {
//            val builders = ArrayList<BubbleShowCaseBuilder>()
//            for (model in caseModels) {
//                builders.add(
//                    BubbleShowCaseBuilder(activity)
//                        .title(model.title)
//                        .description(model.content)
//                        .titleTextSize(model.titleSize)
//                        .descriptionTextSize(model.contentSize)
//                        .backgroundColor(model.backGround)
//                        .titleColorResourceId(model.textColor)
//                        .contentColorResourceId(model.textColor)
//                        .showNextButton(true)
//                        .nextButtonResourceId(model.nextButtonResourceId)
//                        .nextButtonColorResourceId(R.color.black)
//                        .disableCloseAction(true)
//                        .showOnce(model.key)
//                        .scroolHere(model.scrollHere)
//                        .listener(listener).targetView(model.view)
//                )
//            }
//            BubbleShowCaseSequence().addShowCases(builders).show(android.R.color.transparent)
//        }

        fun showCases(activity: Activity,models:List<ShowCaseModel>,listener:BubbleShowCaseListener,showClose: Boolean=false){
            var data:MutableList<BubbleShowCaseBuilder> = ArrayList()
            models.forEach {
                data.add(BubbleShowCaseBuilder(activity)
                    .title(it.title)
                    .description(it.content)
                    .titleColor(it.textColor)
                    .contentColor(it.contentColor)
                    .backgroundColorResourceId(it.backGround)
                    .titleTextSize(it.titleSize)
                    .titleFont(it.titleFont)
                    .contentFont(it.contentFont)
                    .descriptionTextSize(it.contentSize)
                    .showNextButton(true)
                    .nextButtonText("Next")
                    .nextButtonResourceId(it.nextButtonResourceId)
                    .nextButtonColorResourceId(R.color.white)
                    .nextButtonTextSize(10f)
                    .showOnce(it.key)
                    .allowTargetAnimation(it.allowTargetAnimation)
                    .disableCloseAction(!showClose)
                    .highlightMode(BubbleShowCase.HighlightMode.VIEW_SURFACE)
                    .listener(listener).targetView(it.view)
                )

            }
            BubbleShowCaseSequence()
                .addShowCases(data).show()
        }
    }
}