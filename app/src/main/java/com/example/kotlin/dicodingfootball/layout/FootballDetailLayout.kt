package com.example.kotlin.dicodingfootball.layout

import android.view.Gravity
import com.bumptech.glide.Glide
import com.example.kotlin.dicodingfootball.FootballDetailActivity
import com.example.kotlin.dicodingfootball.entity.FootballEntity
import org.jetbrains.anko.*

class FootballDetailLayout(private val footballEntity: FootballEntity?): AnkoComponent<FootballDetailActivity> {

    override fun createView(ui: AnkoContext<FootballDetailActivity>) = with(ui) {
        verticalLayout {
            val imageFootball = imageView()
                    .lparams(200, 200){
                        this.gravity = Gravity.CENTER_HORIZONTAL
                        margin = dip(20)
                    }

            val nameFootball = textView{
                this.gravity = Gravity.CENTER_HORIZONTAL
            }
            nameFootball.textSize = dip(12).toFloat()

            val descriptionFootball = textView{
                this.gravity = Gravity.CENTER_HORIZONTAL
            }
            descriptionFootball.lparams(matchParent, wrapContent){
                this.setMargins(0, 10, 0, 0)
            }
            descriptionFootball.textSize = dip(12).toFloat()

            Glide.with(context).load(footballEntity?.image).into(imageFootball)
            nameFootball.text = footballEntity?.name
            descriptionFootball.text = footballEntity?.description
        }
    }
}