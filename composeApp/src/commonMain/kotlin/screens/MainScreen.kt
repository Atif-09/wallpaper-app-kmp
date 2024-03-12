package screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.lazy.staggeredgrid.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Card
import androidx.compose.material.Chip
import androidx.compose.material.ChipDefaults
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDownward
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import api.ApiClass
import com.seiko.imageloader.rememberImagePainter
import kmpnetworktemplate.composeapp.generated.resources.Res
import kmpnetworktemplate.composeapp.generated.resources.image_1
import kmpnetworktemplate.composeapp.generated.resources.image_2
import kmpnetworktemplate.composeapp.generated.resources.pacifico_regular
import kotlinx.coroutines.launch
import model.Photo
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
    var showSearch by remember { mutableStateOf(false) }
    var searchText by remember { mutableStateOf("") }
    /*    var urlList by remember { mutableStateOf<List<Photo>>(emptyList()) }
        val scope = rememberCoroutineScope()

        scope.launch {
            urlList = ApiClass().greeting().photos
        }*/
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
                modifier = Modifier.fillMaxWidth().fillMaxHeight(0.08f)
                    .padding(horizontal = 12.dp, vertical = 9.dp),
                shape = RoundedCornerShape(50),
                backgroundColor = Color(0xFF000000)
            ) {
                val brush = remember {
                    Brush.linearGradient(
                        colors = listOf(
                            Color(0xFFff0000),
                            Color(0xFFffa500),
                            Color(0xFFffff00),
                            Color(0xFF008000),
                            Color(0xFF0000ff),
                            Color(0xFF4b0082),
                            Color(0xFFee82ee),
                        )
                    )
                }
                if (showSearch) {
                    Row(modifier = Modifier.fillMaxWidth()) {
                        BasicTextField(
                            value = searchText,
                            onValueChange = {searchText = it},
                            modifier = Modifier.fillMaxWidth(0.8f).align(Alignment.CenterVertically).padding(start = 12.dp),
                         /*   colors = TextFieldDefaults.textFieldColors(
                                textColor = Color.White,
                                disabledPlaceholderColor = Color(0xFF3D3D3D),
                                placeholderColor = Color(0xFF3D3D3D),
                                backgroundColor = Color.Transparent,
                                cursorColor = Color.White,
                                focusedIndicatorColor = Color.Transparent,
                                disabledIndicatorColor = Color.Transparent
                            ),*/
                            textStyle = TextStyle(brush = brush, fontSize = 18.sp),
//                            placeholder = { Text("Type to search something...") },
                            )
                        Card(
                            modifier = Modifier.fillMaxWidth(1f).padding(6.dp),
                            shape = RoundedCornerShape(50),
                            backgroundColor = Color(0xFF202020)
                        ) {
                            Box(modifier = Modifier.fillMaxSize()) {

                                Text(
                                    "Go",
                                    modifier = Modifier.align(Alignment.Center),
                                    color = Color.White
                                )
                            }
                        }
                    }

                } else {
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
            LazyVerticalStaggeredGrid(
                columns = StaggeredGridCells.Fixed(2),
                modifier = Modifier.fillMaxWidth().padding(horizontal = 12.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalItemSpacing = 12.dp
            ) {
                itemsIndexed(imageList) { index, imageResource ->

                    val height = if (index == 1) 250.dp else 170.dp
                    Card(
                        modifier = if (index == 1) Modifier.width(170.dp)
                            .height(250.dp) else Modifier.size(170.dp),
                        shape = RoundedCornerShape(7),
                        border = BorderStroke(1.dp, Color(0xFFC4C4C4))
                    ) {
                        Image(
                            imageResource, null, modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )
                        /*
                                                Image(
                                                    rememberImagePainter(imageResource.src.original),
                                                    null,
                                                    modifier = Modifier.fillMaxSize(),
                                                    contentScale = ContentScale.Crop
                                                )*/
                    }

                }
            }

        }

        ///////////// Bottom Nav /////////////////
        Card(
            modifier = Modifier.height(80.dp)
                .padding(horizontal = 12.dp, vertical = 9.dp)
                .align(Alignment.BottomCenter),
            shape = RoundedCornerShape(30.dp),
            backgroundColor = Color(0xD9FFFFFF)
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
                    onClick = {
                        exploreSelected = true
                        searchSelected = false
                        showSearch = false
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
                    onClick = {
                        searchSelected = true
                        exploreSelected = false
                        showSearch = true
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