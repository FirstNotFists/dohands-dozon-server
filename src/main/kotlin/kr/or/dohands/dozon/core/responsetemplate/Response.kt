package kr.or.dohands.dozon.core.responsetemplate

import lombok.Data

@Data
class Response<T>(
    var status: Int,
    var data: T,
    var message: String
) {

}