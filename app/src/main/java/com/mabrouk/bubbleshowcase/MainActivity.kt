package com.mabrouk.bubbleshowcase

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mabrouk.showcases.BubbleShowCase
import com.mabrouk.showcases.BubbleShowCaseBuilder
import com.mabrouk.showcases.BubbleShowCaseListener
import com.mabrouk.showcases.BubbleShowCaseSequence
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var models:MutableList<showCaseModel> = ArrayList()
        models.add(showCaseModel(textView,"text View ","ducucducud","textViewone"))
        models.add(showCaseModel(button,"button one ","ducucducud","buttonOne"))
        models.add(showCaseModel(textView2,"text View 2 ","ducucducud","textView2"))
        models.add(showCaseModel(button3,"Button 3 ","ducucducud","button3"))
        models.add(showCaseModel(button2,"Button 2 ","ducucducud","button2"))
        showCases(models)
    }

    fun showCases(models:List<showCaseModel>){
        var data:MutableList<BubbleShowCaseBuilder> = ArrayList()
        models.forEach {
            data.add(BubbleShowCaseBuilder(this)
                .title(it.title)
                .description(it.content)
                .titleColor(Color.parseColor("#000000"))
                .contentColor(Color.parseColor("#00574B"))
                .backgroundColor(Color.parseColor("#ffffff"))
                .titleTextSize(17)
                .descriptionTextSize(18)
                .disableCloseAction(true)
                .showNextButton(true)
                .nextButtonText("Next")
                .nextButtonTextColor(Color.parseColor("#ffffff"))
                .nextButtonTextSize(10f)
                .showSkip(true)
                .skipResourceId(R.drawable.next_btn)
                .skipText("Skip All")
                .skipTextColorResourceId(R.color.colorAccent)
                .nextButtonResourceId(R.drawable.next_btn)
                .showOnce(it.key)
                .listener(object : BubbleShowCaseListener {
                    override fun onTargetClick(bubbleShowCase: BubbleShowCase) {

                    }

                    override fun onCloseActionImageClick(bubbleShowCase: BubbleShowCase) {

                    }

                    override fun onBackgroundDimClick(bubbleShowCase: BubbleShowCase) {

                    }

                    override fun onBubbleClick(bubbleShowCase: BubbleShowCase) {

                    }

                }).targetView(it.view)
            )

        }
        BubbleShowCaseSequence()
            .addShowCases(data).show()
    }




}
