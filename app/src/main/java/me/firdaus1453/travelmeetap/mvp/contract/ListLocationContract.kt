package me.firdaus1453.travelmeetap.mvp.contract


import me.firdaus1453.travelmeetap.Model.PlaceModel

interface ListLocationContract {
    interface View{
        fun setAdapter(places:List<PlaceModel>, view:android.view.View)
        fun showError(error:String?, view:android.view.View)
        fun setviewprogress(view:android.view.View)
        fun hideprogressbar(view:android.view.View)
    }

    interface Presenter{
        fun loadData(search:String)
        fun onProgress(input:String)
        fun setData(places: List<PlaceModel>, search: String)
    }
}