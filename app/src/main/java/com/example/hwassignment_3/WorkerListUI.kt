package com.example.hwassignment_3

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun WorkerListUI(
    onWorkerSelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val viewModel: WorkerListViewModel = viewModel { WorkerListViewModel(MyApplication.x) }
    val workers = viewModel.workers

    Column(modifier = modifier.fillMaxSize().padding(16.dp)) {
        WorkerListSection(workers, onWorkerSelected)
    }
}

@Composable
private fun WorkerListSection(
    workers: List<Worker>,
    onWorkerSelected: (String) -> Unit
) {
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

            WorkerList(workers, onWorkerSelected)
        }
    }
}

@Composable
private fun WorkerList(
    workers: List<Worker>,
    onWorkerSelected: (String) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(workers) { worker ->
            WorkerItem(worker, onWorkerSelected)
        }
    }
}

@Composable
private fun WorkerItem(
    worker: Worker,
    onWorkerSelected: (String) -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 1.dp),
        shape = RoundedCornerShape(8.dp),
        color = Color.LightGray
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(6.dp)
                .clickable { onWorkerSelected(worker.name) }
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