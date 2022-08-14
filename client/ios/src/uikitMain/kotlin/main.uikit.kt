import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Application
import kotlinx.cinterop.*
import platform.Foundation.NSStringFromClass
import platform.UIKit.*
import ru.starfactory.pixel.ui.view.ArcProgressBar
import kotlin.math.min

fun main() {
    val args = emptyArray<String>()
    memScoped {
        val argc = args.size + 1
        val argv = (arrayOf("skikoApp") + args).map { it.cstr.ptr }.toCValues()
        autoreleasepool {
            UIApplicationMain(argc, argv, null, NSStringFromClass(SkikoAppDelegate))
        }
    }
}

class SkikoAppDelegate : UIResponder, UIApplicationDelegateProtocol {
    companion object : UIResponderMeta(), UIApplicationDelegateProtocolMeta

    @ObjCObjectBase.OverrideInit
    constructor() : super()

    private var _window: UIWindow? = null
    override fun window() = _window
    override fun setWindow(window: UIWindow?) {
        _window = window
    }

    override fun application(application: UIApplication, didFinishLaunchingWithOptions: Map<Any?, *>?): Boolean {
        window = UIWindow(frame = UIScreen.mainScreen.bounds)
        window!!.rootViewController = Application("TeslaApp") {
            Column {
                // To skip upper part of screen.
                Box(modifier = Modifier.height(30.dp))
                DashboardScreen()
            }
        }
        window!!.makeKeyAndVisible()
        return true
    }
}


// TODO move to shared
@Composable
fun DashboardScreen() {
    DashboardContent()
}

@Composable
private fun DashboardContent() {
    Column(Modifier.fillMaxSize()) {
        Speed()
        Battery()
    }
}


@Composable
private fun ColumnScope.Battery() {
    val transition = rememberInfiniteTransition()
    val progress by transition.animateFloat(
        initialValue = 0f,
        targetValue = 100f,
        animationSpec = infiniteRepeatable(
            animation = tween(5000),
            repeatMode = RepeatMode.Reverse
        )
    )


    ArcValueIndicatorWidget(
        Modifier
            .weight(1f)
            .align(Alignment.CenterHorizontally)
            .padding(16.dp),
        currentValue = progress.toInt(),
        maxValue = 100,
        text = "%"
    )
}

@Composable
private fun ColumnScope.Speed() {
    val transition = rememberInfiniteTransition()
    val progress by transition.animateFloat(
        initialValue = 0f,
        targetValue = 40f,
        animationSpec = infiniteRepeatable(
            animation = tween(5000),
            repeatMode = RepeatMode.Reverse
        )
    )


    ArcValueIndicatorWidget(
        Modifier
            .weight(1f)
            .align(Alignment.CenterHorizontally)
            .padding(16.dp),
        currentValue = progress.toInt(),
        maxValue = 60,
        text = "km/h"
    )
}

@OptIn(ExperimentalTextApi::class)
@Composable
fun ArcValueIndicatorWidget(
    modifier: Modifier = Modifier.fillMaxSize(),
    currentValue: Int,
    maxValue: Int,
    text: String,
) {
    val progress by animateFloatAsState(
        targetValue = min(currentValue, maxValue) / maxValue.toFloat(),
        animationSpec = tween(300)
    )

    ArcProgressBar(
        modifier,
        progress = progress,
        strokeWidth = 18.dp,
        frameWidth = 24.dp,
    ) {
        Column(Modifier.fillMaxSize()) {
            AutoSizeText(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .weight(1f), // TODO нужно правильное измерение
                text = currentValue.toString(),
                textStyle = LocalTextStyle.current.copy(
                    fontSize = 100.sp,
                    fontWeight = FontWeight.Bold,
                    //platformStyle = PlatformTextStyle(false),
                ),
            )
            Text(
                text = text,
                Modifier
                    .align(Alignment.CenterHorizontally),
                style = MaterialTheme.typography.h6
            )
        }
    }
}

@Composable
fun AutoSizeText(
    modifier: Modifier = Modifier,
    text: String,
    textStyle: TextStyle = LocalTextStyle.current,
) {
    var scaledTextStyle by remember { mutableStateOf(textStyle) }
    var readyToDraw by remember { mutableStateOf(false) }

    Text(
        text,
        modifier.drawWithContent {
            if (readyToDraw) {
                drawContent()
            }
        },
        style = scaledTextStyle,
        softWrap = false,
        onTextLayout = { textLayoutResult ->
            if (textLayoutResult.didOverflowWidth || textLayoutResult.didOverflowHeight) {
                scaledTextStyle =
                    scaledTextStyle.copy(fontSize = scaledTextStyle.fontSize * 0.9)
            } else {
                readyToDraw = true
            }
        }
    )
}
