package com.devj.todoproducts.features.reviewed.presenter

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.devj.todoproducts.core.domain.model.Product
import com.devj.todoproducts.core.presenter.ProductEvent
import com.devj.todoproducts.core.presenter.composables.ProductDialog
import com.devj.todoproducts.core.presenter.composables.ProductsGrid
import com.devj.todoproducts.core.presenter.composables.showToast
import com.devj.todoproducts.features.reviewed.presenter.viewmodel.ReviewedViewModel
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReviewedScreen(
    modifier: Modifier = Modifier,
    viewModel: ReviewedViewModel = koinViewModel()
) {
    val context = LocalContext.current
    val viewState by viewModel.viewState.collectAsStateWithLifecycle()
    val event by viewModel.events.collectAsStateWithLifecycle(null)
    val gridState = rememberLazyGridState()
    var showDialog by rememberSaveable { mutableStateOf(false) }
    var selectedProduct by remember {
        mutableStateOf<Product?>(null)
    }

    if (showDialog && selectedProduct != null) {
        ProductDialog(
            modifier = Modifier,
            onDismiss = { showDialog = false },
            product = selectedProduct!!
        )
    }

    LaunchedEffect(event) {
        when (event) {
            is ProductEvent.ProductDeleted -> showToast(context, "Product Deleted")
            is ProductEvent.ProductUpdated -> showToast(context, "Product Updated")
            null -> {}
        }
    }

    Column(
        modifier = modifier.background(MaterialTheme.colorScheme.background),

        )
    {

        val context = LocalContext.current

        TopAppBar(
            modifier = Modifier.padding(top = 56.dp),
            title = { Text("Reviewed Products", style = MaterialTheme.typography.headlineMedium) },
            actions = {
                Icon(
                    Icons.Outlined.Info, contentDescription = "info",
                    modifier = Modifier
                        .padding(end = 8.dp)
                        .clickable {
                            Toast.makeText(context, "Developed by jander96", Toast.LENGTH_SHORT)
                                .show()
                        }
                )
            })

        ProductsGrid(
            gridKey = "reviewed",
            enableEdition = false,
            gridState = gridState,
            modifier = modifier.padding(horizontal = 8.dp),
            state = viewState,
            onLoad = {
                viewModel.getProducts()
            },
            onRefresh = {
                viewModel.refresh()
            },
            onItemClick = {
                selectedProduct = it
                showDialog = true
            },
            onDelete = {
                viewModel.deleteProduct(it)
            },

            )

    }


}