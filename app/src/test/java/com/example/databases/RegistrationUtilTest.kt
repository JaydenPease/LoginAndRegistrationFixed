package com.example.databases

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
        val actual = RegistrationUtil.validatePassword("thepasswordever1", "thepasswordever1")
        assertThat(actual).isFalse()
    }

    @Test
    fun validatePassword_goodPassword_isTrue() {
        val actual = RegistrationUtil.validatePassword("Password1", "Password1")
        assertThat(actual).isTrue()
    }

    @Test
    fun validateEmail_startsWithAnAt_isFalse() {
        val actual = RegistrationUtil.validateEmail("@gmail.com")
        assertThat(actual).isFalse()
    }

    @Test
    fun validateEmail_noAt_isFalse() {
        val actual = RegistrationUtil.validateEmail("emailgmail.com")
        assertThat(actual).isFalse()
    }

    @Test
    fun validateEmail_noPeriod_isFalse() {
        val actual = RegistrationUtil.validateEmail("email@gmailcom")
        assertThat(actual).isFalse()
    }

    @Test
    fun validateEmail_empty_isFalse() {
        val actual = RegistrationUtil.validateEmail("")
        assertThat(actual).isFalse()
    }

    @Test
    fun validateEmail_noDomain_isFalse() {
        val actual = RegistrationUtil.validateEmail("email@.com")
        assertThat(actual).isFalse()
    }

    @Test
    fun validateEmail_goodEmail_isTrue() {
        val actual = RegistrationUtil.validateEmail("email@gmail.com")
        assertThat(actual).isTrue()
    }

    @Test
    fun validateUsername_empty_isFalse() {
        val actual = RegistrationUtil.validateUsername("")
        assertThat(actual).isFalse()
    }

    @Test
    fun validateUsername_tooShort_isFalse() {
        val actual = RegistrationUtil.validateUsername("yo")
        assertThat(actual).isFalse()
    }

    @Test
    fun validateUsername_isAlreadyTaken_isFalse() {
        val actual = RegistrationUtil.validateUsername("Already taken")
        assertThat(actual).isFalse()
    }

    @Test
    fun validateUsername_goodUsername_isTrue() {
        val actual = RegistrationUtil.validateUsername("theusernameever")
        assertThat(actual).isTrue()
    }



    // Make the tests for the other functions in the Util class with
    // the common failures and 1 success for each
}