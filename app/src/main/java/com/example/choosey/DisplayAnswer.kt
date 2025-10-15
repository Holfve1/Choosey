
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun DisplayAnswer(
    text: String,
    isFallback: Boolean = false,
    fontSize: Int = 50
) {
    val textMeasurer = rememberTextMeasurer()
    val configuration = LocalConfiguration.current
    val screenWidthDp = configuration.screenWidthDp
    val density = LocalDensity.current

    val baseFontSize = fontSize.sp

    val screenWidthPx = with(density) { screenWidthDp.dp.toPx() }

    val measuredText = textMeasurer.measure(
        text = text,
        style = TextStyle(fontSize = baseFontSize)
    )

    val adjustedFontSize = when {
        measuredText.size.width > screenWidthPx -> (fontSize * 0.7).sp
        else -> baseFontSize
    }
    Text(
        text = text,
        fontSize = adjustedFontSize,
        lineHeight = (adjustedFontSize.value + 5).sp,
        fontWeight = FontWeight.Bold,
        color = Color.White,
        textAlign = TextAlign.Center,
        modifier = Modifier.fillMaxWidth(),
        maxLines = 3,
        overflow = TextOverflow.Ellipsis,
        softWrap = true
    )
}


@Preview
@Composable
fun DisplayAnswerPreview() {
    DisplayAnswer(text = "test")
}