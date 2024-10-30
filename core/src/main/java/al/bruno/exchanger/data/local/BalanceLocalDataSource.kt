package al.bruno.exchanger.data.local

import al.bruno.exchanger.data.local.model.Balance

interface BalanceLocalDataSource {
    suspend fun balance(): Balance
}