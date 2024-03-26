package screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.seiko.imageloader.rememberImagePainter
import model.ShowScreenDataClass

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun LargeShowImageUI(data: ShowScreenDataClass) {
    val navigator = LocalNavigator.currentOrThrow
    Column(modifier = Modifier.fillMaxSize()) {
        Row (modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {

            Card(
                modifier = Modifier.size(100.dp).padding(18.dp),
                shape = RoundedCornerShape(100),
                backgroundColor = Color.White,
                onClick = {
                    navigator.push(LargeMainScreenNav())
                }
            ) {
                Box(modifier = Modifier.fillMaxSize()) {
                    Icon(
                        Icons.Default.ArrowBackIosNew,
                        null,
                        modifier = Modifier.align(Alignment.Center),
                        tint = Color(0xFF202020)
                    )
                }
            }
            Text(
                data.name.uppercase(), color = Color.White, fontSize = 27.sp,
            )
        }
        Card(modifier = Modifier.align(Alignment.CenterHorizontally).padding(vertical = 18.dp, horizontal = 36.dp)) {
            Image(
                rememberImagePainter(data.url),
                null,
            )
        }


    }
}

class LargeMainScreenNav() : Screen {

    @Composable
    override fun Content() {
        LargeMainScreenUI()
    }
}
