package com.nazrawi.table.common.exceptions

import java.io.IOException

class APIException : IOException() {
    override val message: String
        get() = "Error Connecting to Server"
}