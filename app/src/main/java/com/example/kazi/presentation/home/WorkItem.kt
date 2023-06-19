package com.example.kazi.presentation.home

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.kazi.data.local.Work
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun WorkItem(
    work: Work,
    deleteWork: (id: Int) -> Unit,
    onCardClick: (id: Int) -> Unit
) {
    val dismissState = rememberDismissState()
    if (dismissState.isDismissed(DismissDirection.EndToStart)){
        deleteWork(work.id)
    }
    SwipeToDismiss(
        state =dismissState,
        background ={
            val color by animateColorAsState(
                when(dismissState.targetValue){
                    DismissValue.Default-> Color.White
                    else -> Color.Red
                }
            )
            val alignment = Alignment.CenterEnd
            val icon = Icons.Default.Delete

            val scale by animateFloatAsState(
                if (dismissState.targetValue== DismissValue.Default) 0.75f else 1f
            )

            Box(
                Modifier
                    .fillMaxSize()
                    .background(color)
                    .padding(horizontal = 20.dp),
                contentAlignment = alignment
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = "Delete icon",
                    modifier = Modifier.scale(scale)
                )
            }
        },
    ){
        Card(
            elevation = 4.dp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .clickable { onCardClick(work.id) }
        ) {
            Row(modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column() {
                    Text(text = work.title,style = MaterialTheme.typography.h2)
                    Text(text = work.description.trim(),style = MaterialTheme.typography.body1)
                }
                work.date?.let { Text(
                    text = it.format(DateTimeFormatter.ofPattern("yyyy/mm/dd")),
                    style = MaterialTheme.typography.body1
                ) }
            }
        }
    }

}