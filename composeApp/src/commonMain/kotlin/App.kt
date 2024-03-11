import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import api.ApiClass
import com.seiko.imageloader.rememberImagePainter
import kotlinx.coroutines.launch
import model.Photo
import org.jetbrains.compose.resources.ExperimentalResourceApi
import screens.LargeMainScreenUI
import screens.MainScreenUI

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
                LargeMainScreenUI()
            } else {
                MainScreenUI()
            }
        }
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