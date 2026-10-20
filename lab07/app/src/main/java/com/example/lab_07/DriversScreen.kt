package com.example.lab_07

import android.widget.Space
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun DriversScreen(navController: NavController){ //navController allows us to navigate back to the home screen

    var driverName by remember { // remember keeps the values when the screen updates
        mutableStateOf("")
    }

    val drivers = remember {
        mutableStateListOf<String>()
    }

    Column(modifier=Modifier.fillMaxSize().padding(16.dp))
    {
        Spacer(modifier = Modifier.height(16.dp))

        //back button
        Button(
            onClick ={
                navController.popBackStack() // popbackstack() returns to the previous screen(Similar implementation as upcomingracesscreen)
            }, modifier = Modifier.testTag("driversBackButton")
        ) {
            Text("Back")
        }

        Spacer(modifier = Modifier.height(16.dp))

        //screen title
        Text(
            text = "FormulaZero Drivers",
            fontSize = 28.sp
        )
        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier= Modifier.fillMaxWidth()
        ) {
            TextField(
                value = driverName, //displays the current input
                onValueChange = { //updates drivername when user types
                    driverName=it
                },
                label={
                    Text("Driver Name")
                },
                modifier= Modifier.testTag("driverInput")
            )

            Spacer(modifier= Modifier.width(10.dp))

            Button(
                onClick = {
                    if(driverName.isNotBlank()){ // checks that the user entered something rather than empty spaces
                        drivers.add(driverName) // adds the entered name to the list
                        driverName="" //clears the text field after adding the driver
                    }
                },
                modifier = Modifier.testTag("addDriverButton") // allows the test to find the button
            ) {
                Text("Add")
            }
        }

        //clear button -- clears all the drivers from the list
        Button(
            onClick = {
                drivers.clear() //removes every driver from the list
            }, modifier = Modifier.testTag("clearDriversButton") //allows automated test to find the button
        ) {
            Text("Clear all Drivers")
        }

        Spacer(modifier = Modifier.height(16.dp))
        //displays all the added drivers
        LazyColumn {  //lazycolumn displays a scrollable list
            items(drivers){driver ->   //goes through every driver in the list
                Spacer(modifier=Modifier.height(12.dp))
                Text(
                    text=driver, // displays the name of the drier on the screen
                    fontSize = 24.sp
                )
            }
        }

    }

}