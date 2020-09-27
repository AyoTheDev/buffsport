package com.buffup.sdk.exceptions

import java.io.IOException

open class NetworkException(val code : Int, message : String) : IOException(message)