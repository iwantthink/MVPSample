package com.ryan.common.rx

import com.ryan.common.common.ResultCode
import com.ryan.common.http.entity.BaseEntity
import rx.Observable
import rx.functions.Func1

/**
 * 对bool类型数据的通用处理
 */
class BaseConvertBool<T> : Func1<BaseEntity<T>, Observable<Boolean>> {
    override fun call(t: BaseEntity<T>): Observable<Boolean> {
        if (t.status != ResultCode.SUCCESS) {
            return Observable.error(BaseException(t.status, t.message))
        }
        return Observable.just(true)

    }
}