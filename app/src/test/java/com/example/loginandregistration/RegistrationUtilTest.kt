package com.example.loginandregistration

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class RegistrationUtilTest {
    
    // methodName_someCondition_expectedResult
    @Test
    fun validatePassword_emptyPassword_isFalse() {
        
        //remember to uncomment this once you have your code finished
        
        val actual = RegistrationUtil.validatePassword("", "")
        // assertThat(actualValue).isEqual(desiredValue)
        // there are other methods you can use besides isEqual
        assertThat(actual).isFalse()
        
    }

    @Test
    fun validatePassword_passwordsDontMatch_isFalse() {
        val actual = RegistrationUtil.validatePassword("BozoBozo1", "DoltDolt2")

        assertThat(actual).isFalse()
    }

    @Test
    fun validatePassword_passwordTooShort_isFalse() {
        val actual = RegistrationUtil.validatePassword("Shorty1", "Shorty1")
        assertThat(actual).isFalse()
    }

    @Test
    fun validatePassword_noNumber_isFalse() {
        val actual = RegistrationUtil.validatePassword("NoNumberLmao", "NoNumberLmao")
        assertThat(actual).isFalse()
    }

    @Test
    fun validatePassword_noCapitalLetter_isFalse() {
        val actual = RegistrationUtil.validatePassword("thepasswordever1", "thepasswordever2")
        assertThat(actual).isFalse()
    }

    @Test
    fun validatePassword_goodPassword_isTrue() {
        val actual = RegistrationUtil.validatePassword("Password1", "Password1")
        assertThat(actual).isTrue()
    }



    // Make the tests for the other functions in the Util class with
    // the common failures and 1 success for each
}