package model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ImageDataClass(
    @SerialName("next_page")
    val next_page: String,
    @SerialName("page")
    val page: Int,
    @SerialName("per_page")
    val per_page: Int,
    @SerialName("photos")
    val photos: List<Photo>
)