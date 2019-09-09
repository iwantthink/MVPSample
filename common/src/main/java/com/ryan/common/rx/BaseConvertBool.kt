package com.ryan.common.rx

import com.ryan.common.common.ResultCode
import com.ryan.common.http.entity.BaseEntity
import io.reactivex.Observable
import io.reactivex.functions.Function

/**
 * 对bool类型数据的通用处理
 */
class BaseConvertBool<T> : Function<BaseEntity<T>, Observable<Boolean>> {
    override fun apply(t: BaseEntity<T>): Observable<Boolean> {
        if (t.status != ResultCode.SUCCESS) {
            return Observable.error(BaseException(t.status, t.message))
        }
        return Observable.just(true)
    }
}