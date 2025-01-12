package kr.or.dohands.dozon.user.controller.data

import lombok.AllArgsConstructor

@AllArgsConstructor
class EditRequest(
    val id: String,
    val password: String
) {

}