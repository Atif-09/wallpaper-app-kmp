package screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.lazy.staggeredgrid.itemsIndexed
import androidx.compose.foundation.lazy.staggeredgrid.rememberLazyStaggeredGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Chip
import androidx.compose.material.ChipDefaults
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDownward
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.SendTimeExtension
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import api.ApiClass
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.seiko.imageloader.rememberImagePainter
import kmpnetworktemplate.composeapp.generated.resources.Res
import kmpnetworktemplate.composeapp.generated.resources.image
import kmpnetworktemplate.composeapp.generated.resources.image_1
import kmpnetworktemplate.composeapp.generated.resources.image_2
import kmpnetworktemplate.composeapp.generated.resources.logo
import kmpnetworktemplate.composeapp.generated.resources.pacifico_regular
import kotlinx.coroutines.launch
import model.Photo
import model.ShowScreenDataClass
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.Font
import org.jetbrains.compose.resources.painterResource


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun LargeMainScreenUI() {
    val scope = rememberCoroutineScope()
    val navigator = LocalNavigator.currentOrThrow
    var urlList by remember { mutableStateOf<List<Photo>>(emptyList()) }
    scope.launch {
        urlList = ApiClass().greeting().photos
    }
    Column(modifier = Modifier.fillMaxSize()) {

        LazyVerticalGrid(columns = GridCells.Adaptive(200.dp), modifier = Modifier.padding(horizontal = 12.dp, vertical = 12.dp), horizontalArrangement = Arrangement.spacedBy(9.dp), verticalArrangement = Arrangement.spacedBy(9.dp)){
            itemsIndexed(urlList) { index, imageResource ->
                println("Size of the photos${urlList.size}")
                Card(
                    modifier = Modifier.size(200.dp),
                    shape = RoundedCornerShape(7.dp),
                    border = BorderStroke(1.dp, Color(0xFFC4C4C4)),
                    onClick = {
                        navigator.push(ShowLargeImageScreenNav(ShowScreenDataClass(imageResource.alt, imageResource.src.original)))
                    }
                ) {
                    /*Image(
                        imageResource, null, modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )*/
                    Image(
                        rememberImagePainter(imageResource.src.medium),
                        null,
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                }

            }
        }

    }

/*    Row(modifier = Modifier.fillMaxSize()) {
        LeftScreenUI(modifier = Modifier.fillMaxWidth(0.4f))
        Divider(
            color = Color.White,
            thickness = 2.dp,
            modifier = Modifier.width(2.dp).fillMaxHeight().padding(horizontal = 3.dp)
        )
        RightScreenUI(modifier = Modifier.fillMaxWidth(1f))
    }*/
}


@OptIn(ExperimentalResourceApi::class, ExperimentalMaterialApi::class)
@Composable
fun LeftScreenUI(modifier: Modifier) {

    val listOfChips = mutableListOf<ChipsNames>(
        ChipsNames("Discover"),
        ChipsNames("Nature"),
        ChipsNames("Anime"),
        ChipsNames("Architect"),
        ChipsNames("Tech")
    )
    var selected by remember { mutableStateOf(false) }

    Box(modifier = modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {
            Box(modifier = Modifier.fillMaxWidth()) {
                /*Text(
                    "WallBest",
                    modifier = Modifier.padding(top = 12.dp).align(Alignment.Center),
                    fontFamily = FontFamily(Font(Res.font.pacifico_regular)),
                    fontSize = 27.sp,
                    color = Color.White
                )*/
                Image(
                    painterResource(Res.drawable.logo),
                    null,
                    modifier.padding(12.dp).align(Alignment.Center)
                )
            }

            Card(
                modifier = Modifier.fillMaxWidth()
                    .padding(horizontal = 12.dp, vertical = 9.dp),
                shape = RoundedCornerShape(30.dp),
                backgroundColor = Color(0xFF000000)
            ) {
                LazyRow(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(9.dp),
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 6.dp)
                ) {
                    items(listOfChips) { chips ->
                        Chip(
                            onClick = {
                                selected = true
                            },
                            content = {
                                Text(
                                    chips.name,
                                    color = Color.White,
                                    fontWeight = FontWeight.Bold
                                )
                            },
                            colors = ChipDefaults.chipColors(
                                backgroundColor = if (selected) Color(
                                    0xFF144C6C
                                ) else Color(0xFF202020)
                            )

                        )
                    }

                }
            }



        }

        ///////// Bottom Nav /////////////
        Card(
            modifier = Modifier.fillMaxWidth().height(60.dp)
                .padding(horizontal = 12.dp, vertical = 9.dp)
                .align(Alignment.BottomCenter),
            shape = RoundedCornerShape(30.dp),
            backgroundColor = Color.White
        ) {
            var exploreSelected by remember { mutableStateOf(true) }
            var searchSelected by remember { mutableStateOf(false) }
            Row(
                modifier = Modifier.fillMaxSize().padding(horizontal = 12.dp),
                horizontalArrangement = Arrangement.spacedBy(18.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                val width = if (exploreSelected) 100.dp else 40.dp
                Card(
                    modifier = if(exploreSelected) Modifier.height(35.dp).width(width) else Modifier.size(35.dp),
                    shape = RoundedCornerShape(50),
                    backgroundColor = Color.Black,
                    onClick = {exploreSelected = true
                        searchSelected = false
                    }
                )
                {
                    Row(modifier = Modifier.fillMaxSize()) {
                        Icon(
                            Icons.Default.SendTimeExtension,
                            null,
                            tint = Color.White,
                            modifier = Modifier.size(30.dp).align(Alignment.CenterVertically)
                        )
                        if (exploreSelected) {
                            Text(
                                "Explore",
                                color = Color.White,
                                modifier = Modifier.align(Alignment.CenterVertically)
                            )
                        }
                    }


                }

                Card(
                    modifier = if(searchSelected) Modifier.height(40.dp).width(width) else Modifier.size(35.dp),
                    shape = RoundedCornerShape(50),
                    backgroundColor = Color.Black,
                    onClick = {exploreSelected = false
                        searchSelected = true
                    }
                )
                {
                    Row(modifier = Modifier.fillMaxSize()) {
                        Icon(
                            Icons.Default.Search,
                            null,
                            tint = Color.White,
                            modifier = Modifier.align(Alignment.CenterVertically)
                        )
                        if (searchSelected) {
                            Text(
                                "Search",
                                color = Color.White,
                                modifier = Modifier.align(Alignment.CenterVertically)
                            )
                        }
                    }

                }

                Card(
                    modifier = Modifier.size(35.dp),
                    shape = RoundedCornerShape(50),
                    backgroundColor = Color.Black
                )
                {
                    Box(modifier = Modifier.fillMaxSize()) {
                        Icon(
                            Icons.Default.ArrowDownward,
                            null,
                            tint = Color.White,
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }

                }

                Card(
                    modifier = Modifier.size(35.dp),
                    shape = RoundedCornerShape(50),
                    backgroundColor = Color.Black
                )
                {
                    Box(modifier = Modifier.fillMaxSize()) {
                        Icon(
                            Icons.Default.Settings,
                            null,
                            tint = Color.White,
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }

                }

            }
        }

        ///////// Bottom Nav End /////////////
    }

    /*    Box(modifier = modifier.fillMaxSize()) {
            Column(modifier = Modifier.fillMaxSize()) {
                Box(modifier = Modifier.fillMaxWidth()) {
                    Image(
                        painterResource(Res.drawable.logo),
                        null,
                        modifier.padding(12.dp).align(Alignment.Center)
                    )
                }

                Card(
                    modifier = Modifier.fillMaxWidth().height(60.dp).padding(horizontal = 12.dp, vertical = 9.dp),
                    shape = RoundedCornerShape(30.dp),
                    backgroundColor = Color(0xFF000000)
                ) {
                    LazyRow(modifier = Modifier.fillMaxWidth().padding(9.dp)) {
                        items(listOfChips) {chips->
                            Chip(onClick = {

                            },
                                content = { Text(chips.name) },
                                colors = ChipDefaults.chipColors(backgroundColor = Color(0xFF144C6C))

                            )
                        }

                    }

                }
            }
        }*/

}

@OptIn(ExperimentalResourceApi::class)
@Composable
fun RightScreenUI(modifier: Modifier) {
    Card(modifier.fillMaxSize().padding(12.dp), shape = RoundedCornerShape(20.dp)) {
        Image(
            painterResource(
                Res.drawable.image
            ), null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop

        )
    }
}

data class ChipsNames(val name: String)

class ShowLargeImageScreenNav(val data: ShowScreenDataClass) : Screen {

    @Composable
    override fun Content() {
        LargeShowImageUI(data)
    }
}
