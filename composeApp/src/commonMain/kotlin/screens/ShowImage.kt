package screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.seiko.imageloader.rememberImagePainter
import kmpnetworktemplate.composeapp.generated.resources.Res
import kmpnetworktemplate.composeapp.generated.resources.pacifico_regular
import kmpnetworktemplate.composeapp.generated.resources.ubuntu_bold
import kotlinx.coroutines.launch
import model.ShowScreenDataClass
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.Font

@OptIn(ExperimentalResourceApi::class, ExperimentalMaterialApi::class)
@Composable
fun ShowImageUI(data: ShowScreenDataClass) {
    val navigator = LocalNavigator.currentOrThrow
    Column(modifier = Modifier.fillMaxSize()) {
        Box(modifier = Modifier.fillMaxWidth()) {
            Text(
                "WallBest",
                modifier = Modifier.padding(top = 12.dp).align(Alignment.Center),
                fontFamily = FontFamily(Font(Res.font.pacifico_regular)),
                fontSize = 27.sp,
                color = Color.White
            )
        }

        Card(
            modifier = Modifier.fillMaxWidth().fillMaxHeight(0.09f)
                .padding(horizontal = 12.dp, vertical = 9.dp),
            shape = RoundedCornerShape(50),
            backgroundColor = Color(0xFF000000)
        ) {
            Box(modifier = Modifier.fillMaxWidth()) {
                Card(
                    modifier = Modifier.width(120.dp).padding(9.dp).align(Alignment.CenterStart),
                    shape = RoundedCornerShape(100),
                    backgroundColor = Color(0xFF202020),
                    onClick = {
                        navigator.push(MainScreenNav())
                    }
                ) {
                    Row(modifier = Modifier.fillMaxSize()) {
                        Icon(
                            Icons.Default.ArrowBackIosNew,
                            null,
                            modifier = Modifier.align(Alignment.CenterVertically)
                                .padding(horizontal = 6.dp),
                            tint = Color.White
                        )
                        Text(
                            "Back",
                            modifier = Modifier.align(Alignment.CenterVertically),
                            color = Color.White,
                            fontFamily = FontFamily(Font(Res.font.ubuntu_bold))
                        )
                    }
                }

                Row(modifier = Modifier.align(Alignment.CenterEnd)) {
                    Card(
                        modifier = Modifier.size(50.dp).padding(6.dp),
                        shape = RoundedCornerShape(100),
                        backgroundColor = Color(0xFF202020),
                        onClick = {
                        }
                    ) {
                        Box(modifier = Modifier.fillMaxSize()) {
                            Icon(
                                Icons.Default.Download,
                                null,
                                modifier = Modifier.align(Alignment.Center),
                                tint = Color.White
                            )
                        }
                    }

                    Card(
                        modifier = Modifier.size(50.dp).padding(6.dp),
                        shape = RoundedCornerShape(100),
                        backgroundColor = Color(0xFF202020),
                        onClick = {
                        }
                    ) {
                        Box(modifier = Modifier.fillMaxSize()) {
                            Icon(
                                Icons.Default.Star,
                                null,
                                modifier = Modifier.align(Alignment.Center),
                                tint = Color.White
                            )
                        }
                    }
                }

                /* Card(
                     modifier = Modifier.fillMaxWidth(0.2f).padding(6.dp).align(Alignment.CenterStart),
                     shape = RoundedCornerShape(50),
                     backgroundColor = Color(0xFF202020),
                     onClick = {
                     }
                 ) {
                     Row(modifier = Modifier.fillMaxSize()) {
                         Icon(Icons.Default.ArrowBackIosNew, null, modifier = Modifier.align(Alignment.CenterVertically).padding(start = 12.dp), tint = Color.White)
                         Text(
                             "Go",
                             modifier = Modifier.align(Alignment.CenterVertically),
                             color = Color.White
                         )
                     }
                 }*/


            }


        }
        if (data.name.isNotEmpty()) {
            Box(modifier = Modifier.fillMaxWidth()) {
                Text(
                    data.name.uppercase(), color = Color.White,
                    modifier = Modifier.align(Alignment.Center).padding(horizontal = 12.dp),
                    fontFamily = FontFamily(Font(Res.font.ubuntu_bold))
                )
            }
        }

        Card(
            modifier = Modifier.fillMaxWidth().padding(10.dp),
            shape = RoundedCornerShape(15.dp),
            border = BorderStroke(1.dp, Color(0x33FFFFFF))
        ) {
            Image(
                rememberImagePainter(data.url),
                null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )


        }

    }
}

class MainScreenNav() : Screen {

    @Composable
    override fun Content() {
        MainScreenUI()
    }
}