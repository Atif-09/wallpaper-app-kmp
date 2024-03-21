package screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.horizontalScroll
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
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import api.ApiClass
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.seiko.imageloader.rememberImagePainter
import kmpnetworktemplate.composeapp.generated.resources.Res
import kmpnetworktemplate.composeapp.generated.resources.image_1
import kmpnetworktemplate.composeapp.generated.resources.image_2
import kmpnetworktemplate.composeapp.generated.resources.pacifico_regular
import kmpnetworktemplate.composeapp.generated.resources.ubuntu_bold
import kmpnetworktemplate.composeapp.generated.resources.ubuntu_regular
import kotlinx.coroutines.launch
import model.Photo
import model.ShowScreenDataClass
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.Font
import org.jetbrains.compose.resources.painterResource
import kotlin.random.Random

@OptIn(ExperimentalResourceApi::class, ExperimentalMaterialApi::class)
@Composable
fun MainScreenUI() {

    val listOfChips = mutableListOf(
        ChipsNames("Discover"),
        ChipsNames("Nature"),
        ChipsNames("Anime"),
        ChipsNames("Architect"),
        ChipsNames("Tech")
    )
    val scope = rememberCoroutineScope()
    var urlList by remember { mutableStateOf<List<Photo>>(emptyList()) }
    var discoverSelected by remember { mutableStateOf(true) }
    var natureSelected by remember { mutableStateOf(false) }
    var animeSelected by remember { mutableStateOf(false) }
    var architectSelected by remember { mutableStateOf(false) }
    var techSelected by remember { mutableStateOf(false) }
    var showSearch by remember { mutableStateOf(false) }
    var searchText by remember { mutableStateOf("") }
    val navigator = LocalNavigator.currentOrThrow
    val keyboardController = LocalSoftwareKeyboardController.current
    val url = "https://api.pexels.com/v1/curated/?page=1&per_page=80"


    var currentPage by remember { mutableStateOf(1) }
    var nextPageUrl by remember { mutableStateOf<String?>(null) }

    var searchCurrentPage by remember { mutableStateOf(1) }
    var searchNextPageUrl by remember { mutableStateOf<String?>(null) }

    val searchUrl =
        "https://api.pexels.com/v1/search?query=$searchText&per_page=80"
    scope.launch {
        val imageData = ApiClass().greeting(url)
        urlList = imageData.photos
        nextPageUrl = imageData.next_page
        currentPage = imageData.page
    }
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
                            onValueChange = { searchText = it },
                            modifier = Modifier.fillMaxWidth(0.8f).align(Alignment.CenterVertically)
                                .padding(start = 12.dp),
                            cursorBrush = brush,
                            /*   colors = TextFieldDefaults.textFieldColors(
                                   textColor = Color.White,
                                   disabledPlaceholderColor = Color(0xFF3D3D3D),
                                   placeholderColor = Color(0xFF3D3D3D),
                                   backgroundColor = Color.Transparent,
                                   cursorColor = Color.White,
                                   focusedIndicatorColor = Color.Transparent,
                                   disabledIndicatorColor = Color.Transparent
                               ),*/
                            textStyle = TextStyle(
                                brush = brush,
                                fontSize = 18.sp,
                                fontFamily = FontFamily(Font(Res.font.ubuntu_bold))
                            ),
                            keyboardOptions = KeyboardOptions(
                                capitalization = KeyboardCapitalization.Sentences,
                                autoCorrect = true, imeAction = ImeAction.Search
                            ),
                            keyboardActions = KeyboardActions(
                                onSearch = {
                                    urlList = emptyList()
                                    scope.launch {
                                        val imageData = ApiClass().searchImage(searchUrl)
                                        urlList = imageData.photos
                                        nextPageUrl = imageData.next_page
                                        currentPage = imageData.page

                                    }
                                    keyboardController!!.hide()
                                }
                            ),
                            decorationBox = {
                                it()
                                if (searchText.isEmpty()) {
                                    Text(
                                        "Type to search something.......",
                                        color = Color(0xFF3D3D3D),
                                        fontSize = 15.sp,
                                        fontFamily = FontFamily(Font(Res.font.ubuntu_regular))
                                    )
                                }
                            }
//                            placeholder = { Text("Type to search something...") },
                        )

                        Card(
                            modifier = Modifier.fillMaxWidth(1f).padding(6.dp),
                            shape = RoundedCornerShape(50),
                            backgroundColor = Color(0xFF202020),
                            onClick = {
                                scope.launch {
                                    val imageData = ApiClass().searchImage(searchUrl)
                                    urlList = imageData.photos
                                    searchNextPageUrl = imageData.next_page
                                    searchCurrentPage = imageData.page
                                }
                            }
                        ) {
                            Box(modifier = Modifier.fillMaxSize()) {

                                Text(
                                    "Go",
                                    modifier = Modifier.align(Alignment.Center),
                                    color = Color.White,
                                    fontFamily = FontFamily(Font(Res.font.ubuntu_bold))
                                )
                            }
                        }
                    }

                } else {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(9.dp),
                        modifier = Modifier.fillMaxWidth().padding(horizontal = 3.dp)
                            .horizontalScroll(
                                rememberScrollState()
                            )
                    ) {
                        Chip(
                            modifier = Modifier,
                            onClick = {
                                discoverSelected = true
                                natureSelected = false
                                animeSelected = false
                                architectSelected = false
                                techSelected = false
                            },
                            content = {
                                Text(
                                    "Discover",
                                    color = Color.White,
                                    fontFamily = FontFamily(Font(Res.font.ubuntu_bold))
                                )
                            },
                            colors = ChipDefaults.chipColors(
                                backgroundColor = if (discoverSelected) Color(
                                    0xFF144C6C
                                ) else Color(0xFF202020)
                            )

                        )

                        Chip(
                            modifier = Modifier,
                            onClick = {
                                discoverSelected = false
                                natureSelected = true
                                animeSelected = false
                                architectSelected = false
                                techSelected = false
                            },
                            content = {
                                Text(
                                    "Nature",
                                    color = Color.White,
                                    fontFamily = FontFamily(Font(Res.font.ubuntu_bold))
                                )
                            },
                            colors = ChipDefaults.chipColors(
                                backgroundColor = if (natureSelected) Color(
                                    0xFF144C6C
                                ) else Color(0xFF202020)
                            )

                        )
                        if (natureSelected) {
                            val natureUrl =
                                "https://api.pexels.com/v1/search?query=nature&per_page=80"
                            scope.launch {
                                val imageData = ApiClass().searchImage(natureUrl)
                                urlList = imageData.photos
                                searchNextPageUrl = imageData.next_page
                                searchCurrentPage = imageData.page
                            }
                        }

                        Chip(
                            modifier = Modifier,
                            onClick = {
                                discoverSelected = false
                                natureSelected = false
                                animeSelected = true
                                architectSelected = false
                                techSelected = false
                            },
                            content = {
                                Text(
                                    "Anime",
                                    color = Color.White,
                                    fontFamily = FontFamily(Font(Res.font.ubuntu_bold))
                                )
                            },
                            colors = ChipDefaults.chipColors(
                                backgroundColor = if (animeSelected) Color(
                                    0xFF144C6C
                                ) else Color(0xFF202020)
                            )

                        )
                        if (animeSelected) {
                            val natureUrl =
                                "https://api.pexels.com/v1/search?query=anime&per_page=80"
                            scope.launch {
                                val imageData = ApiClass().searchImage(natureUrl)
                                urlList = imageData.photos
                                searchNextPageUrl = imageData.next_page
                                searchCurrentPage = imageData.page
                            }
                        }

                        Chip(
                            modifier = Modifier,
                            onClick = {
                                discoverSelected = false
                                natureSelected = false
                                animeSelected = false
                                architectSelected = true
                                techSelected = false
                            },
                            content = {
                                Text(
                                    "Architect",
                                    color = Color.White,
                                    fontFamily = FontFamily(Font(Res.font.ubuntu_bold))
                                )
                            },
                            colors = ChipDefaults.chipColors(
                                backgroundColor = if (architectSelected) Color(
                                    0xFF144C6C
                                ) else Color(0xFF202020)
                            )

                        )
                        if (architectSelected) {
                            val natureUrl =
                                "https://api.pexels.com/v1/search?query=architect&per_page=80"
                            scope.launch {
                                val imageData = ApiClass().searchImage(natureUrl)
                                urlList = imageData.photos
                                searchNextPageUrl = imageData.next_page
                                searchCurrentPage = imageData.page
                            }

                        }

                        Chip(
                            modifier = Modifier,
                            onClick = {
                                discoverSelected = false
                                natureSelected = false
                                animeSelected = false
                                architectSelected = false
                                techSelected = true
                            },
                            content = {
                                Text(
                                    "Tech",
                                    color = Color.White,
                                    fontFamily = FontFamily(Font(Res.font.ubuntu_bold))
                                )
                            },
                            colors = ChipDefaults.chipColors(
                                backgroundColor = if (techSelected) Color(
                                    0xFF144C6C
                                ) else Color(0xFF202020)
                            )

                        )
                        if (techSelected) {
                            val natureUrl =
                                "https://api.pexels.com/v1/search?query=tech&per_page=80"
                            scope.launch {
                                val imageData = ApiClass().searchImage(natureUrl)
                                urlList = imageData.photos
                                searchNextPageUrl = imageData.next_page
                                searchCurrentPage = imageData.page
                            }
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
                itemsIndexed(urlList) { index, imageResource ->
                    println("Size of the photos${urlList.size}")
                    val height = if (index == 1 || index == 78) 250.dp else 170.dp
                    Card(
                        modifier = if (index == 1 || index == 78) Modifier.width(170.dp)
                            .height(250.dp) else Modifier.size(170.dp),
                        shape = RoundedCornerShape(7),
                        border = BorderStroke(1.dp, Color(0x33FFFFFF)),
                        onClick = {
                            navigator.push(
                                ShowImageScreenNav(
                                    ShowScreenDataClass(
                                        imageResource.alt,
                                        imageResource.src.original
                                    )
                                )
                            )
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

        ///////////// Bottom Nav /////////////////
        Card(
            modifier = Modifier.height(75.dp)
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
                                    .padding(horizontal = 6.dp),
                                fontFamily = FontFamily(Font(Res.font.ubuntu_regular))

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
                                    .padding(horizontal = 6.dp),
                                fontFamily = FontFamily(Font(Res.font.ubuntu_regular))
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

class ShowImageScreenNav(val data: ShowScreenDataClass) : Screen {

    @Composable
    override fun Content() {
        ShowImageUI(data)
    }
}