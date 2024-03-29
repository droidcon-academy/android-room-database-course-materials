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

package com.droidcon.easyinvoice.data.entities

import androidx.room.Embedded
import androidx.room.Relation

data class InvoiceWithItems(
    @Embedded val invoice: Invoice,
    @Relation(
        parentColumn = "business_id",
        entityColumn = "id"
    )
    val business: Business,
    @Relation(
        parentColumn = "customer_id",
        entityColumn = "id"
    )
    val customer: Customer,
    @Relation(
        parentColumn = "tax_id",
        entityColumn = "id"
    )
    val tax: Tax,
    @Relation(
        parentColumn = "id",
        entityColumn = "invoice_id"
    )
    val items: List<InvoiceItem>
) {
    val invoiceAmount: Double
        get() = totalAmount + taxAmount

    val taxAmount: Double
        get() = totalAmount * tax.value / 100

    val totalAmount: Double
        get() = items.sumOf { it.qty * it.price }
}