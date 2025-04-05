package com.example.hwassignment_3

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun DetailsUI(workerName: String, onBackPressed: () -> Unit, modifier: Modifier = Modifier) {

    val viewModel = viewModel<DetailListViewModel>(key = workerName) {
        DetailListViewModel(MyApplication.x, workerName)
    }

    val name = viewModel.name.value
    val hoursWorked = viewModel.hoursWorked.value
    val rate = viewModel.rate.value
    val pay = viewModel.pay.value

    Column(modifier = modifier.fillMaxSize().padding(16.dp)) {
        Surface(
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(10.dp),
            shadowElevation = 30.dp
        ) {
            Button(
                onClick = onBackPressed,
                modifier = Modifier.padding(10.dp)
            ) {
                Text("Back to Worker List")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        WorkerDetailSection(name, hoursWorked, rate, pay)
    }
}

@Composable
private fun WorkerDetailSection(name: String, hoursWorked: String, rate: Double, pay: Double) {
    Surface(
        modifier = Modifier
            .padding(20.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(10.dp),
        shadowElevation = 30.dp
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Worker Details",
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                softWrap = false,
                style = MaterialTheme.typography.headlineMedium
            )

            Spacer(modifier = Modifier.height(5.dp))

            Text(
                text = "Name: $name",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Magenta
            )

            Spacer(modifier = Modifier.height(5.dp))

            Text(
                text = "Hours Worked: $hoursWorked",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Magenta
            )

            Spacer(modifier = Modifier.height(5.dp))

            Text(
                text = "Rate: $${String.format("%.2f", rate)}",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Magenta
            )

            Spacer(modifier = Modifier.height(5.dp))

            Text(
                text = "Total Pay: $${String.format("%.2f", pay)}",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Magenta
            )
        }
    }
}