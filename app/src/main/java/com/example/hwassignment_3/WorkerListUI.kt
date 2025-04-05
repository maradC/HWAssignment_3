package com.example.hwassignment_3

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
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
fun WorkerListUI(modifier: Modifier = Modifier) {
    val viewModel: WorkerListViewModel = viewModel { WorkerListViewModel(MyApplication.x) }
    val workers = viewModel.workers

    Column(modifier = modifier.fillMaxSize().padding(16.dp)) {
        Spacer(modifier = Modifier.height(16.dp))
        WorkerListSection(workers)
    }
}

@Composable
private fun WorkerListSection(workers: List<Worker>) {
    Surface(
        modifier = Modifier
            .padding(20.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(10.dp),
        shadowElevation = 30.dp
    ) {
        Column(modifier = Modifier.padding(16.dp).fillMaxSize()) {
            Heading("Worker List")

            Spacer(modifier = Modifier.height(16.dp))

            WorkerList(workers)
        }
    }
}

@Composable
private fun WorkerList(workers: List<Worker>) {
    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(workers) { worker ->
            WorkerItem(worker)
        }
    }
}

@Composable
private fun WorkerItem(worker: Worker) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {  }
            .padding(vertical = 1.dp),
        shape = RoundedCornerShape(8.dp),
        color = Color.LightGray,
        onClick = { /* You can add your click action here */ }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(6.dp)
        ) {
            Text(
                text = worker.name,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
            Text(
                text = "Hours Worked: ${worker.hoursWorked}",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Magenta
            )
            Text(
                text = "Rate: $${String.format("%.2f", worker.rate)}",
                color = Color.Magenta,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}
@Composable
private fun Heading(heading: String) {
    Text(
        text = heading,
        color = MaterialTheme.colorScheme.primary,
        fontWeight = FontWeight.Bold,
        maxLines = 1,
        softWrap = false,
        style = MaterialTheme.typography.headlineMedium
    )
}