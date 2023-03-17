package com.example.heroeslistactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.databases.Loan
import com.example.databases.R
import com.example.databases.databinding.ActivityLoanListBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class LoanListActivity : AppCompatActivity() {

    companion object {
        val TAG:String = "HeroesListActivity"
    }

    private lateinit var binding: ActivityLoanListBinding
    lateinit var adapter: LoanAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoanListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadHeroesFromJSON()
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

    private fun loadHeroesFromJSON() {

//        val inputStream = resources.openRawResource(R.raw.heroes)
//
//        val jsonString = inputStream.bufferedReader().use {
//
//            it.readText()
//        }
//        Log.d(TAG, "onCreate: $jsonString")




        val gson = Gson()


//        val type = object : TypeToken<List<Loan>>() { }.type
//        val loans = gson.fromJson<MutableList<Loan>>(jsonString, type)


//        adapter = LoanAdapter(loans)

        binding.recyclerViewLoanList.adapter = adapter

        binding.recyclerViewLoanList.layoutManager = LinearLayoutManager(this)
    }

}


