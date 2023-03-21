package com.example.heroeslistactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.backendless.Backendless
import com.backendless.async.callback.AsyncCallback
import com.backendless.exceptions.BackendlessFault
import com.backendless.persistence.DataQueryBuilder
import com.example.databases.Loan
import com.example.databases.MainActivity
import com.example.databases.databinding.ActivityLoanListBinding

class LoanListActivity : AppCompatActivity() {

    companion object {
        val TAG: String = "HeroesListActivity"
    }

    private lateinit var binding: ActivityLoanListBinding
    lateinit var adapter: LoanAdapter

    var loans: List<Loan?>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoanListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val userId = intent.getStringExtra(MainActivity.EXTRA_USERID)



    }

    private fun retrieveAllData(userId: String) {


        val whereClause: String = "ownerId = '$userId'"
        val queryBuilder = DataQueryBuilder.create()
        queryBuilder.whereClause = whereClause
        Backendless.Data.of(Loan::class.java).find(queryBuilder, object : AsyncCallback<List<Loan?>?> {
            override fun handleResponse(foundLoans: List<Loan?>?) {
                // all Contact instances have been found
                Log.d(MainActivity.TAG, "handleResponse: $foundLoans")
                loans = foundLoans

            }

            override fun handleFault(fault: BackendlessFault) {
                // an error has occurred, the error code can be retrieved with fault.getCode()
                Log.d(MainActivity.TAG, "handleFault: ${fault.message}")
            }
        })

        if (loans != null) {
        adapter = LoanAdapter(loans as List<Loan>)
        }

        binding.recyclerViewLoanList.adapter = adapter

        binding.recyclerViewLoanList.layoutManager = LinearLayoutManager(this)
    }

//    override fun onCreateOptionsMenu(menu: Menu): Boolean {
//        val inflater: MenuInflater = menuInflater
//        inflater.inflate(R.menu.heroes_list_options_menu, menu)
//        return true
//
//    }
//
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        // Handle item selection
//        return when (item.itemId) {
//            R.id.menuItem_heroesListOptionsMenu_sortByName -> {
//                Toast.makeText(this, "Sorted by Name", Toast.LENGTH_SHORT).show()
//                adapter.dataSet = adapter.dataSet.sortedBy {
//                    it.name
//                }
//                adapter.notifyDataSetChanged()
//                true
//            }
//            R.id.menuItem_heroesListOptionsMenu_sortByRank -> {
//                Toast.makeText(this, "Sorted by Rank", Toast.LENGTH_SHORT).show()
//                adapter.dataSet = adapter.dataSet.sorted()
//                adapter.notifyDataSetChanged()
//                true
//            }
//            R.id.menuItem_heroesListOptionsMenu_sortBySuperpowerLength -> {
//                Toast.makeText(this, "Sorted by Superpower Length", Toast.LENGTH_SHORT).show()
//                adapter.dataSet = adapter.dataSet.sortedBy {
//                    it.superpower.length
//                }
//                adapter.notifyDataSetChanged()
//                true
//            }
//            else -> super.onOptionsItemSelected(item)
//        }
//    }

}



