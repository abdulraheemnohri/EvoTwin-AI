package com.evotwin.utils

import android.util.Base64

class SecurityManager {
    fun encrypt(data: String): String {
        // Mock encryption: Base64
        return Base64.encodeToString(data.toByteArray(), Base64.DEFAULT)
    }

    fun decrypt(encryptedData: String): String {
        // Mock decryption
        return String(Base64.decode(encryptedData, Base64.DEFAULT))
    }
}
