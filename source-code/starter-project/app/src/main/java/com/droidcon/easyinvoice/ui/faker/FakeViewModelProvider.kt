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

package com.droidcon.easyinvoice.ui.faker

/*
* Currently there is a problem with *Jetpack Compose Preview* & *Hilt*
* Jetpack compose is not able to inject using hiltViewModel() to generate Compose Previews
* In future when both these libraries will be compatible, we can remove this object
* But for now, to see preview, we can use this FakeViewModelProvider
* */
object FakeViewModelProvider {

}