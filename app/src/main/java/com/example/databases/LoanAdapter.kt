package com.example.databases

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.backendless.Backendless
import com.backendless.async.callback.AsyncCallback
import com.backendless.exceptions.BackendlessFault


class LoanAdapter(var dataSet: List<Loan>) :
    RecyclerView.Adapter<LoanAdapter.ViewHolder>() {



    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder)
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textViewName: TextView
        val textViewAmount: TextView

        val layout : ConstraintLayout

        init {
            textViewName = view.findViewById(R.id.textView_itemLoan_name)
            textViewAmount = view.findViewById(R.id.textView_itemLoan_amount)

            layout = view.findViewById(R.id.itemLoan_layout)
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_loan, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        val loan = dataSet.get(position)
        viewHolder.textViewName.text = loan.name
        viewHolder.textViewAmount.text = "$${loan.loanAmount.toString()}"
        viewHolder.layout.isLongClickable = true

        viewHolder.layout.setOnLongClickListener {
            val popMenu = PopupMenu(viewHolder.textViewName.context, viewHolder.textViewName)
            popMenu.inflate(R.menu.menu_loan_list_context)
            popMenu.setOnMenuItemClickListener {
                when(it.itemId) {
                    R.id.menu_loanlist_delete -> {
                        deleteFromBackendless(position)
                        true
                    }
                    else -> true
                }

            }
            popMenu.show()
            true
        }


        viewHolder.layout.setOnClickListener {
            Toast.makeText(it.context, "${loan.name} has been clicked", Toast.LENGTH_SHORT).show()

            //make the Intent to open the new activity
//            val detailIntent = Intent(it.context, HeroesDetailsActivity::class.java)
//            detailIntent.putExtra(HeroesDetailsActivity.EXTRA_HERO, hero)
//
//            //launch the activity
//            it.context.startActivity(detailIntent)
        }

    }

    private fun deleteFromBackendless(position: Int) {
        Log.d("LoanAdapter", "deleteFromBackendless: Trying to delete ${dataSet[position]}")

        Backendless.Data.of(Loan::class.java).remove(dataSet[position],
            object : AsyncCallback<Long?> {
                override fun handleResponse(response: Long?) {
                    // Contact has been deleted. The response is the
                    // time in milliseconds when the object was deleted
                }

                override fun handleFault(fault: BackendlessFault) {
                    // an error has occurred, the error code can be
                    // retrieved with fault.getCode()
                }
            })

    }



    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size



}
