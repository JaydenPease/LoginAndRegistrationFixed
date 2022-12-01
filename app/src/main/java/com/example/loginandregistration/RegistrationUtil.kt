package com.example.loginandregistration

//create the code to validate that all the sign up information is entered in the correct format here

class RegistrationUtil {

    companion object {

    public fun validatePassword(password: String, confirmPassword: String): Boolean {

        var passwordsMatch: Boolean = false
        var passwordContainsNumber: Boolean = false
        var passwordContainsCapitalLetter: Boolean = false
        var passwordLongerThanOrEqualTo8Letters: Boolean = false

        //test if passwords match
        if (password == confirmPassword) {
            passwordsMatch = true
        }

        //test if password is long enough
        if(password.length >= 8) {
            passwordLongerThanOrEqualTo8Letters = true
        }

        //test if password contains a capital letter
        for(i in password.indices) {
            if (password.substring(i, i + 1) == password.substring(i, i + 1).uppercase()) {
                passwordContainsCapitalLetter = true
                break
            }
        }

        //test for a number in password
        for(i in password.indices)
            for(j in 0..9) {
                if(j.toString() == password.substring(i,i+1)) {
                    passwordContainsNumber = true
                    break
                }
                if(passwordContainsNumber) {break}
            }



        return (passwordsMatch && passwordContainsNumber && passwordContainsCapitalLetter && passwordLongerThanOrEqualTo8Letters)
    }

}
}