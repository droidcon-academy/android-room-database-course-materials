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

package com.droidcon.easyinvoice.ui.home.taxes

import android.annotation.SuppressLint
import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.droidcon.easyinvoice.R
import com.droidcon.easyinvoice.ui.AppScreen
import com.droidcon.easyinvoice.ui.commons.EmptyScreen
import com.droidcon.easyinvoice.ui.faker.FakeViewModelProvider
import com.droidcon.easyinvoice.ui.home.customers.Customer
import com.droidcon.easyinvoice.ui.theme.AppTheme

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Taxes(viewModel: TaxesViewModel, navController: NavController) {
    val context = LocalContext.current
    val taxes = viewModel.taxes.collectAsState()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate(AppScreen.Taxes.ManageTaxes.route)
                }
            ) {
                Icon(Icons.Filled.Add, stringResource(id = R.string.empty))
            }
        },
        content = {
            if (taxes.value.isNotEmpty()) {
                LazyColumn {
                    items(taxes.value) { item ->
                        Tax(
                            tax = item,
                            onClick = {
                                viewModel.setUpdating(item)
                                navController.navigate(AppScreen.Taxes.ManageTaxes.route)
                            }
                        )
                    }
                }
            } else {
                EmptyScreen(
                    title = stringResource(id = R.string.empty_taxes),
                    onRefresh = {}
                )
            }
        }
    )
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun TaxesPreviewLight() {
    AppTheme {
        Taxes(FakeViewModelProvider.provideTaxesViewModel(), rememberNavController())
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun TaxesPreviewDark() {
    AppTheme {
        Taxes(FakeViewModelProvider.provideTaxesViewModel(), rememberNavController())
    }
}