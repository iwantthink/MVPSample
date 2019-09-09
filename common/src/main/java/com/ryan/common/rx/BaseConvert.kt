package com.ryan.common.rx


import com.ryan.common.common.ResultCode
import com.ryan.common.http.entity.BaseEntity
import io.reactivex.Observable
import io.reactivex.functions.Function

/**
 * 通用数据处理
 *
 * status 和message 仅在失败时回传!!
 */
class BaseConvert<T> : Function<BaseEntity<T>, Observable<T>> {
    override fun apply(t: BaseEntity<T>): Observable<T> {
        if (t.status != ResultCode.SUCCESS) {
            return Observable.error(BaseException(t.status, t.message))
        }
        return Observable.just(t.data)
    }


}