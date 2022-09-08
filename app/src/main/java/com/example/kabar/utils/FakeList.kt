package com.example.kabar.utils

import com.example.kabar.R
import com.example.kabar.model.NewsInfo

class FakeList {

    companion object{
        fun getFakeList() =
            listOf(
                NewsInfo(R.drawable.zelenski,R.string.mock_text,"BBC News","4 hour ago"),
                NewsInfo(R.drawable.life,R.string.fake_text,"CNN","9 hour ago"),
                NewsInfo(R.drawable.ship,R.string.fake_text,"New York Times","1 week ago"),
                NewsInfo(R.drawable.zelenski,R.string.mock_text,"BBC News","4 hour ago"),
                NewsInfo(R.drawable.life,R.string.fake_text,"CNN","9 hour ago"),
                NewsInfo(R.drawable.ship,R.string.fake_text,"New York Times","1 week ago"),
                NewsInfo(R.drawable.zelenski,R.string.fake_text,"BBC News","4 hour ago"),
                NewsInfo(R.drawable.life,R.string.fake_text,"CNN","9 hour ago"),
                NewsInfo(R.drawable.ship,R.string.fake_text,"New York Times","1 week ago"),
                NewsInfo(R.drawable.zelenski,R.string.fake_text,"BBC News","4 hour ago"),
                NewsInfo(R.drawable.life,R.string.fake_text,"CNN","9 hour ago"),
                NewsInfo(R.drawable.ship,R.string.fake_text,"New York Times","1 week ago"),
                NewsInfo(R.drawable.zelenski,R.string.fake_text,"BBC News","4 hour ago")
            )
    }
}