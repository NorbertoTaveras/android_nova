import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Bookmarks
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.norbertotaveras.android_nova.presentation.screens.navigator.composables.BottomNavigationItem
import com.norbertotaveras.android_nova.presentation.theme.AppColors
import com.norbertotaveras.android_nova.presentation.theme.NewsTheme
import com.norbertotaveras.android_nova.utils.dimens.Dimens.ExtraSmallPadding2
import com.norbertotaveras.android_nova.utils.dimens.Dimens.IconSize

@Composable
fun NewsBottomNavigation(
    items: List<BottomNavigationItem>,
    selectedItem: Int,
    onItemClick: (Int) -> Unit
) {
    NavigationBar(
        modifier = Modifier.fillMaxWidth(),
        containerColor = AppColors.parentBottom,
        tonalElevation = 10.dp
    ) {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = index == selectedItem,
                onClick = { onItemClick(index) },
                icon = {
                    Column(horizontalAlignment = CenterHorizontally) {
                        Icon(
                            imageVector = item.icon,
                            contentDescription = null,
                            modifier = Modifier.size(IconSize),
                        )
                        Spacer(modifier = Modifier.height(ExtraSmallPadding2))
                        Text(text = item.text, style = MaterialTheme.typography.labelSmall)
                    }
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = AppColors.selectedIconColor,
                    selectedTextColor = AppColors.selectedIconColor,
                    unselectedIconColor = AppColors.unselectedIconColor,
                    unselectedTextColor = AppColors.unselectedIconColor,
                    indicatorColor = MaterialTheme.colorScheme.background
                ),
            )
        }
    }
}

@Preview
@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun NewsBottomNavigationPreview() {
    NewsTheme(dynamicColor = false) {
        NewsBottomNavigation(items = listOf(
            BottomNavigationItem(icon = Icons.Rounded.Home, text = "Home"),
            BottomNavigationItem(icon = Icons.Rounded.Search, text = "Search"),
            BottomNavigationItem(icon = Icons.Rounded.Bookmarks, text = "Bookmark"),
        ), selectedItem = 0, onItemClick = {})
    }
}