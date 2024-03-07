package screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Chip
import androidx.compose.material.ChipDefaults
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kmpnetworktemplate.composeapp.generated.resources.Res
import kmpnetworktemplate.composeapp.generated.resources.logo
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource


@Composable
fun LargeMainScreenUI() {
    Row(modifier = Modifier.fillMaxSize()) {
        LeftScreenUI(modifier = Modifier.fillMaxWidth(0.4f))
        Divider(
            color = Color.White,
            thickness = 2.dp,
            modifier = Modifier.width(2.dp).fillMaxHeight().padding(horizontal = 3.dp)
        )
        RightScreenUI(modifier = Modifier.fillMaxWidth(1f))
    }
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

    Box(modifier = modifier.fillMaxSize()) {
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
    }

}

@Composable
fun RightScreenUI(modifier: Modifier) {
}

data class ChipsNames(val name: String)
