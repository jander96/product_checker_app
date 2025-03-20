package com.devj.todoproducts.core.presenter.composables

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxState
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.devj.todoproducts.core.domain.model.Product
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun SwipeDelete(
    modifier: Modifier = Modifier,
    product: Product,
    onDelete: (Product) -> Unit,
    animationDuration: Int = 500,
    content: @Composable (Product) -> Unit
) {
    val scope = rememberCoroutineScope()
    var isRemoved by remember {
        mutableStateOf(false)
    }
    val state = rememberSwipeToDismissBoxState(
        confirmValueChange = { value ->
            if (value == SwipeToDismissBoxValue.EndToStart) {
                isRemoved = true
            }
            false
        }
    )

    LaunchedEffect(key1 = isRemoved) {
        if (isRemoved) {
            delay(animationDuration.toLong())
            onDelete(product)
        }
    }
    AnimatedVisibility(
        modifier = modifier,
        visible = true,
        exit = shrinkVertically(
            animationSpec = tween(animationDuration),
            shrinkTowards = Alignment.Top
        ) + fadeOut()
    ) {
        SwipeToDismissBox(
            state = state,
            backgroundContent = {
                DeleteBackground(
                    swipeDismissState = state,
                    onDelete = {
                        isRemoved = true
                        scope.launch {
                            state.dismiss(SwipeToDismissBoxValue.EndToStart)
                        }
                    }
                )
            },
            enableDismissFromStartToEnd = true,
        ) {
            content(product)
        }
    }
}

@Composable
private fun DeleteBackground(
    modifier: Modifier = Modifier,
    swipeDismissState: SwipeToDismissBoxState,
    onDelete: () -> Unit
) {

    val color = if (swipeDismissState.dismissDirection == SwipeToDismissBoxValue.EndToStart) {
        Color.Red
    } else {
        Color.Transparent
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color)
            .padding(16.dp),
        contentAlignment = Alignment.CenterEnd
    ) {
        Icon(
            modifier = Modifier.clickable { onDelete() },
            imageVector = Icons.Default.Delete,
            contentDescription = null,
            tint = Color.White
        )
    }

}