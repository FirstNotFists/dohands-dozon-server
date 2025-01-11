package kr.or.dohands.dozon.notification.exception

import kr.or.dohands.dozon.notification.response.BaseResponse

class ErrorResponseException(
    code: Int,
    phrase: String,
    errors: List<BaseResponse.Error>
) : Exception(createMessage(code, phrase, errors)) {

    companion object{
        private fun createMessage(code: Int, phrase: String, errors: List<BaseResponse.Error>): String {
            val sb : StringBuilder = StringBuilder()
            sb.append(String.format("Return code: %d%n", code))
            sb.append(String.format("Phrase: %s%n", phrase))
            sb.append(String.format("Errors: %n", errors))
            if (errors != null && errors.isEmpty()){
                for (error in errors){
                    sb.append(String.format("%s%n", error.toString()))
                }
            } else {
                sb.append("")
            }
            return sb.toString()
        }
    }
}