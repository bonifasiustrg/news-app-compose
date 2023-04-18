package com.bonifasiustrg.taskreminder

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bonifasiustrg.taskreminder.actionbutton.ActionButton
import com.bonifasiustrg.taskreminder.actionbutton.Design
import com.bonifasiustrg.taskreminder.ui.theme.TaskReminderTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TaskReminderTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        ActionButton(
                            text = "Primary",
                            modifier = Modifier.fillMaxWidth(),
                            design = Design.Primary,
                            onClick = {
                                Toast.makeText(
                                    applicationContext,
                                    "Hello World",
                                    Toast.LENGTH_SHORT
                                ).show()
                            },
                            icon = {
                                Icon(imageVector = Icons.Default.Check,
                                    contentDescription = null
                                )
                            }
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        ActionButton(
                            text = "Secondary",
                            modifier = Modifier.fillMaxWidth(),
                            design = Design.Secondary,
                            onClick = {
                                Toast.makeText(
                                    applicationContext,
                                    "Hello World",
                                    Toast.LENGTH_SHORT
                                ).show()
                            },
                            icon = {
                                Icon(imageVector = Icons.Default.Close,
                                    contentDescription = null
                                )
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TaskReminderTheme {
        //Greeting("Android")


    }
}