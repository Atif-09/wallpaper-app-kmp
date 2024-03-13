import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.Navigator
import model.Photo
import org.jetbrains.compose.resources.ExperimentalResourceApi
import screens.LargeMainScreenUI
import screens.MainScreenUI
import screens.ShowImageUI

@OptIn(ExperimentalResourceApi::class)
@Composable
fun App() {
    MaterialTheme {
        var showContent by remember { mutableStateOf(false) }
        var urlList by remember { mutableStateOf<List<Photo>>(emptyList()) }
        val scope = rememberCoroutineScope()
        var urlString by remember { mutableStateOf("") }
        Column(
            Modifier.fillMaxSize().background(Color(0xFF202020))
        ) {
            if (getPlatform().name.contains("Desktop") || getPlatform().name.contains("Web")) {
                Navigator(LargeMainScreenNav())
                //LargeMainScreenUI()
            } else {
                Navigator(ShowMainScreen())
                //MainScreenUI()
            }
        }
    }

}

class ShowMainScreen() : Screen {

    @Composable
    override fun Content() {
        MainScreenUI()
    }
}

class LargeMainScreenNav() : Screen {

    @Composable
    override fun Content() {
        LargeMainScreenUI()
    }
}

/*  Button(onClick = {
                 scope.launch {
                     urlList = ApiClass().greeting().photos
                 }

             }) {
                 Text("Ktor!")
             }
             println("List of Photos $urlList")

             LazyVerticalGrid(columns = GridCells.Adaptive(150.dp),modifier = Modifier.fillMaxSize()) {
                 items(urlList) { it ->
                     Image(
                         rememberImagePainter(it.src.original),
                         null,
                         modifier = Modifier.size(150.dp).padding(9.dp).clip(RoundedCornerShape(15.dp))

                     )
                 }
             }*/