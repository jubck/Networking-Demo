package com.hfad.networkingdemo

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.hfad.networkingdemo.databinding.FragmentDetailBinding


/**
 * A simple [Fragment] subclass.
 * Use the [DetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DetailFragment : Fragment() {


     var hero_id: Int = 0

    private var _binding : FragmentDetailBinding? =null
    private val binding get() = _binding !!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //   recipient = arguments!!.getString("recipient")
        val bundle = arguments
        if (bundle == null) {
            Log.e("DetailFragment", "DetailFragment did not receive hero id")

            return
        }
        hero_id = DetailFragmentArgs.fromBundle(bundle).heroId

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.name.text = heroList.get(hero_id).name
        binding.realName.text = heroList.get(hero_id).realname
        binding.bio.text = heroList.get(hero_id).bio
        binding.firstApperance.text = heroList.get(hero_id).firstappearance
        Glide.with(requireContext()).load(heroList.get(hero_id).imageurl)
            .apply(RequestOptions().centerCrop())
            .into(binding.itemImage)

    }
}