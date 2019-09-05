package com.ryan.common.rx


import com.githink.common.common.ResultCode
import com.ryan.common.http.entity.BaseEntity
import rx.Observable
import rx.functions.Func1

/**
 * 通用数据处理
 */
class BaseConvert<T> : Func1<BaseEntity<T>, Observable<T>> {
    override fun call(t: BaseEntity<T>): Observable<T> {
        if (t.status != ResultCode.SUCCESS) {
            return Observable.error(BaseException(t.status, t.message))
        }
        return Observable.just(t.data)
    }
}