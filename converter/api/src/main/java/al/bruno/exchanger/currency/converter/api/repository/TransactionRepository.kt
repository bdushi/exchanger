package al.bruno.exchanger.currency.converter.api.repository

import al.bruno.exchanger.currency.converter.api.domain.Transaction

interface TransactionRepository {
    suspend fun insert(transaction: Transaction): Long
}