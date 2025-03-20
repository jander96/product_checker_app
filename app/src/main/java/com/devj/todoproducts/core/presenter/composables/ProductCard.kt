package com.devj.todoproducts.core.presenter.composables

import com.devj.todoproducts.core.domain.model.Product

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.devj.todoproducts.core.presenter.toSimpleDate
import java.time.format.DateTimeFormatter

@Composable
fun ProductCard(
    modifier: Modifier = Modifier,
    product: Product,
    onCheckedChange: (Boolean) -> Unit,
    onItemClick: (Product) -> Unit = {},
    lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current,
    isUnderModification: Boolean = false,
    enabled: Boolean = true,
) {
    var isApproved by remember { mutableStateOf(product.approved) }
    val backgroundColor = if (!product.checked) {
        MaterialTheme.colorScheme.surface
    } else if (isApproved) {
        MaterialTheme.colorScheme.primaryContainer
    } else {
        Color.Red.copy(alpha = 0.2f)
    }
    val checkBoxColor = if (isApproved) {
        CheckboxDefaults.colors(
            checkedColor = MaterialTheme.colorScheme.primary,
            uncheckedColor = MaterialTheme.colorScheme.outline,
            checkmarkColor = MaterialTheme.colorScheme.onPrimary
        )
    } else {
        CheckboxDefaults.colors(
            checkedColor = MaterialTheme.colorScheme.primary,
            uncheckedColor = MaterialTheme.colorScheme.outline,
            checkmarkColor = MaterialTheme.colorScheme.onPrimary
        )
    }

    DisposableEffect(lifecycleOwner) {
        onDispose {
            isApproved = product.approved
        }
    }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {
                onItemClick(product)
            },
        colors = CardDefaults.cardColors(
            containerColor = backgroundColor
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = product.name,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    textDecoration = if (!product.checked) TextDecoration.None
                    else if (!isApproved) TextDecoration.LineThrough
                    else TextDecoration.None,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.titleMedium,
                    color = if (isApproved) MaterialTheme.colorScheme.onPrimaryContainer else MaterialTheme.colorScheme.onSurfaceVariant,
                )
                Text(
                    text = product.createdDate.toSimpleDate(),
                    style = MaterialTheme.typography.bodySmall,
                    color = if (isApproved) MaterialTheme.colorScheme.onPrimaryContainer else MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            if (isUnderModification) {
                CircularProgressIndicator(modifier = Modifier.size(28.dp))
            } else {
                Checkbox(
                    enabled = enabled,
                    checked = isApproved,
                    onCheckedChange = {
                        isApproved = it
                        onCheckedChange(it)
                    },
                    colors = checkBoxColor
                )
            }

        }
    }
}