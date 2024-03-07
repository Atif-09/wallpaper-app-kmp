package screens

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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kmpnetworktemplate.composeapp.generated.resources.Res
import kmpnetworktemplate.composeapp.generated.resources.pacifico_regular
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.Font

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
        Card(
            modifier = Modifier.fillMaxWidth().height(70.dp).padding(horizontal = 12.dp, vertical = 9.dp)
                .align(Alignment.BottomCenter),
            shape = RoundedCornerShape(30.dp),
            backgroundColor = Color.White
        ) {
            Row(
                modifier = Modifier.fillMaxSize().padding(horizontal = 12.dp),
                horizontalArrangement = Arrangement.spacedBy(18.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Card(
                    modifier = Modifier.height(40.dp).width(120.dp),
                    shape = RoundedCornerShape(50),
                    backgroundColor = Color.Black
                )
                {
                    Row(modifier = Modifier.fillMaxSize()) {
                        Icon(
                            Icons.Default.Send,
                            null,
                            tint = Color.White,
                            modifier = Modifier.align(Alignment.CenterVertically)
                        )
                        Text("Explore",color = Color.White, modifier = Modifier.align(Alignment.CenterVertically))
                    }

                }

                Card(
                    modifier = Modifier.size(40.dp),
                    shape = RoundedCornerShape(50),
                    backgroundColor = Color.Black
                )
                {
                    Box(modifier = Modifier.fillMaxSize()) {
                        Icon(
                            Icons.Default.Search,
                            null,
                            tint = Color.White,
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }

                }

                Card(
                    modifier = Modifier.size(40.dp),
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
                    modifier = Modifier.size(40.dp),
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