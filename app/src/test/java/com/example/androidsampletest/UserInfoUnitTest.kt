package com.example.androidsampletest

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.androidsampletest.ui.UserViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.generic.instance
import org.kodein.di.newInstance

class UserInfoUnitTest : KodeinAware {
    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    override val kodein: Kodein = Kodein.lazy {
        import(UserViewModelModule)
    }

    @Test
    fun userInfo_isNotNull() {
        val viewModel by kodein.newInstance { UserViewModel(instance()) }
        GlobalScope.launch(Dispatchers.IO) {
            // when
            val res = viewModel.getUserInfo()

            // then
            Assert.assertNotNull(res)
        }
    }

    @Test
    fun userInfo_isCorrectSize() {
        val viewModel by kodein.newInstance { UserViewModel(instance()) }
        GlobalScope.launch(Dispatchers.IO) {
            // when
            val res = viewModel.getUserInfo()

            // then
            Assert.assertEquals(10, res.size)
        }
    }

    @Test
    fun userInfo_isCorrectItem() {
        val viewModel by kodein.newInstance { UserViewModel(instance()) }
        GlobalScope.launch(Dispatchers.IO) {
            // when
            val res = viewModel.getUserInfo()

            // then
            Assert.assertSame("Leanne Graham", res.get(0).name)
        }
    }


}