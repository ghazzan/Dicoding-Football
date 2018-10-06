package com.example.kotlin.dicodingfootball

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Parcel
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.example.kotlin.dicodingfootball.`interface`.OnItemClickListener
import com.example.kotlin.dicodingfootball.adapter.FootballTeamAdapter
import com.example.kotlin.dicodingfootball.entity.FootballEntity
import com.example.kotlin.dicodingfootball.presenter.MainPresenter
import com.example.kotlin.dicodingfootball.view.MainView
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

@SuppressLint("ParcelCreator")
class MainActivity : AppCompatActivity(), OnItemClickListener, MainView {
    override fun writeToParcel(p0: Parcel?, p1: Int) {

    }

    override fun describeContents(): Int {
        return 0
    }

    private var items: MutableList<FootballEntity> = mutableListOf()
    private var presenter: MainPresenter = MainPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initData()
        rvFootballTeam.layoutManager = LinearLayoutManager(this)
        rvFootballTeam.adapter = FootballTeamAdapter(this, items, this)
    }

    private fun initData(){
        val name = resources.getStringArray(R.array.club_name)
        val image = resources.obtainTypedArray(R.array.club_image)
        val description = resources.getStringArray(R.array.content_club)
        items.clear()

        for (i in name.indices) {
            items.add(FootballEntity(name[i], image.getResourceId(i, 0), description[i]))
        }

        presenter.getMatchEvents()
    }

    override fun onClick(item: FootballEntity) {
        toast("You clicked ${item.name}")
        startActivity<FootballDetailActivity>(FootballDetailActivity.KEY_FOOTBALL_VALUE to item)
    }

    override fun showLoading() {
        toast("Loading ....")
    }

    override fun hideLoading() {
        toast("Hide Loading ...")
    }

    override fun showWarning(message: String) {
        toast("Show Warning !!!")
    }
}
