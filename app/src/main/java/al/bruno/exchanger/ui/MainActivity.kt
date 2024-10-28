package al.bruno.exchanger.ui

import al.bruno.exchanger.ui.foundation.theme.ExchangerTheme
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Surface

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ExchangerTheme {
                Surface {
                    MainScreen()
                }
            }
        }
    }
}