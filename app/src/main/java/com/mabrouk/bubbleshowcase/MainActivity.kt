package com.mabrouk.bubbleshowcase

import android.app.Activity
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.mabrouk.showcases.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val titleFont:Typeface=Typeface.createFromAsset(
            assets,
            "fonts/opensans_extrabold.ttf"
        )

        val contentFont:Typeface=Typeface.createFromAsset(
            assets,
            "fonts/opensans_regular.ttf"
        )
        var models:MutableList<ShowCaseModel> = ArrayList()
        models.add(ShowCaseModel(textView,"text View ","ducucducud","textViewone",R.color.colorGreen,R.drawable.next_btn,textColor = R.color.white,titleFont = titleFont,contentFont = contentFont))
        models.add(ShowCaseModel(button,"button one ","ducucducud","buttonOne",R.color.colorOrange,R.drawable.next_btn,titleFont = titleFont,contentFont = contentFont))
        models.add(ShowCaseModel(textView2,"text View 2 ","ducucducud","textView2",R.color.colorRed,R.drawable.next_btn,titleFont = titleFont,contentFont = contentFont,scrollHere = true))
        models.add(ShowCaseModel(button3,"Button 3 ","ducucducud","button3",R.color.colorPink,R.drawable.next_btn,titleFont = titleFont,contentFont = contentFont))
        models.add(ShowCaseModel(button2,"Button 2 ","ducucducud","button2",R.color.colorBlueGray,R.drawable.next_btn,titleFont = titleFont,contentFont = contentFont))
        ShowCaseUtil.showCases(this,models,object : BubbleShowCaseListener{
            override fun onTargetClick(bubbleShowCase: BubbleShowCase) {

            }

            override fun onCloseActionImageClick(bubbleShowCase: BubbleShowCase,scroll:Boolean) {
            }

            override fun onBackgroundDimClick(bubbleShowCase: BubbleShowCase) {
            }

            override fun onBubbleClick(bubbleShowCase: BubbleShowCase) {
            }

            override fun onLastItem(bubbleShowCase: BubbleShowCase) {
                Toast.makeText(this@MainActivity,"Last Item${bubbleShowCase.getTargetView()?.bottom}  ",Toast.LENGTH_SHORT).show()
            }

        })
    }

    fun showCases(activity: Activity,models:List<ShowCaseModel>,listener:BubbleShowCaseListener){
        var data:MutableList<BubbleShowCaseBuilder> = ArrayList()
        models.forEach {
            data.add(BubbleShowCaseBuilder(activity)
                .title(it.title)
                .description(it.content)
                .titleColor(it.textColor)
                .contentColor(it.contentColor)
                .backgroundColorResourceId(it.backGround)
                .titleTextSize(it.titleSize)
                .titleFont(
                    Typeface.createFromAsset(
                        assets,
                        "fonts/opensans_extrabold.ttf"
                    ))
                .contentFont(
                    Typeface.createFromAsset(
                        assets,
                        "fonts/opensans_regular.ttf.ttf"
                    ))
                .descriptionTextSize(it.contentSize)
                .showNextButton(true)
                .nextButtonText("Next")
                .nextButtonColorResourceId(R.color.white)
                .nextButtonTextSize(10f)
                .showSkip(true)
                .skipTextSize(10f)
                .skipResourceId(R.drawable.next_btn)
                .skipText("Skip All")
                .skipTextColorResourceId(R.color.white)
                .nextButtonResourceId(R.drawable.next_btn)
                .showOnce(it.key)
                .closeActionImageResourceId(R.drawable.ic_close_black_24dp)
                .imageResourceId(R.drawable.download__1_)
                .highlightMode(BubbleShowCase.HighlightMode.VIEW_SURFACE)
                .listener(listener).targetView(it.view)
            )

        }
        BubbleShowCaseSequence()
            .addShowCases(data).show()
    }




}
