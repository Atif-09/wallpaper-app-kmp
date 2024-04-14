import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.Navigator
import screens.LargeMainScreenUI
import screens.MainScreenUI

@Composable
fun App() {
    MaterialTheme {
        Column(
            Modifier.fillMaxSize().background(Color(0xFF202020))
        ) {
            if (getPlatform().name.contains("Desktop") || getPlatform().name.contains("Web")) {
                Navigator(LargeMainScreenNav())
            } else {
                Navigator(ShowMainScreen())
            }
        }
    }

}

class ShowMainScreen : Screen {

    @Composable
    override fun Content() {
        MainScreenUI()
    }
}

class LargeMainScreenNav : Screen {

    @Composable
    override fun Content() {
        LargeMainScreenUI()
    }
}