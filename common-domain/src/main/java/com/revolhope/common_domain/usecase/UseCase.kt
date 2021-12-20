package com.revolhope.common_domain.usecase

import com.revolhope.common_domain.state.model.State
import kotlinx.coroutines.flow.Flow

abstract class UseCase<REQ, RES> {

    suspend operator fun invoke(params: REQ): Flow<State<RES>> {


        TODO()
    }

}