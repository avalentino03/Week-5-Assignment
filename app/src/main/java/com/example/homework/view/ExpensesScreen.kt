package com.example.homework.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.homework.ui.theme.Brown
import com.example.homework.ui.theme.ErrorRed
import com.example.homework.ui.theme.Tan
import com.example.homework.viewmodel.ExpenseViewModel
import java.util.Locale
private fun rupiah(value: Long): String =
    "Rp " + String.format(Locale.US, "%,d", value).replace(',', '.')
@Composable
fun ExpenseScreen(vm: ExpenseViewModel = viewModel()) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .padding(20.dp)
    ) {
        Text(
            text="expenses",
            style=MaterialTheme.typography.headlineMedium,
            fontWeight=FontWeight.Bold
        )
        Text(
            text="Total Expense : ${rupiah(vm.total)}",
            style=MaterialTheme.typography.bodyMedium
        )
        Spacer(modifier=Modifier.height(16.dp))
        OutlinedTextField(
            value =vm.name,
            onValueChange =vm::onNameChange,
            label = {Text("expensename") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        Row(verticalAlignment = Alignment.Top) {
            OutlinedTextField(
                value = vm.amount,
                onValueChange = vm::onAmountChange,
                label = { Text("Amount of Expense") },
                singleLine = true,
                isError = vm.error != null,
                supportingText = { vm.error?.let { Text(it) } },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.weight(1f)
            )
            IconButton(
                onClick = vm::addExpense,
                modifier = Modifier.padding(start = 8.dp, top = 8.dp)
            ) {
                Icon(Icons.Default.Add, contentDescription = "Add", tint = Brown)
            }
        }



        Spacer(modifier = Modifier.height(8.dp))

        LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            items(vm.expenses, key = { it.id }) { expense ->
                Card(
                    colors = CardDefaults.cardColors(containerColor = Tan),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier.padding(start = 16.dp, top = 8.dp, bottom = 8.dp, end = 4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = "Name : ${expense.name}",
                                fontWeight = FontWeight.SemiBold
                            )
                            Text(
                                text = rupiah(expense.amount),
                                style = MaterialTheme.typography.bodySmall
                            )
                        }
                        IconButton(onClick = { vm.deleteExpense(expense) }) {
                            Icon(
                                Icons.Default.Delete,
                                contentDescription = "Delete",
                                tint = ErrorRed
                            )
                        }
                    }
                }
            }
        }
    }
}