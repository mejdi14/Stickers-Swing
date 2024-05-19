import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

import stickersswing.composeapp.generated.resources.Res
import stickersswing.composeapp.generated.resources.compose_multiplatform
import stickersswing.composeapp.generated.resources.heart
import stickersswing.composeapp.generated.resources.lightning
import stickersswing.composeapp.generated.resources.lolippop
import stickersswing.composeapp.generated.resources.playboy
import stickersswing.composeapp.generated.resources.watch_this
import kotlin.math.roundToInt

@OptIn(ExperimentalResourceApi::class)
@Composable
@Preview
fun App() {
    MaterialTheme {
        var offsetX by remember { mutableStateOf(0f) }
        var offsetY by remember { mutableStateOf(0f) }
        var isDragging by remember { mutableStateOf(false) }
        var rotationAngle by remember { mutableStateOf(0f) }
        var startOffset by remember { mutableStateOf(Offset.Zero) }
        Row(
            Modifier.fillMaxSize().padding(bottom = 100.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.Bottom
        ) {

            Image(
                painter = painterResource(Res.drawable.watch_this),
                contentDescription = null,
                modifier = Modifier.size(70.dp).rotate(rotationAngle)
                    .offset { IntOffset(offsetX.roundToInt(), offsetY.roundToInt()) }
                    .pointerInput(Unit) {
                        detectDragGestures(
                            onDragStart = {
                                isDragging = true
                            },
                            onDragEnd = {
                                isDragging = false
                                offsetX = 0f
                                offsetY = 0f
                                rotationAngle = 0f
                            },
                            onDragCancel = {
                                isDragging = false
                                offsetX = 0f
                                offsetY = 0f
                                rotationAngle = 0f
                            }
                        ) { change, dragAmount ->
                            if (isDragging) {
                                change.consume()
                                offsetX += dragAmount.x
                                offsetY += dragAmount.y
                                rotationAngle = offsetX /50
                                // Adjust the rotation based on the horizontal drag amount
                            }
                        }
                    }
            )
            Image(painterResource(Res.drawable.lightning), null, modifier = Modifier.size(70.dp))
            Image(painterResource(Res.drawable.lolippop), null, modifier = Modifier.size(70.dp))
            Image(painterResource(Res.drawable.playboy), null, modifier = Modifier.size(70.dp))
            Image(painterResource(Res.drawable.watch_this), null, modifier = Modifier.size(70.dp))
        }
    }
}