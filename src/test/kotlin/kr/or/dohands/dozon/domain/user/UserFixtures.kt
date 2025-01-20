package kr.or.dohands.dozon.domain.user

fun signIn(
    id : String,
    password : String
): SignInRequest {
    return SignInRequest(
        id = id,
        password = password
    )
}

