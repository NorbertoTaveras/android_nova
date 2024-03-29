package com.norbertotaveras.android_nova.presentation.screens.home.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ContentAlpha
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForwardIos
import androidx.compose.material.icons.automirrored.rounded.ArrowForwardIos
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.norbertotaveras.android_nova.presentation.theme.AppColors
import com.norbertotaveras.android_nova.utils.dimens.Dimens.DividerAlpha

@Composable
fun CategoryList(
    categories: List<String>,
    onCategorySelected: (String) -> Unit
) {
    LazyColumn {
        items(categories) { category ->
            CategoryItem(
                categoryName = category,
                onCategorySelected = onCategorySelected
            )
        }
    }
}

@Composable
fun CategoryListWithHeader(
    header: String,
    categories: List<String>,
    onCategorySelected: (String) -> Unit
) {
    Column {
        Text(
            text = header,
            style = MaterialTheme.typography.displayMedium,
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 8.dp)
        )
        categories.forEach { category ->
            CategoryCardItem(categoryName = category, onCategorySelected = onCategorySelected)
        }
    }
}

@Composable
fun CategoryListItem(
    categoryName: String,
    onCategorySelected: (String) -> Unit
) {
    Row(
        modifier = Modifier
            .background(color = AppColors.parent)
            .fillMaxWidth()
            .clickable { onCategorySelected(categoryName) }
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = categoryName,
            style = MaterialTheme.typography.bodyMedium.copy(
                color = AppColors.App_Black,
                fontWeight = FontWeight.SemiBold
            )
        )
        Icon(
            modifier = Modifier.size(16.dp),
            imageVector = Icons.AutoMirrored.Filled.ArrowForwardIos,
            contentDescription = "Go to $categoryName",
            tint = MaterialTheme.colorScheme.onSurface.copy(alpha = ContentAlpha.medium)
        )
    }
    Divider(color = MaterialTheme.colorScheme.onSurface.copy(alpha = DividerAlpha))
}

@Composable
fun CategoryCardItem(
    categoryName: String,
    onCategorySelected: (String) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onCategorySelected(categoryName) }
            .padding(vertical = 4.dp, horizontal = 16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = RoundedCornerShape(4.dp)
    ) {
        Row(
            modifier = Modifier
                .background(color = Color.White)
                .fillMaxWidth()
                .padding(top = 16.dp, bottom = 16.dp, start = 16.dp, end = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = categoryName,
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = AppColors.App_Black,
                    fontWeight = FontWeight.SemiBold
                )
            )
            Icon(
                modifier = Modifier.size(16.dp),
                imageVector = Icons.AutoMirrored.Filled.ArrowForwardIos,
                contentDescription = "Go to $categoryName"
            )
        }
    }
}

@Composable
fun CategoryItem(categoryName: String, onCategorySelected: (String) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onCategorySelected(categoryName) }
            .padding(vertical = 16.dp, horizontal = 24.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = categoryName,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.weight(1f)
        )
        Icon(
            imageVector = Icons.AutoMirrored.Rounded.ArrowForwardIos,
            contentDescription = "Go to $categoryName",
            modifier = Modifier.size(16.dp)
        )
    }
}