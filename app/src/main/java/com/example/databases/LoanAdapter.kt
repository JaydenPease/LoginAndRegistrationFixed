package com.example.heroeslistactivity

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.databases.Loan
import com.example.databases.R


class LoanAdapter(var dataSet: List<Loan>) :
    RecyclerView.Adapter<LoanAdapter.ViewHolder>() {



    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder)
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
//        val textViewName: TextView
//        val textViewDescription: TextView
//        val textViewRanking: TextView
        val layout : ConstraintLayout

        init {
//            textViewName = view.findViewById(R.id.textView_itemHero_name)
//            textViewDescription = view.findViewById(R.id.textView_itemHero_description)
//            textViewRanking = view.findViewById(R.id.textView_itemHero_ranking)
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
        val loan = dataSet[position]
//        viewHolder.textViewName.text = hero.name
//        viewHolder.textViewDescription.text = hero.description
//        viewHolder.textViewRanking.text = hero.ranking.toString()

        viewHolder.layout.setOnClickListener {
//            Toast.makeText(it.context, "${hero.name} has been clicked", Toast.LENGTH_SHORT).show()

            //make the Intent to open the new activity
//            val detailIntent = Intent(it.context, HeroesDetailsActivity::class.java)
//            detailIntent.putExtra(HeroesDetailsActivity.EXTRA_HERO, hero)
//
//            //launch the activity
//            it.context.startActivity(detailIntent)
        }

    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

}
