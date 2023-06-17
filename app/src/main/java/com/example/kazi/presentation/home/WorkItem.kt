package com.example.kazi.presentation.home

import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.kazi.data.local.Work
import java.time.format.DateTimeFormatter

@Composable
fun WorkItem(work: Work) {
    Card(
        elevation = 4.dp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Row(modifier = Modifier.padding(8.dp)
            .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column() {
                Text(text = work.title)
                Text(text = work.description)
            }
            work.date?.let { Text(text = it.format(DateTimeFormatter.ofPattern("dd/mm/yyyy"))) }
        }
    }
}