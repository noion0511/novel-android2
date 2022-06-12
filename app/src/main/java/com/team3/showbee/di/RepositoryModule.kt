package com.team3.showbee.di

import com.team3.showbee.data.repository.financial.FinancialRepository
import com.team3.showbee.data.repository.financial.FinancialRepositoryImpl
import com.team3.showbee.data.repository.login.LogInRepository
import com.team3.showbee.data.repository.login.LogInRepositoryImpl
import com.team3.showbee.data.repository.schedule.ScheduleRepository
import com.team3.showbee.data.repository.schedule.ScheduleRepositoryImpl
import com.team3.showbee.data.repository.user.UserRepository
import com.team3.showbee.data.repository.user.UserRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindsLoginRepository(
        repositoryImpl: LogInRepositoryImpl
    ): LogInRepository

    @Binds
    abstract fun bindsUserRepository(
        repositoryImpl: UserRepositoryImpl
    ): UserRepository

    @Binds
    abstract fun bindsFinancialRepository(
        repositoryImpl: FinancialRepositoryImpl
    ): FinancialRepository

    @Binds
    abstract fun bindsScheduleRepository(
        repositoryImpl: ScheduleRepositoryImpl
    ): ScheduleRepository

}