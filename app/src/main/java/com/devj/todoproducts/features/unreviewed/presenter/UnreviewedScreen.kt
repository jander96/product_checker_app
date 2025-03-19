package com.devj.todoproducts.features.unreviewed.presenter


import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.devj.todoproducts.features.unreviewed.presenter.composable.UnreviewedProductsGrid
import com.devj.todoproducts.features.unreviewed.presenter.viewmodel.UnreviewedViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun UnreviewedScreen(
    modifier: Modifier = Modifier,
    viewModel: UnreviewedViewModel = koinViewModel()
) {

    val viewState by viewModel.viewState.collectAsStateWithLifecycle()

    UnreviewedProductsGrid(
        modifier = modifier,
        state = viewState,
        onLoad = {
            viewModel.getProducts()
        }
    )
}