/*
 * Copyright (c) 2022. Droidcon
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.droidcon.easyinvoice.ui.home.invoices

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.droidcon.easyinvoice.R
import com.droidcon.easyinvoice.data.utils.toDateString
import com.droidcon.easyinvoice.ui.theme.AppTheme
import com.droidcon.easyinvoice.ui.theme.spacing
import com.droidcon.easyinvoice.ui.utils.INR

@Composable
fun InvoiceCard(onClick: () -> Unit, onMenuClick: (menu: InvoiceMenu) -> Unit) {
    val context = LocalContext.current
    val spacing = MaterialTheme.spacing

    val isMenuExpanded = remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(start = spacing.medium, end = spacing.medium, top = spacing.medium)
            .clickable { onClick.invoke() }
    ) {
        Column(
            modifier = Modifier.padding(spacing.medium)
        ) {

            Row(
                verticalAlignment = Alignment.Top,
                modifier = Modifier.padding(bottom = spacing.small)
            ) {
                Text(
                    text = context.toDateString(1666455527343),
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.weight(0.9f)
                )

                Box(
                    modifier = Modifier
                        .weight(0.1f)
                        .clickable {
                            isMenuExpanded.value = true
                        }
                ) {
                    Image(
                        imageVector = Icons.Filled.MoreVert,
                        contentDescription = stringResource(id = R.string.empty),
                        modifier = Modifier.align(Alignment.TopEnd)
                    )

                    InvoiceDropDown(
                        expanded = isMenuExpanded.value,
                        isPaid = true,
                        onItemClick = {
                            onMenuClick.invoke(it)
                            isMenuExpanded.value = false
                        },
                        onDismiss = {
                            isMenuExpanded.value = false
                        }
                    )
                }
            }

            Row {
                Text(
                    text = "${stringResource(id = R.string.from)} DroidCon",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.weight(1f)
                )
                Text(
                    text = "${stringResource(id = R.string.to)} Android Club",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.End
                )
            }

            Text(
                text = "$INR 500",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(top = spacing.small, bottom = spacing.small)
            )

            InvoiceMarker(isPaid = true)
        }
    }
}

@Composable
fun InvoiceDropDown(
    expanded: Boolean,
    isPaid: Boolean,
    onItemClick: (item: InvoiceMenu) -> Unit,
    onDismiss: () -> Unit,
) {
    val spacing = MaterialTheme.spacing
    DropdownMenu(
        expanded = expanded,
        onDismissRequest = { onDismiss.invoke() },
    ) {

        val invoiceMenu = listOf(
            if (isPaid) InvoiceMenu.MarkAsUnPaid else InvoiceMenu.MarkAsPaid,
            InvoiceMenu.Edit,
            InvoiceMenu.Delete,
        )

        invoiceMenu.forEach { menu ->
            DropdownMenuItem(
                text = {
                    Row {
                        Icon(
                            imageVector = menu.icon,
                            contentDescription = stringResource(menu.title),
                            modifier = Modifier.size(ButtonDefaults.IconSize)
                        )
                        Spacer(modifier = Modifier.size(spacing.small))
                        Text(text = stringResource(menu.title))
                    }
                },
                onClick = {
                    onItemClick.invoke(menu)
                }
            )
        }
    }
}


@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun InvoicePreviewLight() {
    AppTheme {
        InvoiceCard({}, {})
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun InvoicePreviewDark() {
    AppTheme {
        InvoiceCard({}, {})
    }
}