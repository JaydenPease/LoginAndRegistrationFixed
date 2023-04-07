package com.example.databases

import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.backendless.Backendless
import com.backendless.async.callback.AsyncCallback
import com.backendless.exceptions.BackendlessFault
import com.example.databases.databinding.ActivityLoanDetailBinding
import java.util.*

class LoanDetailActivity : AppCompatActivity() {

    private lateinit var binding : ActivityLoanDetailBinding
    var loanIsEditable = false
    var cal = Calendar.getInstance()
    lateinit var loan : Loan

    companion object {
        val TAG: String = "LoanDetailActivity"

        val EXTRA_LOAN = "loan"

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoanDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val passedLoan = intent.getParcelableExtra<Loan>(EXTRA_LOAN)
        if(passedLoan == null) {
            loan = Loan()
            toggleEditable()
        } else {
            loan = passedLoan

            binding.checkBoxLoanDetailIsFullyRepaid.isChecked = loan.fullyRepaid
            binding.editTextLoanDetailInitialLoan.setText(loan.loanAmount.toString())
            binding.editTextLoanDetailBorrower.setText(loan.name)
            binding.editTextLoanDetailAmountRepaid.setText(loan.amountRepaid.toString())
            binding.textViewLoanDetailAmountStillOwed.text = String.format("Still Owed %.2f", loan.loanAmount - loan.amountRepaid)
        }



        binding.buttonLoanDetailSave.setOnClickListener {

            //save loan: decide if the loan is a new one and save new or update existing
            if(loan.ownerId.isNullOrBlank()) {
                loan.ownerId = intent.getStringExtra(MainActivity.EXTRA_USERID)!!
            }
            // get the values from all the fields and update the loan object
            loan.name = binding.editTextLoanDetailBorrower.text.toString()
            loan.loanAmount = binding.editTextLoanDetailInitialLoan.text.toString().toDouble()
            loan.amountRepaid = binding.editTextLoanDetailAmountRepaid.text.toString().toDouble()

            // make the backendless call to save the object


            Backendless.Data.of(Loan::class.java)
                .save(loan, object : AsyncCallback<Loan?> {
                    override fun handleResponse(response: Loan?) {
                        Toast.makeText(this@LoanDetailActivity, "Loan saved", Toast.LENGTH_SHORT).show()
                    }

                    override fun handleFault(fault: BackendlessFault) {
                        // an error has occurred, the error code can be retrieved with fault.getCode()
                        Log.d(TAG, fault.message)
                    }
                })

            toggleEditable()


        }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu_loan_detail, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection
        return when (item.itemId) {
            R.id.menu_item_loan_detail_edit -> {
                toggleEditable()
                true
            }
            R.id.menu_item_loan_detail_delete -> {
                deleteFromBackendless()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun deleteFromBackendless() {
        Backendless.Data.of(Loan::class.java).remove( loan,
            object : AsyncCallback<Long?> {
                override fun handleResponse(response: Long?) {
                    // Person has been deleted. The response is the
                    // time in milliseconds when the object was deleted
                    Toast.makeText(this@LoanDetailActivity, "${loan.name} Deleted", Toast.LENGTH_SHORT).show()
                    finish()
                }

                override fun handleFault(fault: BackendlessFault) {
                    Log.d(TAG, "handleFault: ${fault.message}")
                }
            })
    }


    private fun toggleEditable() {
        if (loanIsEditable) {
            loanIsEditable = false
            binding.buttonLoanDetailSave.isEnabled = false
            binding.buttonLoanDetailSave.visibility = View.GONE
            binding.checkBoxLoanDetailIsFullyRepaid.isEnabled = false
            binding.editTextLoanDetailBorrower.inputType = InputType.TYPE_NULL
            binding.editTextLoanDetailBorrower.isEnabled = false
            binding.editTextLoanDetailAmountRepaid.inputType = InputType.TYPE_NULL
            binding.editTextLoanDetailAmountRepaid.isEnabled = false
            binding.editTextLoanDetailInitialLoan.inputType = InputType.TYPE_NULL
            binding.editTextLoanDetailInitialLoan.isEnabled = false
            binding.checkBoxLoanDetailIsFullyRepaid.isClickable = false
        } else {
            loanIsEditable = true
            binding.buttonLoanDetailSave.isEnabled = true
            binding.buttonLoanDetailSave.visibility = View.VISIBLE
            binding.checkBoxLoanDetailIsFullyRepaid.isEnabled = true
            binding.editTextLoanDetailBorrower.inputType = InputType.TYPE_TEXT_VARIATION_PERSON_NAME
            binding.editTextLoanDetailBorrower.isEnabled = true
            binding.editTextLoanDetailAmountRepaid.inputType = InputType.TYPE_CLASS_NUMBER
            binding.editTextLoanDetailAmountRepaid.isEnabled = true
            binding.editTextLoanDetailInitialLoan.inputType = InputType.TYPE_CLASS_NUMBER
            binding.editTextLoanDetailInitialLoan.isEnabled = true
            binding.checkBoxLoanDetailIsFullyRepaid.isClickable = true
        }
    }
}