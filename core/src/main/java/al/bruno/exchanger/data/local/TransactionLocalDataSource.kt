package al.bruno.exchanger.data.local

import al.bruno.exchanger.data.local.model.Transaction

interface TransactionLocalDataSource {
    suspend fun insert(transaction: Transaction): Long
}