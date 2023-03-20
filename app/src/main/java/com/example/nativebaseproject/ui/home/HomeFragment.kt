package com.example.nativebaseproject.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.nativebaseproject.R
import com.example.nativebaseproject.common.action_popup.ActionItem
import com.example.nativebaseproject.common.action_popup.ActionPopup
import com.example.nativebaseproject.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textHome
        homeViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.textHome.setOnClickListener {
            val actions = listOf<ActionItem<Any>>(ActionItem(icon = R.drawable.ic_home_black_24dp, actionText = "Hello", action = {
                Toast.makeText(requireContext(), "1", Toast.LENGTH_SHORT).show()
            }),
                ActionItem(icon = R.drawable.ic_dashboard_black_24dp, actionText = "Haha", action = {
                    Toast.makeText(requireContext(), "2", Toast.LENGTH_SHORT).show()
                }))
            ActionPopup.show(requireContext(), it, Any(), actions)

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}