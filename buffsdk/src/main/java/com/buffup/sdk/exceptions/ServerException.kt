package com.buffup.sdk.exceptions

class ServerException(code: Int, message: String?) : NetworkException(code, message)