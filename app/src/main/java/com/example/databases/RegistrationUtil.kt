package com.example.databases

//create the code to validate that all the sign up information is entered in the correct format here

class RegistrationUtil {

    companion object {

    public fun validatePassword(password: String, confirmPassword: String): Boolean {

        var passwordsMatch: Boolean = false
        var passwordContainsNumber: Boolean = false
        var passwordContainsCapitalLetter: Boolean = false
        var passwordLongerThanOrEqualTo8Letters: Boolean = false

        var isANumber: Boolean = false

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
            if (password.substring(i, i + 1).equals(password.substring(i, i + 1).uppercase(), ignoreCase = false)) {
                for(j in 0..9) {
                    if(j.toString() == password.substring(i, i+1)) {
                        isANumber = true
                        break
                    }
                }
                if(!isANumber) {
                    passwordContainsCapitalLetter = true
                    break
                }
                else {
                    isANumber = false
                }

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



        return ((passwordsMatch && passwordContainsNumber) && (passwordContainsCapitalLetter && passwordLongerThanOrEqualTo8Letters))
    }



        public fun validateEmail(email: String): Boolean{

            var containsPrefix: Boolean = false
            var containsDomain: Boolean = false



            if(email.length > 0) {
                if(email.substring(0, 1) != "@") {containsPrefix = true}
                else {return false}
            }
            else {return false}


            var pastAt: Boolean = false
            var pastPeriod: Boolean = false
            for(i in email.indices) {

                if(pastAt && email.substring(i, i+1) == "@") {return false}
                if(email.substring(i, i+1) == "." && pastPeriod) {return false}
                if(email.substring(i, i+1) == "." && !pastAt) {return false}

                if(email.substring(i, i+1) == "." && pastAt) {pastPeriod = true}


                if(email.substring(i, i+1) == "@") {pastAt = true}


                if(pastAt && !pastPeriod && email.substring(i, i+1) != "@" && email.substring(i, i+1) != ".") {containsDomain = true}



            }


            return containsPrefix && containsDomain

        }

        public fun validateUsername(username: String): Boolean {
            if(!(username.length >= 3)) {return false}

            if(username.lowercase() == "already taken") {return false}

            return true
        }

}
}