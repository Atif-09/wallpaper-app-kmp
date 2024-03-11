package screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Chip
import androidx.compose.material.ChipDefaults
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDownward
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kmpnetworktemplate.composeapp.generated.resources.Res
import kmpnetworktemplate.composeapp.generated.resources.image_1
import kmpnetworktemplate.composeapp.generated.resources.image_2
import kmpnetworktemplate.composeapp.generated.resources.pacifico_regular
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.Font
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalResourceApi::class, ExperimentalMaterialApi::class)
@Composable
fun MainScreenUI() {

    val listOfChips = mutableListOf<ChipsNames>(
        ChipsNames("Discover"),
        ChipsNames("Nature"),
        ChipsNames("Anime"),
        ChipsNames("Architect"),
        ChipsNames("Tech")
    )
    var selected by remember { mutableStateOf(false) }
    Box(modifier = Modifier.fillMaxSize()) {
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
                modifier = Modifier.fillMaxWidth()
                    .padding(horizontal = 12.dp, vertical = 9.dp),
                shape = RoundedCornerShape(50),
                backgroundColor = Color(0xFF000000)
            ) {
                LazyRow(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(9.dp),
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 3.dp)
                ) {
                    items(listOfChips) { chips ->
                        Chip(
                            modifier = Modifier,
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

            val imageList = mutableListOf(
                painterResource(Res.drawable.image_1),
                painterResource(Res.drawable.image_2),
                painterResource(Res.drawable.image_1),
                painterResource(Res.drawable.image_1),
                painterResource(Res.drawable.image_1),
                painterResource(Res.drawable.image_1),
                painterResource(Res.drawable.image_1),
                painterResource(Res.drawable.image_1),
                painterResource(Res.drawable.image_1),
                painterResource(Res.drawable.image_1),
                painterResource(Res.drawable.image_1),
                painterResource(Res.drawable.image_1),
            )
            LazyVerticalStaggeredGrid(columns = StaggeredGridCells.Fixed(2),modifier = Modifier.fillMaxWidth().padding(horizontal = 12.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalItemSpacing = 12.dp
            ){
                items(imageList){imageResource ->
                    Card(modifier= Modifier.size(170.dp), shape = RoundedCornerShape(7), border = BorderStroke(1.dp, Color(0xFFC4C4C4))){
                        Image(imageResource, null, modifier= Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop
                            )
                    }

                }
            }

        }

        ///////////// Bottom Nav /////////////////
        Card(
            modifier = Modifier.height(70.dp)
                .padding(horizontal = 12.dp, vertical = 9.dp)
                .align(Alignment.BottomCenter),
            shape = RoundedCornerShape(30.dp),
            backgroundColor = Color.White
        ) {
            var exploreSelected by remember { mutableStateOf(true) }
            var searchSelected by remember { mutableStateOf(false) }

            Row(
                modifier = Modifier.padding(horizontal = 12.dp),
                horizontalArrangement = Arrangement.spacedBy(18.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {


                Card(
                    modifier = if (exploreSelected) Modifier.height(35.dp)
                        .width(130.dp) else Modifier.size(35.dp),
                    shape = RoundedCornerShape(50),
                    backgroundColor = Color.Black,
                    onClick = { exploreSelected = true
                        searchSelected = false
                    }
                )

                {
                    if (exploreSelected) {

                        Row(modifier = Modifier.fillMaxSize()) {
                            Icon(
                                Icons.Default.Send,
                                null,
                                tint = Color.White,
                                modifier = Modifier.align(Alignment.CenterVertically)
                                    .padding(start = 12.dp)
                            )

                            Text(
                                "EXPLORE",
                                color = Color.White,
                                modifier = Modifier.align(Alignment.CenterVertically)
                                    .padding(horizontal = 6.dp)
                            )

                        }

                    } else {

                        Box(modifier = Modifier.fillMaxSize()) {
                            Icon(
                                Icons.Default.Send,
                                null,
                                tint = Color.White,
                                modifier = Modifier.align(Alignment.Center)
                            )
                        }


                    }
                }


                Card(
                    modifier = if (searchSelected) Modifier.height(35.dp)
                        .width(130.dp) else Modifier.size(35.dp),
                    shape = RoundedCornerShape(50),
                    backgroundColor = Color.Black,
                    onClick = {searchSelected = true
                        exploreSelected= false
                    }
                )
                {
                    if (searchSelected) {

                        Row(modifier = Modifier.fillMaxSize()) {
                            Icon(
                                Icons.Default.Search,
                                null,
                                tint = Color.White,
                                modifier = Modifier.align(Alignment.CenterVertically)
                                    .padding(start = 12.dp)
                            )

                            Text(
                                "SEARCH",
                                color = Color.White,
                                modifier = Modifier.align(Alignment.CenterVertically)
                                    .padding(horizontal = 6.dp)
                            )

                        }

                    } else {

                        Box(modifier = Modifier.fillMaxSize()) {
                            Icon(
                                Icons.Default.Search,
                                null,
                                tint = Color.White,
                                modifier = Modifier.align(Alignment.Center)
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
    }
}