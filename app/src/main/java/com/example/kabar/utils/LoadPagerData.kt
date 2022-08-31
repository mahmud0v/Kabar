package com.example.kabar.utils

import com.example.kabar.R
import com.example.kabar.model.PagerInfo

class LoadPagerData {

    companion object {

        fun getPagerData() = listOf<PagerInfo>(
            PagerInfo(R.drawable.intro1, R.string.intro1_head_txt, R.string.intro1_desc),
            PagerInfo(R.drawable.intro2, R.string.intro2_head_txt, R.string.intro2_desc),
            PagerInfo(R.drawable.intro3, R.string.intro3_head_txt, R.string.intro3_desc)
        )

    }

}