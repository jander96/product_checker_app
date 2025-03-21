package com.devj.todoproducts.core.presenter.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.devj.todoproducts.core.domain.model.Product

@Composable
fun ProductDialog(modifier: Modifier = Modifier, product: Product, onDismiss: () -> Unit) {
    AlertDialog(
        modifier = modifier,
        onDismissRequest = { onDismiss() },
        title = { Text(text = "Product Details") },
        text = {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = "ID: ${product.id}")
                Text(text = "Name: ${product.name}")
                Text(text = "Created Date: ${product.createdDate}")
                Text(text = "Updated Date: ${product.updatedDate}")
                Text(text = "Approved: ${product.approved}")
                Text(text = "Checked: ${product.checked}")
            }
        },
        confirmButton = {
            Button(onClick = { onDismiss() }) {
                Text("Close")
            }
        }
    )
}