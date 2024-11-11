package al.bruno.exchanger.data.di

import al.bruno.exchanger.data.local.AppDatabase
import al.bruno.exchanger.data.local.dao.BalanceDao
import al.bruno.exchanger.data.local.dao.CommissionDao
import al.bruno.exchanger.data.local.dao.ExchangeDao
import al.bruno.exchanger.data.local.dao.TransactionDao
import al.bruno.exchanger.data.local.dao.ExchangeRuleDao
import androidx.room.Room
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

/*
INSERT INTO Balance (amount, currency, dateCreated, lastUpdated)
VALUES (1000.0, 'EUR', DATE('now'), DATE('now'));

INSERT INTO ExchangeRule (priority, description, condition, action, value, enable, dateCreated, lastUpdated) VALUES
    (1, "Standard commission rules", "transactionCount >= 5", "COMMISSION", 0.07, true, DATE('now'), DATE('now')),
    (2, "Reward after specific conditions", "transactionCount >= 10", "REWARD", 0.01, false, DATE('now'), DATE('now')),
    (1, "Discount on exchanges", "transactionCount >= 20", "DISCOUNT", 0.05, false, DATE('now'), DATE('now')),
    (3, "Every tenth exchange is free", "transactionCount % 10 == 0", "FREE_EXCHANGE", 0.0, false, DATE('now'), DATE('now')),
    (4, "Exchanges up to 200 Euros are free", "amount >= 200", "FREE_UP_TO_LIMIT", 0.0, false, DATE('now'), DATE('now'));

INSERT INTO TransactionRule (condition, action) VALUES
    ("transactionCount >= 5", "applyCommission"),
    ("transactionCount >= 10", "applyReward"),
    ("transactionCount >= 20", "applyDiscount");

INSERT INTO Parameter (key, value, transactionRuleId) VALUES
    ("rate", "0.007", 1),
    ("rewardPercentage", "0.01", 2),
    ("discountRate", "0.05", 3),
    ("maxDiscount", "10.0", 3);
*/

val databaseModule = module {
    single<AppDatabase> {
        Room.databaseBuilder(
            androidContext(),
            AppDatabase::class.java, "exchanger"
        )
            .createFromAsset("exchanger.db")
            .build()
    }

    single<TransactionDao> {
        get<AppDatabase>().transactionDao()
    }
    single<BalanceDao> {
        get<AppDatabase>().balanceDao()
    }
    single<ExchangeDao> {
        get<AppDatabase>().exchangeDao()
    }
    single<ExchangeRuleDao> {
        get<AppDatabase>().transactionRuleDao()
    }
    single<CommissionDao> {
        get<AppDatabase>().commissionDao()
    }
}
