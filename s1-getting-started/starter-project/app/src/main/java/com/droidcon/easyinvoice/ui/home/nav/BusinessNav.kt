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

package com.droidcon.easyinvoice.ui.home.nav

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.droidcon.easyinvoice.ui.AppScreen
import com.droidcon.easyinvoice.ui.home.mybusinesses.ManageMyBusiness
import com.droidcon.easyinvoice.ui.home.mybusinesses.MyBusinesses

fun NavGraphBuilder.businessNav(navController: NavController) {
    navigation(
        startDestination = AppScreen.MyBusinesses.Home.route,
        route = AppScreen.MyBusinesses.route
    ) {

        composable(AppScreen.MyBusinesses.Home.route) {
            MyBusinesses(navController)
        }

        composable(AppScreen.MyBusinesses.ManageMyBusiness.route) {
            ManageMyBusiness(navController)
        }

    }
}