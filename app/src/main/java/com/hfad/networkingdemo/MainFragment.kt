package com.hfad.networkingdemo

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback


/**
 * A simple [Fragment] subclass.
 * Use the [MainFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MainFragment : Fragment() {
    lateinit var recyclerView: RecyclerView
    lateinit var recyclerAdapter: RecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.recyclerview)
        recyclerAdapter = RecyclerAdapter(requireContext(), Navigation.findNavController(view))
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = recyclerAdapter

        val apiInterface = ApiInterface.create().getHeros()

        if (apiInterface != null)
            apiInterface.enqueue(object : Callback<ArrayList<Hero?>?> {
                override fun onResponse(
                    call: Call<ArrayList<Hero?>?>,
                    response: Response<ArrayList<Hero?>?>
                ) {
                    if (response?.body() != null)
                        recyclerAdapter.setHerosListItems(response.body()!! as ArrayList<Hero>)
                }

                override fun onFailure(call: Call<ArrayList<Hero?>?>, t: Throwable) {
                    if (t != null)
                        t.message.let { Log.d("onFailure", it) }

                }
            })
        }

    }
}