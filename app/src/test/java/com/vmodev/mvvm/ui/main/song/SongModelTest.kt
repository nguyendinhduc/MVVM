package com.vmodev.mvvm.ui.main.song

import com.vmodev.mvvm.data.model.ItemSong
import com.vmodev.mvvm.data.remote.ApiSong
import io.reactivex.Observable
import io.reactivex.observers.TestObserver
import org.junit.Assert
import org.hamcrest.core.Is
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import java.lang.Exception
import java.lang.ref.WeakReference

@RunWith(JUnit4::class)
class SongModelTest {
    @Mock
    private lateinit var api: ApiSong
    @Mock
    private lateinit var callBack: WeakReference<SongCallBack>

    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
    }
    @Test
    fun getSongsTest(){
        val songs = arrayListOf<ItemSong>()
        var song = ItemSong()
        song.artistName = "Noo Phước Thịnh"
        song.id = "1"
        song.keySearch = "Xa em"
        song.linkSong = "https://chiasenhac.vn/mp3/tan-nhan/xa-em-ts35vvs0qh224n.html"
        song.songName = "Xa em"
        song.linkImage = "https://data.chiasenhac.com/data/cover/3/2673.jpg"
        songs.add(song)
        song = ItemSong()
        song.artistName = "Cẩm ly"
        song.id = "1"
        song.keySearch = "Xa em"
        song.linkSong = "https://chiasenhac.vn/mp3/tan-nhan/xa-em-ts35vvs0qh224n.html"
        song.songName = "Xa em"
        song.linkImage = "https://data.chiasenhac.com/data/cover/3/2673.jpg"
        songs.add(song)

        Mockito.`when`(api.getSongs("Xa em")).thenReturn(Observable.just(songs))
        val testOb = TestObserver<MutableList<ItemSong>>()
        api.getSongs("Xa em").subscribe(testOb)
        testOb.assertComplete()
        testOb.assertNoErrors()
        testOb.assertValueCount(1)
        val results = testOb.values()
        Assert.assertThat(results.size, Is.`is`(1))
        Assert.assertThat(results[0].size, Is.`is`(2))
        Assert.assertThat(results[0][0].linkImage,Is.`is`("https://data.chiasenhac.com/data/cover/3/2673.jpg"))

//        val inOrder = Mockito.inOrder(callBack)
//        inOrder.verify(callBack).get()!!.error(ItemSong::class.java.name, Exception("Null"))
    }

}