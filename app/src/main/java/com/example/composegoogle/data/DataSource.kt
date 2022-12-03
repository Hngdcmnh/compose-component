package com.example.composegoogle.data

import com.example.composegoogle.R
import com.example.composegoogle.model.ItemData

object DataSource {
    val quantityOptions = listOf(
        Pair(R.string.one_cupcake, 1),
        Pair(R.string.six_cupcakes, 6),
        Pair(R.string.twelve_cupcakes, 12)
    )

    val flavors = listOf(
        R.string.vanilla,
        R.string.chocolate,
        R.string.red_velvet,
        R.string.salted_caramel,
        R.string.coffee
    )

    fun loadData():List<ItemData>{
        return listOf(
            ItemData(R.string.title,R.drawable.image1),
            ItemData(R.string.title,R.drawable.image1),
            ItemData(R.string.title,R.drawable.image1),
            ItemData(R.string.title,R.drawable.image1),
            ItemData(R.string.title,R.drawable.image1),
            ItemData(R.string.title,R.drawable.image1),
            ItemData(R.string.title,R.drawable.image1),
            ItemData(R.string.title,R.drawable.image1),
            ItemData(R.string.title,R.drawable.image1),
            ItemData(R.string.title,R.drawable.image1),
            ItemData(R.string.title,R.drawable.image1),
            ItemData(R.string.title,R.drawable.image1),
            ItemData(R.string.title,R.drawable.image1),
            ItemData(R.string.title,R.drawable.image1),
            ItemData(R.string.title,R.drawable.image1),
            ItemData(R.string.title,R.drawable.image1),

        )
    }


}